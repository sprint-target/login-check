package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testlogin(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("1123");

        UserDao userDao = new UserDao();
        userDao.login(user);
    }
}
