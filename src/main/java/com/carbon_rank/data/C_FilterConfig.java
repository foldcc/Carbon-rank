package com.carbon_rank.data;

import java.io.IOException;
import java.util.Properties;

public class C_FilterConfig {
    static {//静态代码块
        try {
            Properties properties = new Properties();
            //加载配置文件
            properties.load(C_FilterConfig.class.getResourceAsStream("/globa_config.properties"));
            SYSTEM_PATH = properties.getProperty("SYSTEM_PATH");
            USER_PATH = properties.getProperty("USER_PATH");
            Request_Type = properties.getProperty("Request_Type");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String Request_Type;
    public static String SYSTEM_PATH;
    public static String USER_PATH;
}
