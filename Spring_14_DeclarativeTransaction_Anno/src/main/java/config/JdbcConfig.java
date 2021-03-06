package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//连接数据库相关的配置类
public class JdbcConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String username;

    @Value("${password}")
    private String password;


    //创建JdbcTemplate对象
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    //创建数据源对象
    @Bean(name = "dataSource")
    public DataSource createDataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }
}
