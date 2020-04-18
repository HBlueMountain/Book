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
	<style type="text/css">
		a{
			text-decoration:none;
		}
	</style>
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
				<td colspan="2">名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td colspan="2">${ book.name }</td>
					<td>${ book.price }</td>
					<td>${ book.author }</td>
					<td>${ book.sales }</td>
					<td>${ book.stock }</td>
					<td>
						<a href="manager/books?action=getBook&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">修改</a>
						<a class="deleteBook" href="manager/books?action=delete&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">删除</a>
					</td>

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
				<a href="manager/books?action=page&pageNo=${ requestScope.page.pageNo-1 }"> |上一页 </a>
			</c:if>

			<%-- 代码输出的开始 --%>
			<c:choose>
				<%-- 第一种大情况:如果总页码小于等于5 ,则页码输出如下.页码范围是: 1 到 总页码--%>
				<c:when test="${requestScope.page.pageToTal <= 5}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${ requestScope.page.pageToTal }"></c:set>
				</c:when>
				<%-- 第二种情况: 亚麻大于 5 的情况 --%>
				<c:when test="${ requestScope.page.pageToTal > 5 }">
					<c:choose>
						<%-- 第一种小情况: 当前页码是前面3个,1,2,3.页码范围是:1 到 5 固定  --%>
						<c:when test="${ requestScope.page.pageNo <=3 }">
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="5"></c:set>
						</c:when>
						<%-- 第二种小情况:当前页码是最后3个,8,9,10.页码范围是 : 总页码-4 到总页码  --%>
						<c:when test="${ requestScope.page.pageNo > requestScope.page.pageToTal - 3 }">
							<c:set var="begin" value="${ requestScope.page.pageToTal - 4 }"></c:set>
							<c:set var="end" value="${ requestScope.page.pageToTal }"></c:set>
						</c:when>
						<%-- 第三种情况: 中间页码 ,4,5,6,7. 页码范围是: 当前页码-2 到 当前页码+2 --%>
						<c:otherwise>
							<c:set var="begin" value="${ requestScope.page.pageNo - 2 }"></c:set>
							<c:set var="end" value="${ requestScope.page.pageNo + 2 }"></c:set>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
				<c:forEach begin="${ begin }" end="${ end }" var="i">
					<c:if test="${ i != requestScope.page.pageNo}">
						<a href="manager/books?action=page&pageNo=${i}">${i}</a>
					</c:if>
					<c:if test="${ i == requestScope.page.pageNo}">
						<a style="color: red" href="manager/books?action=page&pageNo=${i}">[${i}]</a>
					</c:if>
				</c:forEach>
			<%-- 代码输出的结束 --%>


			<%-- 如果是最后一页则不显示下一页,末页 --%>
			<c:if test="${ requestScope.page.pageNo < requestScope.page.pageToTal }">
				<a  href="manager/books?action=page&pageNo=${ requestScope.page.pageNo+1 }"> 下一页| </a>
				<a href="manager/books?action=page&pageNo=${ requestScope.page.pageToTal }">末页</a>
			</c:if>
			共 ${ requestScope.page.pageToTal } 页，${ requestScope.page.getPageToTalCount } 条记录 跳到第
				<input value="${param.pageNo}" name="pn" id="pn_input"/> 页
				<input id="searchPageBtn" type="button" value="确 定">
			<script type="text/javascript">
				$(function () {
					// 跳到指定的页码,单击事件
					$("#searchPageBtn").click(function () {
						// 1.获取输入框里的页码
						var pageNo = $("#pn_input").val();
						// 获取总页码
						var pageTotal = ${requestScope.page.pageToTal};
						if ( pageNo < 1 || pageNo > pageTotal){
							alert("很抱歉,您输入的页码不在当前范围内!");
							pageNo = ${ requestScope.page.pageNo };
						}
						//它有一个属性 href 它可以获取浏览器地址栏中的地址
						//href 属性可读可写
						// 2.跳转指定页
						location.href ="${pageScope.basePath}manager/books?action=page&pageNo=" + pageNo;
					});
				});
			</script>
		</div>
		<br>
		<%-- 分页条结束 --%>

	</div>
	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>