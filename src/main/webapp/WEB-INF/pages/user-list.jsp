<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <li><a style="margin-left: 100px" href="<%=request.getContextPath()%>/registration"
                   class="nav-link">Registration</a></li>
            <li><a href="<%=request.getContextPath()%>/signin"
                   class="nav-link">Sign in</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Adverts</h3>
        <hr>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Date Of Birth</th>
                <th>Role</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${users}">

                <tr>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.dateOfBirth}"/></td>
                    <td><c:out value="${user.userRole.name}"/></td>
                    <td><c:out value="${user.userStatus.name}"/></td>
                    <td>
                        <a href="editUser?userId=<c:out value='${user.id}' />"> Edit </a>

                        <a style="margin-left: 20px" href="deleteUser?userId=<c:out value='${user.id}' />"> Delete </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
