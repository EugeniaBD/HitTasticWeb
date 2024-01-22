<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="head.jsp" %>
        
    </head>
    <body class="container-fluid">
        <div class="row align-items-center mt-5">
            <div class="col-md-4 offset-md-4 text-center">
                <h2>HitTastic</h4>
                <h4>Login</h4>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-md-4 offset-md-4">
                <% if(request.getAttribute("error") != null){
                    %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("error") %>
                    </div>
                    <%                    
                } %>
                <form action="${pageContext.request.contextPath}/AuthenticationLogin" method="POST" class="d-grid gap-3">
                    <div class="">
                        <label class="form-label">Username</label>
                        <input name="username" class="form-control" required>
                    </div>
                    <div class="">
                        <label class="form-label">Password</label>
                        <input type="password" name="password" class="form-control" required>                        
                    </div>
                    <div class="d-grid gap-2 text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                        <span>or</span>
                        <p><small>Create a new account</small> <a href="register.jsp">Register</a></p>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
