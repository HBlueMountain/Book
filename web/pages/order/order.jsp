<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">我的订单</span>
		<%-- 静态包含 登录成功之后跳转的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td style="width: 40%">日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>		
			<c:forEach items="${ requestScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.totalPrice}</td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0}">未发货</c:when>
							<c:when test="${order.status == 1}">
								<a href="order?action=receiveOrder&orderId=${order.orderId}" style="text-decoration: none; font-size:18px;color: red;">确认签收</a>
							</c:when>
							<c:when test="${order.status == 2}">已签收</c:when>
						</c:choose>
					</td>
					<td><a style="text-decoration: none;" href="order?action=orderDetailsForUser&orderId=${order.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>