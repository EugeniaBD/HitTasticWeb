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
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    <% 
                        Object poiObject = request.getAttribute("pointOfInterest");
                        PointOfInterest poi = null;
                        if(poiObject != null && poiObject instanceof PointOfInterest){
                            poi = (PointOfInterest) poiObject;
                        }
                    %>
                    <form action="<%= request.getContextPath().concat("/PointOfInterestServlet")%>" method="POST" class="mt-4">
                        <% if(poi != null) { %>
                            <input type="hidden" name="id" value="<%= poi.getId() %>">
                        <% } %>
                        
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input class="form-control" name="name" value="<%= (poi != null && poi.getName() != null) ? poi.getName():"" %>" required />                            
                        </div>
                            <div class="mb-3">
                                <label class="form-label">Borough</label>
                                <input class="form-control" name="borough" value="<%= (poi != null && poi.getBorough() != null) ? poi.getBorough() : ""%>" required/>
                            </div>
                        <div class="mb-3">
                            <label class="form-label">Location</label>
                            <input class="form-control" name="location" value="<%= (poi != null && poi.getLocation() != null) ? poi.getLocation():"" %>" required/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <textarea class="form-control" name="description" required><%= (poi != null && poi.getDescription() != null) ? poi.getDescription():"" %></textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="type">Type</label>
                            <select class="form-select" id="type" name="type" required>
                                <%
                                    PointOfInterestType[] options = PointOfInterestType.values();
                                    int i = 0;
                                %>
                                <% for (i = 0; i < options.length; i++) {%>
                                    <option value="<%= options[i]%>" <%= (poi != null && poi.getType() != null && poi.getType().name().equals(request.getAttribute("searchByType"))) ? "selected" : ""%> ><%= options[i].name()%></option>
                                <% } // end of for %>
                            </select>    
                        </div>
                        <button class="btn btn-light" type="button" onclick="history.back()">Cancel</button>
                        <button class="btn btn-primary" type="submit">Save</button>
                    </form>                    
                </div>
            </div>
        </div>
    
    </body>
</html>
