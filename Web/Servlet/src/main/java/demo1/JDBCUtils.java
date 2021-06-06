package demo1;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

//JDBC工具类  使用Druid连接池
public class JDBCUtils {
    private static DataSource ds;

    static{

        try {
            //加载配置文件
            Properties pro=new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            /*InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);*/  //未知原因  空指针异常

            InputStream InputStream = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\Administrator\\IdeaProjects\\Web\\Servlet\\src\\druid.properties")));
            pro.load(InputStream);

            //初始化连接池对象
            ds=DruidDataSourceFactory.createDataSource(pro);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }

    //获取Connection对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
