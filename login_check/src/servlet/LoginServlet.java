package servlet;

import dao.UserDao;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 登录验证Servlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置编码
        //获取验证码进行判断
        String waitCheckCode = request.getParameter("checkCode");//提交的待验证的验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        //判断验证码是否正确
        if (waitCheckCode != null && checkCode.equalsIgnoreCase(waitCheckCode)){
            //验证码正确
            Map<String, String[]> map = request.getParameterMap();
            User loginUser = new User();
            try {
                BeanUtils.populate(loginUser,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            UserDao userdao = new UserDao();
            User user = userdao.login(loginUser);//包含该用户所有信息
            //判断用户是否正确登录
            if (user != null){
                //登录成功
                //信息存入session
                request.getSession().setAttribute("user", user);
                //重定向到success.jsp
                response.sendRedirect("/login_check/success.jsp");
            }else{
                //用户名or密码错误
                //向request对象存储信息
                request.setAttribute("user_error","用户名或密码错误");
                //请求转发
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{
            //验证码错误
            //向request对象存储信息
            request.setAttribute("checkCode_error","验证码错误");
            //请求转发
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
