
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        .lineheight{
            width: 320px;
            display: block;
            height: 36px;
            border: 0;
            outline: 0;
            padding: 6px 10px;
            line-height: 30px;
            margin: 32px;
            font-size: 30px;
            color: #5C3317;
        }
        button{
            background: gold;
            border: none;
            border-radius: 50%;
            width: 200px;
            height: 100px;
            font-size: 25px;
            text-align: center;
            color: dodgerblue;
        }
    </style>
    <script type="text/javascript">
        function service() {
            location.href="${basePath}pages/user/userinfo.jsp";
        }
    </script>
</head>
<body>
<div class="wrap">
        <div class="container">
            <div>
                <span style="color: #FFFFFF;font-size: 40px;position:relative;left: 40%;">吉比特的网</span>
                <span id="user" style="color: #FFFFFF; font-size: 24px;position:relative;left:90%" onclick="user()">用户:${sessionScope.username}</span>
            </div>
        </div>
    <hr color="gold">
    <div class="lineheight">
        <div><button  onclick="service()"/>用户信息设置</div>
        <br>
        <div><button />我是功能2 不知道做什么</div>
        <br>
        <div><button />我是功能3 不知道做什么</div>
        <br>
        <div><button />我是功能4 不知道做什么</div>
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
