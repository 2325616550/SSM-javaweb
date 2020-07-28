<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function(){
            // 给验证码的图片，绑定单击事件
            $("#code_img").click(function () {
                this.src = "${basePath}/loginimage.do?d=" + Math.random();
            });

            $("#sub_btn").click(function () {
                var usernameText = $("#username").val();
                var usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(usernameText)) {
                    $("span.errorMsg").text("用户不合法！");
                    return false;
                }
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }
                var repwdText = $("#repwd").val();
                if (repwdText != passwordText) {
                    $("span.errorMsg").text("确认密码和密码不一致！");
                    return false;
                }
                var emailText = $("#email").val();
                var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();

                //去掉验证码前后空格
                // alert("去空格前：["+codeText+"]")
                codeText = $.trim(codeText);
                // alert("去空格后：["+codeText+"]")

                if (codeText == null || codeText == "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空！");
                    return false;
                }
                // 去掉错误信息
                $("span.errorMsg").text("");

            });
        });
    </script>
</head>
<body>
<div class="wrap">
        <div class="container">
             <h1 style="color: white; margin: 0; text-align: center">欢迎注册</h1>
                <span class="errorMsg" style="color: white; margin: 0; text-align: center">${empty param.msg?"":param.msg}</span>
            <form action="register.do" method="post">
                <label><input type="text" id="username" name="name" placeholder="用户名" value="${param.name}"/></label>
                <label><input type="text" id="email" name="email" placeholder="邮箱" value="${param.email}"/></label>
                <label><input type="password" id="password" name="password" placeholder="密码" /></label>
                <label><input type="password" id="repwd" name="confirmPassword" placeholder="确认你的密码" /></label>
                <label><input type="text" name="code"  id="code" placeholder="验证码" /></label>
                <img id="code_img" alt="验证码刷不出来蜡" src="loginimage.do">
                <input type="submit" id="sub_btn" value="注册"/>
                <p class="change_link" style="text-align: center">
                    <span class="text">已经有了账号？</span>
                    <a href="pages/user/login.jsp" class="to_login">快去登录吧</a>
                </p>
            </form>
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
