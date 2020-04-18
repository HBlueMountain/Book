<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteBook").click(function () {
				var text = $(this).parent().parent().find("td:first").text();
				/**
				 * 根据 a 标签和它的样式,查找绑定的单机事件
				 * confirm 是 JavaScript 提供的 确认消息提示框
				 * 它的参数是提示框显示的内容
				 * 用户点击确定返回 : true
				 * 用户点击取消返回 : false
				 */
				return confirm("你确定要删除《" + text + "》图书吗?")
			});
		});
		$(function () {
			//跳到指定的页码
			$("#searchPageBtn").click(function () {
				var pageNo = $("#pn_input").val();
				//JavaScript提供了一个 location 地址栏对象
				//它有一个属性 href 它可以获取浏览器地址栏中的地址
				//href 属性可读可写
				location.href = "http://localhost:8080/book/manager/books?action=page&pageNo=7";
			});
		});
	</script>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">图书管理系统</span>
		<%-- 静态包含 图书管理菜单 --%>
		<%@ include file="/pages/common/manager_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td>${ book.name }</td>
					<td>${ book.price }</td>
					<td>${ book.author }</td>
					<td>${ book.sales }</td>
					<td>${ book.stock }</td>
					<td><a href="manager/books?action=getBook&id=${ book.id }">修改</a></td>
					<td><a class="deleteBook" href="manager/books?action=delete&id=${ book.id }">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>
		</table>
		<br>

		<%-- 分页条的开始--%>
		<div id="page_nav">
			<%-- 大于首页才显示 --%>
			<c:if test="${ requestScope.page.pageNo > 1}">
				<a href="manager/books?action=page&pageNo=1">首页</a>
				<a href="manager/books?action=page&pageNo=${ requestScope.page.pageNo-1 }">上一页</a>
			</c:if>
			<a href="#">3</a>
			【${ requestScope.page.pageNo }】
			<a href="#">5</a>
			<%-- 如果是最后一页则不显示 --%>
			<c:if test="${ requestScope.page.pageNo < requestScope.page.pageToTal }">
				<a href="manager/books?action=page&pageNo=${ requestScope.page.pageNo+1 }">下一页</a>
				<a href="manager/books?action=page&pageNo=${ requestScope.page.pageToTal }">末页</a>
			</c:if>
			共 ${ requestScope.page.pageToTal } 页，${ requestScope.page.getPageToTalCount } 条记录 到第
				<input value="4" name="pn" id="pn_input"/>页
				<input id="searchPageBtn" type="button" value="确定">
		</div>
		<%-- 分页条结束 --%>
	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>