package com.wy.wydemo.utils;

import cn.dev33.satoken.secure.SaSecureUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 密码加密工具类
 * @class: SecurityUtils
 * @author: yu_wei
 * @create: 2024/11/04 13:12
 */
public class SecurityUtils {
    
    /**
     * 校验密码
     *
     * @param storedPasswordHash 数据库中存储的加密密码
     * @param inputPassword 用户输入的原始密码
     * @return 是否正确
     */
    public static boolean checkPw(String storedPasswordHash, String inputPassword) {
        // 对输入的密码进行加密
        String encryptedPassword = sha256Encrypt(inputPassword);
        // 比较加密后的密码和存储的密码哈希
        return StringUtils.equals(encryptedPassword, storedPasswordHash);
    }
    
    /**
     * sha256加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String sha256Encrypt(String password) {
        return SaSecureUtil.sha256(password);
    }
    
}