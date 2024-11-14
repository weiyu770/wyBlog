package com.wy.wydemo.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.wydemo.exception.ServiceException;
import com.wy.wydemo.mapper.BlogFileMapper;
import com.wy.wydemo.model.entity.BlogFile;
import com.wy.wydemo.model.vo.query.FileQuery;
import com.wy.wydemo.model.vo.query.PageQuery;
import com.wy.wydemo.model.vo.request.FolderReq;
import com.wy.wydemo.model.vo.response.FileResp;
import com.wy.wydemo.model.vo.response.PageResult;
import com.wy.wydemo.service.BlogFileService;
import com.wy.wydemo.strategy.context.UploadStrategyContext;
import com.wy.wydemo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.wy.wydemo.constant.CommonConstant.FALSE;
import static com.wy.wydemo.constant.CommonConstant.TRUE;

/**
 * @description:
 * @class: BlogFileServiceImpl
 * @author: yu_wei
 * @create: 2024/11/05 23:21
 */
@Service
@Slf4j
public class BlogFileServiceImpl  extends ServiceImpl<BlogFileMapper, BlogFile> implements BlogFileService {
    
    
    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String localPath;
    
    
    @Autowired
    private BlogFileMapper blogFileMapper;
    
    
    @Autowired
    private UploadStrategyContext uploadStrategyContext;
    
    
    @Autowired
    private HttpServletResponse response;
    
