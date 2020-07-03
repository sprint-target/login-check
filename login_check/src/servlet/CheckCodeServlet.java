package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码图片Servlet
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics graphics = image.getGraphics();
        //填充背景色
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,width,height);
        //画边框
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,height-1);

        StringBuilder stringBuilder = new StringBuilder();

        //画验证码
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        for (int i =1;i<=4;i++){
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            stringBuilder.append(c);
            graphics.drawString(c+"",width/5*i,height/2);
        }
        //验证码
        String checkCode = stringBuilder.toString();
        //将验证码存入session对象
        request.getSession().setAttribute("checkCode",checkCode);
        //画干扰线
        graphics.setColor(Color.GREEN);
        for (int i =0;i<10;i++){
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int endX = random.nextInt(width);
            int endY = random.nextInt(height);
            graphics.drawLine(startX,startY,endX,endY);
        }
        //输出到页面
        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
