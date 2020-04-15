package edu.bjtu.sse.djd;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Database Connection Pool
public class DBCP {

    private static HikariDataSource hikariDataSource;

    /**
     * @Author 董金达
     * @Date 22:08 2020/4/14
     * @Description TODO
     * @Param
     * @return com.zaxxer.hikari.HikariDataSource
     **/
    public static HikariDataSource getHikariDataSource() {

        if (hikariDataSource != null) {
            return hikariDataSource;
        }

        synchronized (DBCP.class) {
            if (hikariDataSource == null) {
                try {
                    // Hikari 数据库连接池配置
                    HikariConfig hikariConfig = new HikariConfig();

                    // 导入 property 配置文件
                    Properties properties = new Properties();

                    InputStream inputStream = DBCP.class.getClassLoader().getResourceAsStream("Application.properties");

                    properties.load(inputStream);
                    inputStream.close();

                    // 数据库驱动 (Class.forName())
                    hikariConfig.setDriverClassName(properties.getProperty("driverName"));
//                hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
                    // 数据库url
                    hikariConfig.setJdbcUrl(properties.getProperty("dbUrl"));
//                hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/school?useSSL=FALSE&serverTimezone=UTC&characterEncoding=utf8");
                    // 数据库用户名
                    hikariConfig.setUsername(properties.getProperty("userName"));
//                hikariConfig.setUsername("root");
                    // 数据库密码
                    hikariConfig.setPassword(properties.getProperty("password"));
//                hikariConfig.setPassword("990818");

                    hikariDataSource = new HikariDataSource(hikariConfig);

                    return hikariDataSource;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        getHikariDataSource();
//    }


}
