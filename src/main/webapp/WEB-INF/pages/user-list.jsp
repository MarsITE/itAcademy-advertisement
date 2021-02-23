<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<header>
    <jsp:include page="header.jsp"/>
    <br>
</header>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Users</h3>
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
