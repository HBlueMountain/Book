<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
    <%@ include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给用户名绑定失去焦点事件
            $("#username").blur(function () {
                //获取用户名信息
                var usernameText = this.value;
                //发ajax请求给服务器验证用户名是否可用
                $.getJSON(
                    "${basePath}user",
                    "action=ajaxExistsUsername&username=" + usernameText,
                    function (data) {
                        //收到结果后响应到客户端
                        if (data.existsUsername){
                            $("span.errorMsg").text("用户名已存在!");
                        }else{
                            $("span.errorMsg").text("用户可以用!");
                        }
                    })
            });

            // 给验证码图片绑定单击事件
           $("#codeImg").click(function () {
                // 在事件响应的function函数中有一个this对象.这个this对象是当前正在响应事件的dom对象
                // img 标签的src 属性是图片的连接地址,src可读可写
                this.src = "${basePath}kaptcha.jpg?d=" + Math.random();
           });

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
                // 验证确认密码：和密码相同
                // 1.获取确认密码内容
                var repwdText = $("#repwd").val();
                // 2.和密码比较
                if (repwdText != passwordText) {
                    // 3.提示密码结果
                    $("span.errorMsg").text("两次密码不一致!");
                    // 阻止表单提交
                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                // 1.获取用邮箱
                var emailText = $("#email").val();
                // 2.验证正则表达式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                // 3.使用test方法验证
                if (!emailPatt.test(emailText)) {
                    // 4.提示邮箱结果
                    $("span.errorMsg").text("用户邮箱不合法!");
                    // 阻止表单提交
                    return false;
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();
                // 去掉验证前后空格
                codeText = $.trim(codeText);
                if (codeText == "" || codeText == null) {
                    // 4.提示用户结果
                    $("span.errorMsg").text("验证码不合法!");
                    // 阻止表单提交
                    return false;
                }
            });

        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">
    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${ requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="register"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${ requestScope.username }"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${ requestScope.email }"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 120px;" id="code" name="code" value=""/>
                        <%--<img alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px">--%>
                        <img id="codeImg" alt="" src="kaptcha.jpg" style="position: absolute; top: 430px; left: 1150px; height: 40px; width: 100px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%-- 静态包含 版权页脚--%>
<%@ include file="/pages/common/footer.jsp" %>
</div>
</body>
</html>