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
                <div class="col my-4">
                    <h4>Users</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Username</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>                
                            <%  Object usersObject = request.getAttribute("users");
                                if (usersObject != null && usersObject instanceof List) {
                                    List<User> users = (List<User>) usersObject;
                                    for (Iterator<User> it = users.iterator(); it.hasNext();) {
                                        User u = it.next();
                                        %>
                                        <tr>
                                            <td><%= u.getId()%></td>
                                            <td><%= u.getUsername()%></td>
                                            <td>
                                                <div class="d-flex justify-content-end gap-2">
                                                    <a class="btn btn-sm btn-light" href="<%= request.getContextPath().concat(String.format("/User?id=%s", u.getId())) %>">Update</a>
                                                    <a class="btn btn-sm btn-danger" href="<%= request.getContextPath().concat(String.format("/Users?deleteUserId=%s", u.getId())) %>">Delete</a>                                                    
                                                </div>
                                            </td>

                                        </tr>
                                        <%
                                    } // end for
                                } // end if
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
</html>
