package demo1;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

//操作数据库种User表的类
public class UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /*
    *       登录方法
    *           参数：loginUser 只有用户名和密码
    *           返回值：user包含用户所有数据
    *           没有查询到，返回null
    * */
    public User login(User loginUser){
        try {
            //编写SQL
            String sql="select * from user where username=? and password=?";
            //调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());


            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
