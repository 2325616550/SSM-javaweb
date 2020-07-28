<%@ page import="domain.User" %>
<%@ page import="service.UserService" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String username = (String) session.getAttribute("username");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
        UserService userService = ctx.getBean("userServiceImpl", UserService.class);
        User user = userService.getUser(username);
        request.setAttribute("user",user);
        String password=user.getPassword();
        StringBuilder display=new StringBuilder("");
        for (int i = 0; i < password.length(); i++) {
            display.append("*");
        }
    %>
    <title>用户信息管理</title>
    <%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        /* Padding and font style */
        table.gridtable{
            margin: 0 auto;
            padding: 0px 280px auto;
        }

        table.gridtable td{
            font-size: 20px;
            font-family: Verdana;
            font-weight: bold;
            background-color: rgb(156, 186, 95);
            color: #000;
            padding: 15px 10px ;
        }

        #img_pre{
            height: 200px;
            width: 200px;
        }
    </style>
    <link type="text/css"  rel="stylesheet" href="static/css/button.css">
    <script type="text/javascript">
        var toggleValue_email=false;
        var toggleValue_name=false;
        var toggleValue_password=false;
        var toggleValue=false;

        function updateEmail() {
            toggleValue_email=!toggleValue_email;
            if (toggleValue_email){
                $("#email").html("<input type='text' id='upemail' value=${requestScope.user.email}>");
            }
            else{
                var flag = confirm("是否修改？");
                if(flag){
                    $.ajax({
                        url:"updateEmail",
                        data:{email:$("#upemail").val()},
                        type:"post",
                        contentType: "application/x-www-form-urlencoded"
                    });
                    location.href="${basePath}pages/user/update_success.jsp";
                }
                else {
                    toggleValue_email = !toggleValue_email;
                }
            }
        }

        function updateName() {
            toggleValue_name=!toggleValue_name;
            if (toggleValue_name){
                $("#username").html("<input type='text' id='upname' value=${requestScope.user.name}>");
            }
            else{
                var flag = confirm("是否修改？");
                if(flag){
                    $.ajax({
                        url:"updateName",
                        data:{name:$("#upname").val()},
                        type:"post",
                        contentType: "application/x-www-form-urlencoded"
                    });
                    location.href="${basePath}pages/user/update_success.jsp";
                }
                else {
                    toggleValue_name = !toggleValue_name;
                }
            }
        }

        function updatePassword() {
            toggleValue_password=!toggleValue_password;
            if (toggleValue_password){
                $("#password").html("<input type='text' id='uppassword' value=${requestScope.user.password}>");
            }
            else{
                var flag = confirm("是否修改？");
                if(flag){
                    $.ajax({
                        url:"updatePassword",
                        data:{password:$("#uppassword").val()},
                        type:"post",
                        contentType: "application/x-www-form-urlencoded"
                    });
                    location.href="${basePath}pages/user/update_success.jsp";
                }
                else {
                    toggleValue_password = !toggleValue_password;
                }
            }
        }
        function updatePhoto() {

                $("#photo").html(" <form action=\"updatePhoto\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                    "        <label>上传头像：</label><input type=\"file\"  accept=\".jpg,.png,.jpeg\" name=\"file\"><br>\n" +
                    "        <input type=\"submit\"> </form>");

        }
        $(function () {
            $("#img_pre").attr("src", "${requestScope.user.img}");
        })
    </script>
</head>
<body>
    <div class="wrap">
            <div class="container">
        <div>
            <span style="color: #FFFFFF;font-size: 40px;position:relative;left: 40%;">用户信息管理</span>
            <a style="color: #FFFFFF; font-size: 24px;position:relative;left:90%" href="index.jsp">回到首页</a>
        </div>
    </div>
                <hr>
        <div class="container">
                <br><br>
                <table class="gridtable">
                    <tr>
                        <td>头像：</td>
                        <td id="photo">  <img id="img_pre" src="" ></td>
                        <td><button onclick="updatePhoto()">修改头像</button></td>
                    </tr>
                    <tr>
                        <td>用户名：</td>
                        <td id="username">${requestScope.user.name}</td>
                        <td><button onclick="updateName()">修改用户名</button></td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td id="email">${requestScope.user.email}</td>
                        <td><button id="btn_email" onclick="updateEmail()">修改邮箱</button></td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td id="password"><%=display%></td>
                        <td ><button id="btn3" onclick="updatePassword()"/>修改密码</td>
                    </tr>
                </table>

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
