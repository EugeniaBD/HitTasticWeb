<%@page import="com.model.User"%>
<%@page import="com.model.Role"%>
<%@page import="com.model.PointOfInterest"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Object userObject2 = session.getAttribute("user");
    User user = null;
    if(userObject2 != null && userObject2 instanceof User) {
        user = (User) userObject2;
    }
    
    Object pointOfInterestsObject = request.getAttribute("pointOfInterests");
    if (pointOfInterestsObject != null) {
        List<PointOfInterest> pointOfInterests = (ArrayList<PointOfInterest>) pointOfInterestsObject;
%>
<table class="table">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type</th>
            <th>Borough</th>
            <th>Location</th>
            <th>Like</th>
            <th>Comments</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <%
            Iterator<PointOfInterest> pointOfInterestsIterator = pointOfInterests.iterator();
            while (pointOfInterestsIterator.hasNext()) {
                PointOfInterest poi = pointOfInterestsIterator.next();
        %>
        <tr>
            <td><%= poi.getId()%></td>
            <td><%= poi.getName()%></td>
            <td><%= poi.getType()%></td>
            <td><%= poi.getBorough()%></td>
            <td><%= poi.getLocation()%></td>
            <td>
                <%= poi.getLike() ? "Liked" : "Not Liked"%>
            </td>
            <td><%= poi.getCommentsCount()%></td>
            <td class="d-flex gap-1 justify-content-end">
                <a class="btn btn-light btn-sm" href="PointOfInterestServlet?id=<%= poi.getId()%>">
                    View
                </a>
                <% if(user != null && user.getRole().equals(Role.Admin)){ %>
                <a class="btn btn-danger btn-sm" href="PointOfInterestsServlet?id=<%= poi.getId()%>&action=delete&searchType=<%= request.getParameter("searchType") %>&searchByType=<%= request.getParameter("searchByType") %>">
                    Delete
                </a>
                <a class="btn btn-primary btn-sm" href="PointOfInterestServlet?id=<%= poi.getId()%>&action=update">
                    Update
                </a>
                <% } %>
            </td>
        </tr>            
        <%     } // end whilte
        %>
    </tbody>
</table>
<%
    } else {
        out.write("Nothing Found");
    }
%>
