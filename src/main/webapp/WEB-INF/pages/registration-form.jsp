<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>

<header>
    <jsp:include page="header-user.jsp"/>
    <br>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <c:if test="${user != null}">
            <form action="updateUser" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="addUser" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Registration
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="userId" value="<c:out value='${user.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>First Name</label> <input type="text"
                                                         value="<c:out value='${user.firstName}' />"
                                                         class="form-control"
                                                         name="firstName" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Last Name</label> <input type="text"
                                                        value="<c:out value='${user.lastName}' />"
                                                        class="form-control"
                                                        name="lastName" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="text"
                                                       value="<c:out value='${user.password}' />"
                                                       class="form-control"
                                                       name="password" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Date Of Birth</label> <input type="text"
                                                            value="<c:out value='${user.dateOfBirth}' />"
                                                            class="form-control"
                                                            name="dateOfBirth" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label> <input type="text"
                                                    value="<c:out value='${user.email}' />"
                                                    class="form-control"
                                                    name="email" required="required">
                    </fieldset>

                    <c:if test="${user != null}">

                        <fieldset class="form-group">
                            <label>User role</label> <input type="text"
                                                            value="<c:out value='${user.userRole.name}' />"
                                                            class="form-control"
                                                            name="userRole" required="required">
                        </fieldset>

                    </c:if>


                    <button type="submit" class="btn btn-success">Save</button>
                </form>

        </div>
    </div>
</div>
</body>
</html>
