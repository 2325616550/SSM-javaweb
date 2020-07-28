<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>
</head>
<body>
<div class="wrap">
        <div class="container">
          <h1 style="color: white; margin: 0; text-align: center">${empty param.msg?"电波没能到达服务器耶":param.msg}</h1>
    <br><br>
        <h3 align="center" style="color: white; margin: 0; text-align: center">${empty param.ex?"":param.ex}</h3>
    <p class="change_link" style="text-align: center">
        <br><br><br>
            <a href="index.jsp" class="to_login">返回首页吧</a>
    </p>
        </div>
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>



</div>
</body>
</html>
