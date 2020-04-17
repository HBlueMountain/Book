<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
    <%@ include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                // 1.获取用户名
                var usernameText = $("#username").val();
                // 2.验证正则表达式
                var usernamePatt = /^\w{5,12}$/;
                // 3.使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    // 4.提示用户结果
                    $("span.errorMsg").text("用户名不合法!");
                    // 阻止表单提交
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                // 1.获取用密码
                var passwordText = $("#password").val();
                // 2.验证正则表达式
                var passwordPatt = /^\w{5,12}$/;
                // 3.使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    // 4.提示密码结果
                    $("span.errorMsg").text("用户密码不合法!");
                    // 阻止表单提交
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>
<div class="login_banner">
    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${ empty requestScope.msg ? "请输入用户名和密码" : requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username" value="${ requestScope.username }"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%-- 静态包含 版权页脚--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>