<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in form</title>
</head>

<body>

<header>
    <jsp:include page="header.jsp"/>
    <br>
</header>

<div class="login-container" align="center">
    <h1 class="label-sign-in">Log in</h1>
    <br>
    <form class="form-login" method="post" action="login">
        <div class="container">
            <div class="sign-up-content">
                <div class="form-group">
                    <label for="inputEmail" class="form-label">Please, enter your email address</label>
                    <br>
                    <input type="email" class="form-group" id="inputEmail" name="username">
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="form-label">Enter password</label>
                    <br>
                    <input type="password" id="inputPassword" class="form-group" name="password">
                </div>
                <div class="btn-login">
                    <button type="submit" class="btn btn-primary">Log in</button>
                </div>
                <div>
                    <p class="registration-here">Haven't account yet?<a href="registration"
                                                                        class="registration-here-link"> Sign up </a>
                    </p>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>