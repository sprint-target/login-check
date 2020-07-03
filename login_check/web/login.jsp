<%--
  Created by IntelliJ IDEA.
  User: zhimoulu
  Date: 2019/9/13
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>

    </style>
</head>
<body>
    <form action="/login_check/loginServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"></td>
            </tr>
            <tr>
                <td colspan="2"><img id="checkCode" src="/login_check/checkCodeServlet"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit"></td>
            </tr>
        </table>
    </form>
    <div>
        <%=request.getAttribute("user_error") == null ? "":request.getAttribute("user_error")%>
    </div>
    <div>
        <%=request.getAttribute("checkCode_error") == null ? "":request.getAttribute("checkCode_error")%>
    </div>
</body>
<script>
    document.getElementById("checkCode").onclick = function () {
        var time = new Date().getTime();
        this.src = "/login_check/checkCodeServlet?time="+time;
    }

</script>
</html>
