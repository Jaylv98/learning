package config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/*
*   该类是配置类，其作用与xml相同
*   spring中的新注解
*   @Configuration
*       作用：指定当前类是一个配置类
*       细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
*   @ComponentScan
*       作用：用于通过注解指定spring在创建容器时要扫描的包
*       属性：
*           value：和basePackages的作用相同，都是用于指定创建容器时要扫描的包
*                   使用此注解就等同于在xml配置了：
*                   <context:component-scan base-package="com.itheima"></context:component-scan>
*   @Bean
*       作用：用于把当前方法的返回值作为bean对象存入spring的IOC容器中
*       属性：
*           name：用于指定bean的id。默认值为当前方法的名称
*       细节：当使用注解配置方法时，如果方法有参数，spring框架就会去容器中查找有没有可用的bean对象。
*            查找的方式和Autowired注解是相同的
*   @Import
*       作用：用于导入其他配置类
*       属性：
*           value：用于指定其他配置类的字节码
*                   使用了Import的注释之后，有Import注解的类就是父配置类，而导入的都是子配置类
*   @PropertySource
*       作用：用于指定properties文件的位置
*       属性：
*           value：指定文件的名称和路径
*               关键字：classpath：表示类路径下
*
*
* */
//@Configuration
@ComponentScan(basePackages = {"com.itheima"})
@Import(JdbcConfiguration.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
