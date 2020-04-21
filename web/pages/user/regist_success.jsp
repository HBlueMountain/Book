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
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
        h1 a {
            color: red;
        }
    </style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
		<span class="wel_word"></span>
		<%-- 静态包含 登录成功之后跳转的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp" %>
	</div>
	<div id="main">
		<h1>注册成功! <a href="/index.jsp">转到主页</a></h1>
	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>