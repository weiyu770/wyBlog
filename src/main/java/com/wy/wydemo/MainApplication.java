package com.wy.wydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "com.wy.wydemo")
@Slf4j
@EnableAspectJAutoProxy
public class MainApplication {
    
    // ANSI转义序列
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE_BRIGHT = "\u001B[94m";//蓝色
    public static final String ANSI_GREEN_BRIGHT_UNDERLINE = "\u001B[92;4m"; // 绿色下划线
    public static final String ANSI_YELLOW_BRIGHT_UNDERLINE = "\u001B[93;4m"; // 黄色下划线
    
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(MainApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        // 自动获取代码设置的端口号和路径
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "");
        
        log.info(ANSI_BLUE_BRIGHT + "\n----------------------------------------------------------\n\t" +
                "Application AdminWebApplication is running! Access URLs:\n\t" +
                "Knife4j UI接口文档: \thttp://" + ip + ":" + port + path + "/doc.html\n\t" +
                "Swagger UI接口文档: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n" +
                "----------------------------------------------------------\n\t" +
                "COS存储地址为：https://console.cloud.tencent.com/cos/bucket?bucket=wy770880-1327706280&region=ap-guangzhou&path=%252Farticle%252F\n\t"+
                "作者: wy770\n\t" +
                "邮箱: 3658043236@qq.com\n\t" +
                "个人博客: https://blog.csdn.net/wy990880?spm=1011.2415.3001.5343\n" +
                "----------------------------------------------------------" + ANSI_RESET);
    }
}
