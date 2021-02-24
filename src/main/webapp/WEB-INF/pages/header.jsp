<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adverts</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: tomato">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Adverts</a></li>
            <li><a href="<%=request.getContextPath()%>/userlist"
                   class="nav-link">Users</a></li>

            <c:if test="${sessionScope.currentUser == null}">
                <li><a style="margin-left: 100px" href="<%=request.getContextPath()%>/registration"
                       class="nav-link">Registration</a></li>
                <li><a href="<%=request.getContextPath()%>/signin"
                       class="nav-link">Sign in</a></li>
            </c:if>

            <c:if test="${sessionScope.currentUser != null}">
                <li><a style="margin-left: 100px" href=""
                       class="navbar-brand">${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}</a>
                </li>
                <li></li>
                <li><a href="<%=request.getContextPath()%>/logOut"
                       class="nav-link">Log out</a></li>

            </c:if>

        </ul>

    </nav>
</header>
</body>
</html>
