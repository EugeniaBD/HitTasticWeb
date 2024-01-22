<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <%@include file="head.jsp" %>
    </head>
    <body class="container-fluid">
        <div class="row align-items-center mt-5">
            <div class="col-md-4 offset-md-4 text-center">
                <h2>HitTastic</h4>
                <h4>Register</h4>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-md-4 offset-md-4">
                <form action="${pageContext.request.contextPath}/AuthenticationRegister" method="POST" class="d-grid gap-3">
                    <div class="">
                        <label for="#input-username" class="form-label">Username</label>
                        <input id="input-username" class="form-control" name="username" required>
                    </div>
                    <div class="">
                        <label for="#input-password" class="form-label">Password</label>
                        <input id="input-password" class="form-control" type="password" name="password" required>                        
                    </div>
                    <div class="">
                        <label for="#select-role">Role</label>
                        <select id="select-role" name="role" class="form-select" required>
                            <option value="Admin">Admin</option>
                            <option value="User">User</option>
                        </select>
                    </div>
                    <div class="d-grid gap-2 text-center">
                        <button type="submit" class="btn btn-primary">Register</button>
                        <span>or</span>
                        <p><small>Already have an account? then</small> <a href="login.jsp" class="">Login</a></p>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
