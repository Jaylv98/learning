package demo1;

import org.testng.annotations.Test;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser=new User();
        loginUser.setUsername("zhangsan");
        loginUser.setPassword("123");

        UserDao dao=new UserDao();
        User user = dao.login(loginUser);

        System.out.println(user);
    }
}
