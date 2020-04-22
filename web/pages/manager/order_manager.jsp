<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">订单管理系统</span>
		<%-- 静态包含 图书管理菜单 --%>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	</div>
	<div id="main">
		<table>
			<tr>
				<td style="width: 40%">日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${ order.createTime}</td>
					<td>${ order.totalPrice }</td>
					<td><a style="text-decoration: none;" href="order?action=orderDetails&orderId=${order.orderId}">查看详情</a></td>
					<td>
						<c:choose>
							<c:when test="${ order.status == 0}">
								<a style="text-decoration: none; color: red" href="order?action=sndOrder&orderId=${order.orderId}">点击发货</a>
							</c:when>
							<c:when test="${ order.status == 1}">
								未签收
							</c:when>
							<c:when test="${ order.status == 2}">
								已签收
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>