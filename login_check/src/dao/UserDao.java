package dao;



import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

/**
 * 操作数据库中User表的类
 */
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());// JdbcTemplate对象：简化连接池获取连接与释放连接操作

    /**
     * 用户登录
     * @param loginUser 只有用户名和密码
     * @return User（包含用户所有信息）
     */
    public User login(User loginUser){
        try{
            String sql = "select * from user where username = ? and password = ?";
            User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());

            return user;
        }catch (DataAccessException e){
            System.out.println(e);
            return null;
        }
        /*
            spring中使用JdbcTemplate的queryForObject方法，
                当查不到数据时会抛出如下异常：
                    org.springframework.dao.EmptyResultDataAccessException: Incorrect result size: expected 1, actual 0

                    发现当查询的result为空时，size赋值为0，并抛出EmptyResultDataAccessException异常，此为原因所在。
                    猜测Spring这样设计可能是为了防止用户不对空值做出判断，保证了程序的健壮性。同时，当results的size大于1时同样会抛出异常，以保证返回单一的Object。
                解决方法：
                    把这个异常捕获，用try/catch，
                    需要获取的user数据刚好也是一条
         */
    }
}
