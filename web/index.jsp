<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <%@include file="head.jsp" %>
    </head>
    <body class="container-fluid">
        <%
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath().concat("/login.jsp"));
            } else {
        %>
                <%@include file="header.jsp" %>
                <%@include file="pointofinterests.jsp" %>
        <%
            }
        %>
    </body>
</html>
