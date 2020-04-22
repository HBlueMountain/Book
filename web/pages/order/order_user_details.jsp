<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by YongXin Xue on 2020/04/15 10:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
	<%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">订单详情</span>

		<%-- 静态包含 登录成功之后跳转的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td style="width: 40%">商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td>《 ${ orderItem.name } 》</td>
					<td>${ orderItem.count }</td>
					<td>￥: ${ orderItem.price } 元</td>
					<td>￥: ${ orderItem.totalPrice } 元</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>