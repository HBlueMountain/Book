<%--
  Created by YongXin Xue on 2020/04/15 10:23 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!--
    添加 base 标签
    base 标签一般在 title 标签的下面 [紧跟 title 标签]
    base 标签的值, 一般在项目中都设置到工程路径,就是映射到IDEA代码的 web 目录
    -->
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>
<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath %>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
