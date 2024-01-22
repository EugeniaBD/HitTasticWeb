<%@page import="java.util.List"%>
<%@page import="com.enums.PointOfInterestType"%>
<!DOCTYPE html>
<%@include file="head.jsp" %>
<%@page import="com.model.PointOfInterest"%>
<%@page import="com.model.Comment"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Point of Interest</title>
        <%@include file="head.jsp" %>
    </head>
    <body class="container-fluid">        
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                <% 
                    Object updateUserObject = request.getAttribute("user");
                    if(updateUserObject != null && updateUserObject instanceof User){
                        User u = (User) updateUserObject;
                        %>
                        <form action="${pageContext.request.contextPath}/User" method="POST">
                            <input name="id" value="<%= u.getId()%>" hidden>
                            <div class="mb-3">
                                <label class="form-label">username</label>
                                <input class="form-control" name="username" value="<%= u.getUsername()%>">    
                            </div>
                            <div class="mb-3">
                                <label class="form-label">password</label>                                
                                <input class="form-control" type="password" name="password" value="<%= u.getPassword()%>"/>
                            </div>
                            <button class="btn btn-primary" type="submit">Update</button>
                        </form>                
                        <%
                    }
                %>                    
                </div>
            </div>
        </div>
    </body>
</html>
