package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 账户的业务层实现类
 *
 * XML的配置：
 * <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"
 *         scope="" init-method="" destroy-method="" >
 *       <property name="" value="" / ref=""></property>
 * </bean>
 *
 * 用于创建对象的：
 *          作用和在在xml配置文件中编写一个<bean>标签实现的功能是相同的
 *          @Component
 *              作用：用于把当前类对象存入Spring容器中
 *              属性：
 *                  value：用于指定Bean的id，当操作者未填写时，默认值为当前类名，且首字母小写
 *          @Controller
 *                  一般用于表现层
 *          @Service
 *                  一般用于业务层
 *          @Repository
 *                  一般用于持久层
 *          =======================以上三个注解的作用和属性与Component相同======================================
 *          ==============以上三个是spring框架为我们提供明确的三层使用的注释，使我们的三层对象更加清晰==================
 * 用于注入数据的
 *          作用和在xml配置文件中的bean标签中写一个<property>标签的作用是相同的
 *          @Autowired
 *              作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                   如果IOC容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 *                   如果IOC容器中有多个类型匹配时，先查找数据类型，再查找变量名称
 *              出现位置：
 *                  变量，方法
 *               细节：
 *                  在使用注解注入时，set方法就不是必须的了
 *           @Qualifier
 *               作用：在按照类中注入的基础之上再按照名称注入，它在给类成员注入时不能单独使用， 但是在给方法参数注入时可以
 *               属性：
 *                      value：用于指定注入bean的id
 *            @Resource
 *               作用：直接按照bean的id注入，它可以独立使用
 *               属性：
 *                      name：用于指定bean的id
 *          ======================以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现======================
 *          ==========================================且，集合类型的注入只能通过XML来实现===========================================
 *              @Value
 *                  作用：用于注入基本类型和String类型的数据
 *                  属性
 *                      value：用于指定数据的值，它可以使用SpEL（Spring的EL表达式）
 *                          SpEl写法：${表达式}
 *
 * 用于改变作用范围的
 *          作用和在bean标签中使用scope属性实现的功能是相同的
 *          @Scope
 *              作用：用于指定bean的作用范围
 *              属性：
 *                  value：指定范围的取值   常用取值：singleton  prototype
 * 和生命周期相关的
 *          作用和在bean标签中使用init-method和destroy-method的作用是相同的
 *          @PostConstruct
 *              作用：用于指定初始化方法
 *          @PreDestroy
 *              作用：用于指定销毁方法
 */
@Service(value = "accountService")
@Scope("singleton")
public class AccountServiceImpl implements IAccountService {

  /*  @Autowired
    @Qualifier("accountDao2")*/
    @Resource(name = "accountDao1")
    private IAccountDao accountDao=null;


    public void  saveAccount(){
        accountDao.saveAccount();

    }

    @PostConstruct
    public void init(){
        System.out.println("初始化方法执行");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁方法已执行");
    }
}
