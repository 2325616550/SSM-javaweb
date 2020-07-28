
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>交友大厅</title>
    <%@ include file="/pages/common/head.jsp"%>
    <link type="text/css" rel="stylesheet" href="static/css/button.css" >
    <style type="text/css">
        .lineheight{
            width: 320px;
            display: block;
            height: 36px;
            border: 0;
            outline: 0;
            padding: 6px 10px;
            line-height: 100px;
            margin: 32px;
            font-size: 30px;
            color: #5C3317;
        }
    </style>
    <script type="text/javascript">
        function service() {
            location.href="${basePath}pages/social/findFriends.jsp";
        }
        function social() {

        }
    </script>
</head>
<body>
<div class="wrap">
        <div class="container">
    <div>
        <span style="color: #FFFFFF;font-size: 40px;position:relative;left: 40%;">交友大厅</span>
        <span id="user" style="color: #FFFFFF; font-size: 24px;position:relative;left:90%" onclick="user()">用户:${sessionScope.username}</span>
    </div>
        </div>
    <hr color="gold">
    <div class="lineheight">
        <div><button  onclick="service()"/>寻找好基友</div>
        <div><button  onclick="social()"/>好友列表</div>
        <div>我是功能3</div>
        <div>我是功能4</div>
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
