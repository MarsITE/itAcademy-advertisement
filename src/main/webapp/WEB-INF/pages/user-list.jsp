<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User list</title>

    <script>
        function deleteUser(id) {
            var result = confirm('Do you want to delete user?');
            if (result) {
                var f = document.form;
                f.method = "post";
                f.action = '/deleteUser?userId=' + id;
                f.submit();
            } else {
                return false;
            }
        }
    </script>
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
                <th style="text-align: center;" id="firstName">First Name</th>
                <th style="text-align: center;" id="lastName">Last Name</th>
                <th style="text-align: center;" id="email">Email</th>
                <th style="text-align: center;" id="dateOfBirth">Date Of Birth</th>
                <th style="text-align: center;" id="userRole">Role</th>
                <th style="text-align: center;" id="userStatus">Status</th>
                <c:if test="${sessionScope.currentUser != null}">
                    <th style="text-align: center;" colspan="2" id="action">Actions</th>
                </c:if>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${users}">

                <tr>
                    <td style="text-align: center;"><c:out value="${user.firstName}"/></td>
                    <td style="text-align: center;"><c:out value="${user.lastName}"/></td>
                    <td style="text-align: center;"><c:out value="${user.email}"/></td>
                    <td style="text-align: center;"><c:out value="${user.dateOfBirth}"/></td>
                    <td style="text-align: center;"><c:out value="${user.userRole.name}"/></td>
                    <td style="text-align: center;"><c:out value="${user.userStatus.name}"/></td>

                    <c:if test="${sessionScope.currentUser.userRole.name.equals(\"Admin\")}">

                        <td style="text-align: center;">
                            <div class="btn-group" role="group">
                                <form method="get" action=editUser>
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <button type="submit" class="btn btn-success">Edit</button>
                                </form>
                            </div>
                        </td>
                        <td style="text-align: center;">
                            <div class="btn-group" role="group">
                                <form method="post" action="deleteUser" onsubmit="return deleteUser(${user.id});">
                                    <input type="hidden" name="userId" value="${user.id}">
                                    <button type="submit" class="btn btn-success">Delete</button>
                                </form>
                            </div>
                        </td>

                    </c:if>

                    <c:if test="${!sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
                        <c:if test="${sessionScope.currentUser != null}">
                            <td style="text-align: center;">
                                <c:if test="${sessionScope.currentUser.id == user.id}">
                                    <div class="btn-group" role="group">
                                        <form method="get" action=editUser>
                                            <input type="hidden" name="userId" value="${user.id}">
                                            <button type="submit" class="btn btn-success">Edit</button>
                                        </form>
                                    </div>
                                </c:if>
                            </td>
                            <td style="text-align: center;">
                                <c:if test="${sessionScope.currentUser.id == user.id}">
                                    <div class="btn-group" role="group">
                                        <form method="post" action="deleteUser"
                                              onsubmit="return deleteUser(${user.id});">
                                            <input type="hidden" name="userId" value="${user.id}">
                                            <button type="submit" class="btn btn-success">Delete</button>
                                        </form>
                                    </div>
                                </c:if>
                            </td>
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
