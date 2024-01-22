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
            <%
                Object userObject2 = session.getAttribute("user");
                User user = null;
                if(userObject2 != null && userObject2 instanceof User) {
                    user = (User) userObject2;
                }

                Object poiObject = request.getAttribute("pointOfInterest");
                if (poiObject != null && poiObject instanceof PointOfInterest) {
                    PointOfInterest poi = (PointOfInterest) poiObject;
            %>
            <div class="row">
                <div class="col-12">
                    <div class="card mt-4">
                        <div class="card-body">
                            <small class="card-subtitle"><%= poi.getId()%></small>
                            <h4 class="card-title"><%= poi.getName()%></h4>
                            <strong class="card-subtitle"><%= poi.getType()%></strong>
                            <small>
                                <p class="card-text"><%= poi.getLocation()%></p>
                            </small>
                            <% if (poi.getDescription() != null) { %>
                                <p class="card-text"><%= poi.getDescription()%></p>
                            <%}%>
                            <div class="d-flex align-items-center gap-2 mt-2">
                                <% if (poi.getLike()) { %>
                                    Liked
                                <%} else {%>
                                    <a class="btn btn-primary btn-sm" href="PointOfInterestServlet?id=<%= poi.getId()%>&action=like">Like</a>
                                <%}%>
                                <% if(user != null && user.getRole().equals(Role.Admin)) { %>
                                <a class="btn btn-primary btn-sm" href="PointOfInterestServlet?id=<%= poi.getId()%>&action=update">Update</a>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row my-3">
                <div class="col-12 d-grid gap-2">
                    <div class="d-flex justify-content-between">
                        <h4>Comments <span class="badge badge-primary"><%= poi.getCommentsCount()%></span></h4>
                        <a class="btn btn-primary" href="comment.jsp?pointOfInterestId=<%=poi.getId()%>">Add New Comment</a>                        
                    </div>
                    <%
                        for (Iterator<Comment> it = poi.getComments().iterator(); it.hasNext();) {
                            Comment comment = it.next();
                    %>
                    <div class="card">
                        <div class="card-body">
                            <p><%= comment.getText()%></p>
                            <% if(user != null && user.getRole().equals(Role.Admin)) { %>
                                <a class="btn btn-light btn-sm" href="CommentServlet?pointOfInterestId=<%=poi.getId()%>&commentId=<%=comment.getId()%>&action=authorise">
                                    <% if (comment.getAuthorise()) {
                                        out.print("Unauthorise");
                                    } else {
                                        out.print("Authorise");
                                    } %>
                                </a>
                                <a class="btn btn-danger btn-sm" href="CommentServlet?pointOfInterestId=<%=poi.getId()%>&commentId=<%=comment.getId()%>&action=delete">Delete</a>
                                <a class="btn btn-primary btn-sm" href="comment.jsp?pointOfInterestId=<%=poi.getId()%>&commentId=<%=comment.getId()%>&text=<%=comment.getText()%>">Update</a>
                            <% } %>
                                                            
                        </div>
                    </div>
                     <%
                         }
                     %>
                </div>
            </div>
            <%
                }// end of if
            %>
        </div>

    </body>
</html>