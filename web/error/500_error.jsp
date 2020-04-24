<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
Created by YongXin Xue on 2020/04/15 10:09
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城500首页</title>
    <%-- 使用静态包含 base标签 css样式 jQuery文件 --%>
    <%@ include file="/pages/common/header.jsp" %>
    <style type="text/css">
        body{ text-align:center}
        .div1{
            width: auto;
            height: 600px;
            background: #e3e3e3;
            position: relative;
        }
        .div2{
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -600px;
            margin-top: -200px;
            width: 1200px;
            height: 400px;
            line-height: 400px;
            font-size: 25px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="div1">
        <div class="div2">
             <h2>404 您访问的页面程序员小哥正在抢修中!! <a style="font-size: 25px; color: red" href="/index.jsp">返回首页</a></h2>
        </div>
    </div>
<%-- 静态包含 版权页脚--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>