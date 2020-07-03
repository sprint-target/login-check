<%@ page import="domain.User" %><%--
  Created by IntelliJ IDEA.
  User: zhimoulu
  Date: 2019/9/14
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        User user = (User) request.getSession().getAttribute("user");
        out.write(user.getUsername()+"欢迎您！");
    %>
</body>
</html>
