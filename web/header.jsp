<%@page import="com.model.Role"%>
<%@page import="com.model.User"%>
<style>
    .navbar {
        background-color: #00cccc; /* Light blue */
        color: black; /* Set the text color to black */
    }

    .navbar a {
        color: black; /* Set the text color for navigation links to black */
    }
</style>

<nav class="navbar navbar-expand bg-very-light-green">
    <div class="container-fluid">
        <a href="<%= request.getContextPath()%>" class="navbar-brand">
            Dasboard
        </a>
        <div class="nav">
            <%
                Object userObject = request.getSession().getAttribute("user");
                if (userObject != null && userObject instanceof User) {
                    User user = (User) userObject;

                    if(user.getRole().equals(Role.Admin)){
                        %>
                        <a href="<%= request.getContextPath().concat("/pointofinterestform.jsp") %>" class="nav-link">
                            Add Point of Interest
                        </a>
                        <a href="<%= request.getContextPath().concat("/Users") %>" class="nav-link">
                            Users
                        </a>

                        <%
                    }
                    %>
                    <a href="#" class="nav-link">
                        Welcome <%= user.getUsername()%> (logged as  <%= user.getRole()%>)
                    </a>
                    <a href="<%= request.getContextPath() + "/AuthenticationLogout"%>" class="nav-link">
                        Logout
                    </a>
                    <%
                }
            %>
        </div>
    </div>
</nav>