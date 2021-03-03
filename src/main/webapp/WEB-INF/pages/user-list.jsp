<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User list</title>
</head>

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
            <caption hidden> User list</caption>
            <thead>
            <tr>
                <th id="firstName">First Name</th>
                <th id="lastName">Last Name</th>
                <th id="email">Email</th>
                <th id="dateOfBirth">Date Of Birth</th>
                <th id="userRole">Role</th>
                <th id="userStatus">Status</th>
                <c:if test="${sessionScope.currentUser != null}">
                    <th id="action">Actions</th>
                </c:if>
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

                    <c:if test="${sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
                    <td>
                        <a href="editUser?userId=<c:out value='${user.id}' />"> Edit </a>

                        <a style="margin-left: 20px" href="deleteUser?userId=<c:out value='${user.id}' />">
                            Delete </a>
                    </td>
                    </c:if>

                    <c:if test="${!sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
                        <c:if test="${sessionScope.currentUser != null}">
                            <td>
                            <c:if test="${sessionScope.currentUser.id == user.id}">
                                <a href="editUser?userId=<c:out value='${user.id}' />"> Edit </a>

                                <a style="margin-left: 20px" href="deleteUser?userId=<c:out value='${user.id}' />">
                                    Delete </a>
                                </td>
                            </c:if>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
