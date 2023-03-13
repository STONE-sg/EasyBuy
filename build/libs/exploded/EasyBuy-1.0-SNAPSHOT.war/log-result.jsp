<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2021/12/7
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在进入首页...</title>
</head>
<body>
<%
    session.setAttribute("uname", request.getParameter("userName"));
%>
<script type="text/javascript">
    setTimeout("location.href='log-index.jsp'");
</script>
</body>
</html>
