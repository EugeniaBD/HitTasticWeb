<%@page import="com.enums.PointOfInterestType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="row">
        <div class="col my-4">
            <h4>Search Point of Interest</h4>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <%
                String searchType = request.getParameter("searchType");
                if (searchType == null || searchType.isEmpty()) {
                    searchType = "location";
                }
            %>
            <form action="PointOfInterestsServlet" class="row row-cols-lg-auto g-3 align-items-center">
                <div class="col-12">
                    <label class="form-check-label form-label">Search By:   </label>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" group="searchType" name="searchType" value="location" <%= "location".equals(searchType) ? "checked" : ""%> onchange="this.form.submit()">
                        <label class="form-check-label form-label">Location</label>
                    </div>

                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" group="searchType" name="searchType" value="type" <%= "type".equals(searchType) ? "checked" : ""%>  onchange="this.form.submit()">
                        <label class="form-check-label">Type</label>
                    </div>
                </div>

                <% if (searchType.equals("location")) {
                        String searchByLocation = "";
                        if (request.getAttribute("searchByLocation") != null)
                            searchByLocation = (String) request.getAttribute("searchByLocation");
                %>
                <div class="col-12">
                    <input class="form-control" id="search-by-location" name="searchByLocation" value="<%= searchByLocation%>" placeholder="Enter Location"/>
                </div>
                <% } //end of if(type == "location") %>
                <% if(searchType.equals("type")) { %>
                <div class="col-12">
                    <select id="search-by-type" name="searchByType" class="form-select">
                        <option value>Select Location Type</option>
                        <% 
                            PointOfInterestType[] options = PointOfInterestType.values();
                            int i = 0;
                        %>
                        <% for (i = 0; i < options.length; i++) {%>
                        <option value="<%= options[i]%>" <%= options[i].name().equals(request.getAttribute("searchByType")) ? "selected" : ""%> ><%= options[i].name()%></option>
                        <% } // end of for %>
                    </select>                        
                </div>
                <% } //end of if(type == "type") %>
                <button class="btn btn-primary" type="submit">Search</button>
            </form>
        </div>
    </div>
</div>

    <div class="row">
        <div class="col-12">
            <% if(request.getAttribute("pointOfInterests") != null ) { %>
            <%@include file="searchList.jsp" %>
            <% }%>
        </div>
    </div>
</div>
