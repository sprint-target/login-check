package util;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类，使用Druid连接池
 */
public class JDBCUtils {
    private static DataSource dataSource;

    static {
        try {
            //加载druid配置文件
            Properties properties = new Properties();
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);

            //获取druid连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接池对象
     * @return 连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 获取连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();

    }
}
