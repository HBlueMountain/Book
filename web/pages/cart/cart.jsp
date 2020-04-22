<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by YongXin Xue on 2020/04/15 10:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
	<%@ include file="/pages/common/header.jsp"%>
	<script type="text/javascript">
		$(function () {
			// 给删除的 a标签绑定单机事件
			$("a.deleteItemClass").click(function () {
				return confirm("你确定要删除《" + $(this).parent().parent().find("td:first").text() + "》吗?");
			});

			// 给清空购物车绑定单机事件
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗?")
			});

			// 给购物车 数量的输入框内容绑定事件
			$("input[name='count']").change(function () {
				var text = $(this).parent().parent().find("td:first").text();
				var itemId = $(this).attr("itemId");
				var newCount = this.value;
				if (confirm("你确定要修改《" + text + "》数量为: " + newCount + " 吗?")){
					location.href = "${basePath}cart?action=updateCount&id=" + itemId + "&count=" + newCount;
				}else{
					// defaultValue 是原表单项的默认值
					this.value = this.defaultValue;
				}
			})
		})
	</script>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">购物车</span>

		<%-- 静态包含 登录成功之后跳转的菜单 --%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<%--  当购物车为空就显示友好的提示 --%>
			<c:if test="${ empty sessionScope.cart.items }">
				<tr>
					<td colspan="3">
						<p align="left">您的购物车还是空的，您可以<a style="text-decoration: none; color: red" href="pages/user/login.jsp">立即登录</a></p>
						<p align="right">登录后购物车的商品将保存到您的账号中，或者<a style="text-decoration: none; color: red; font-size: 15px" href="index.jsp">去逛逛</a>吧!</p>
					</td>
				</tr>
				<tr>
					<td  colspan="5"><a href="index.jsp"><img src="static/img/cart.jpg" title="去逛逛" alt="购物车"></a></td>
				</tr>
			</c:if>

			<%-- 当购物车不为空就显示 --%>
			<c:if test="${ not empty sessionScope.cart.items }">
				<tr>
					<td style="width: 30%">商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
			</c:if>

			<%-- 当购物车不为空就遍历输出数据 --%>
			<c:if test="${ not empty sessionScope.cart.items}">
				<c:forEach items="${ sessionScope.cart.items}" var="entry">
					<tr>
						<td>${ entry.value.name }</td>
						<td>
							<input type="text" name="count" id="count" style="width: 60px;"
							itemId="${entry.value.id}" value="${ entry.value.count }" />
						</td>
						<td>${ entry.value.price }</td>
						<td>${ entry.value.totalPrice }</td>
						<td><a class="deleteItemClass" style="text-decoration: none;" href="cart?action=deleteItem&id=${ entry.value.id }">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			
		</table>
		<%-- 当购物车不为空就遍历输出数据 --%>
		<c:if test="${ not empty sessionScope.cart.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${ sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${ sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" style="text-decoration: none;" href="cart?action=clear">清空购物车</a></span>
				<span class="cart_span"><a style="text-decoration: none;" href="order?action=cartOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%-- 静态包含 版权页脚--%>
	<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>