    /**
     * 文件上传
     * @param file
     * @param url
     * @param filePath
     */
    @Override
    public void saveBlogFile(MultipartFile file, String url, String filePath) {
        try {
            // 获取文件md5值
            String md5 = FileUtils.getMd5(file.getInputStream());
            // 获取文件扩展名
            String extName = FileUtils.getExtension(file);
            BlogFile existFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                    .select(BlogFile::getId)
                    .eq(BlogFile::getFileName, md5)
                    .eq(BlogFile::getFilePath, filePath));
            if (Objects.nonNull(existFile)) {
                return;
            }
            // 保存文件信息
            BlogFile newFile = BlogFile.builder()
                    .fileUrl(url)
                    .fileName(md5)
                    .filePath(filePath)
                    .extendName(extName)
                    .fileSize((int) file.getSize())
                    .isDir(FALSE)
                    .build();
            blogFileMapper.insert(newFile);
        } catch (IOException e) {
               log.info("文件上传失败");
        }
    }
    
    
    /**
     * 查询文件列表
     * @param fileQuery
     * @return
     */
    @Override
    public PageResult<FileResp> listFileVOList(FileQuery fileQuery) {
        log.debug("查询条件: filePath={}, current={}, size={}",
                fileQuery.getFilePath(), fileQuery.getCurrent(), fileQuery.getSize());
        
        // 创建查询条件
        LambdaQueryWrapper<BlogFile> queryWrapper = new LambdaQueryWrapper<>();
        if (fileQuery.getFilePath() != null && !fileQuery.getFilePath().trim().isEmpty()) {
            queryWrapper.eq(BlogFile::getFilePath, fileQuery.getFilePath());
        }
        
        // 查询文件数量
        Long count = blogFileMapper.selectCount(queryWrapper);
        if (count == 0) {
            log.warn("未查询到符合条件的文件");
            return new PageResult<>();
        }
        
        // 查询文件列表
        List<FileResp> fileRespList = blogFileMapper.selectFileVOList(fileQuery);
        log.info("查询到 {} 条文件记录", fileRespList.size());
        
        return new PageResult<>(fileRespList, count);
    }
    
    /**
     * 上传文件
     * @param file
     * @param filePath
     */
    @Override
    public void uploadFile(MultipartFile file, String filePath) {
        log.debug("开始上传文件文件名: {} 上传路径: {}", file.getOriginalFilename(), filePath);
        String uploadPath = "/".equals(filePath) ? filePath : filePath + "/";
        // 上传文件
        String url = uploadStrategyContext.executeUploadStrategy(file, uploadPath);
        log.info("文件上传成功 生成的URL: {}", url);
        // 保存文件信息
        saveBlogFile(file, url, filePath);
        log.info("文件信息保存成功 文件名: {}", file.getOriginalFilename());
    }
    
    /**
     * 创建目录
     * @param folder
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFolder(FolderReq folder) {
        log.debug("开始创建目录，目录名: {}, 路径: {}", folder.getFileName(), folder.getFilePath());
        
        String fileName = folder.getFileName();
        String filePath = folder.getFilePath();
        
        // 判断目录是否存在
        BlogFile blogFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                .select(BlogFile::getId)
                .eq(BlogFile::getFilePath, folder.getFilePath())
                .eq(BlogFile::getFileName, fileName));
        
        if (blogFile != null) {
            log.warn("目录 {} 已存在", fileName);
            Assert.isNull(blogFile, "目录已存在");
        }
        
        // 创建目录
        File directory = new File(localPath + filePath + "/" + fileName);
        if (FileUtils.mkdir(directory)) {
            BlogFile newBlogFile = BlogFile.builder()
                    .fileName(fileName)
                    .filePath(filePath)
                    .isDir(TRUE)
                    .build();
            blogFileMapper.insert(newBlogFile);
            log.info("目录 {} 创建成功", fileName);
        } else {
            log.error("创建目录 {} 失败", fileName);
            throw new ServiceException("创建目录失败");
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(List<Integer> fileIdList) {
        log.debug("开始删除文件，文件ID列表: {}", fileIdList);
        
        List<BlogFile> blogFiles = blogFileMapper.selectList(new LambdaQueryWrapper<BlogFile>()
                .select(BlogFile::getFileName, BlogFile::getFilePath, BlogFile::getExtendName, BlogFile::getIsDir)
                .in(BlogFile::getId, fileIdList));
        
        log.info("从数据库中查询到 {} 条文件记录需要删除", blogFiles.size());
        
        // 删除数据库中的文件信息
        blogFileMapper.deleteBatchIds(fileIdList);
        log.info("数据库中已删除文件信息，文件ID列表: {}", fileIdList);
        
        blogFiles.forEach(blogFile -> {
            File file;
            String fileName = localPath + blogFile.getFilePath() + "/" + blogFile.getFileName();
            if (blogFile.getIsDir().equals(TRUE)) {
                // 删除数据库中剩余的子文件
                String filePath = blogFile.getFilePath() + blogFile.getFileName();
                log.debug("目录删除操作，路径: {}", filePath);
                
                blogFileMapper.delete(new LambdaQueryWrapper<BlogFile>().eq(BlogFile::getFilePath, filePath));
                log.info("数据库中已删除子文件，路径: {}", filePath);
                
                // 删除目录
                file = new File(fileName);
                if (file.exists()) {
                    FileUtils.deleteFile(file);
                    log.info("目录 {} 删除成功", fileName);
                } else {
                    log.warn("目录 {} 不存在，无法删除", fileName);
                }
            } else {
                // 删除文件
                file = new File(fileName + "." + blogFile.getExtendName());
                if (file.exists()) {
                    file.delete();
                    log.info("文件 {} 删除成功", fileName + "." + blogFile.getExtendName());
                } else {
                    log.warn("文件 {} 不存在，无法删除", fileName + "." + blogFile.getExtendName());
                }
            }
        });
        log.debug("文件删除操作完成，文件ID列表: {}", fileIdList);
    }
    
    /**
     * 下载文件全流程
     * @param fileId 文件ID
     */
    @Override
    public void downloadFile(Integer fileId) {
        log.debug("开始下载文件，文件ID: {}", fileId);
        
        // 查询文件信息
        BlogFile blogFile = blogFileMapper.selectOne(new LambdaQueryWrapper<BlogFile>()
                .select(BlogFile::getFilePath, BlogFile::getFileName,
                        BlogFile::getExtendName, BlogFile::getIsDir)
                .eq(BlogFile::getId, fileId));
        Assert.notNull(blogFile, "文件不存在");
        String filePath = localPath + blogFile.getFilePath() + "/";
        
        if (blogFile.getIsDir().equals(FALSE)) {
            // 下载的不是目录
            String fileName = blogFile.getFileName() + "." + blogFile.getExtendName();
            log.info("开始下载文件: {}", fileName);
            downloadFile(filePath, fileName);
        } else {
            // 下载的是目录则压缩下载
            String fileName = filePath + blogFile.getFileName();
            File src = new File(fileName);
            File dest = new File(fileName + ".zip");
            try {
                log.info("开始压缩目录: {}", fileName);
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(dest));
                toZip(src, zipOutputStream, src.getName());
                zipOutputStream.close();
                log.info("目录压缩完成: {}", dest.getPath());
                
                downloadFile(filePath, blogFile.getFileName() + ".zip");
                log.info("压缩包下载成功: {}", blogFile.getFileName() + ".zip");
            } catch (IOException e) {
                log.error("文件下载失败: {}", e.getMessage());
            } finally {
                if (dest.exists()) {
                    dest.delete();
                    log.debug("删除临时压缩文件: {}", dest.getPath());
                }
            }
        }
    }
    
    /**
     * 下载文件方法
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    private void downloadFile(String filePath, String fileName) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            log.debug("准备下载文件，文件路径: {}, 文件名: {}", filePath, fileName);
            
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
            fileInputStream = new FileInputStream(filePath + fileName);
            outputStream = response.getOutputStream();
            IOUtils.copyLarge(fileInputStream, outputStream);
            
            log.info("文件 {} 下载成功", fileName);
        } catch (IOException e) {
            log.error("文件下载失败: {}", e.getMessage());
            throw new ServiceException("文件下载失败");
        } finally {
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(outputStream);
            log.debug("下载文件流已关闭");
        }
    }
    
    /**
     * 压缩文件夹
     * @param src 源文件
     * @param zipOutputStream 压缩输出流
     * @param name 文件名
     * @throws IOException IO异常
     */
    private static void toZip(File src, ZipOutputStream zipOutputStream, String name) throws IOException {
        log.debug("开始压缩文件夹: {}", src.getPath());
        
        for (File file : Objects.requireNonNull(src.listFiles())) {
            if (file.isFile()) {
                log.debug("正在压缩文件: {}", file.getName());
                ZipEntry zipEntry = new ZipEntry(name + "/" + file.getName());
                zipOutputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(file);
                int b;
                while ((b = fileInputStream.read()) != -1) {
                    zipOutputStream.write(b);
                }
                fileInputStream.close();
                zipOutputStream.closeEntry();
                log.info("文件压缩完成: {}", file.getName());
            } else {
                log.debug("进入子目录: {}", file.getName());
                toZip(file, zipOutputStream, name + "/" + file.getName());
            }
        }
        
        log.debug("文件夹 {} 压缩完成", src.getPath());
    }

    

}
