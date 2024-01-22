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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <%
                        String pointOfInterestId = request.getParameter("pointOfInterestId");
                        String commentId = request.getParameter("commentId");
                        String text = request.getParameter("text");
                    %>
                    <%
                        String actionUrl = String.format("CommentServlet?pointOfInterestId=%s", pointOfInterestId);
                        if(commentId != null){
                            actionUrl = actionUrl + "&commentId="+commentId;
                        }
                    %>
                    <form action="<%= actionUrl %>" method="POST" class="mt-4">
                        <div class="mb-3">
                            <label class="form-label">Comment</label>
                            <textarea class="form-control" id="text" name="text"><%= text != null ? text : ""%></textarea>
                        </div>
                        <button class="btn btn-primary" type="submit" >Save</button>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
</html>