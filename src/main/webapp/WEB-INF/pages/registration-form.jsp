<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration form</title>

    <script>

        const namePattern = /^[A-Za-z]{2,15}/;

        function validateUser() {
            return isNameValid() && isSurnameValid() && isEmailValid()
        }

        function isNameValid() {
            const firstname = document.getElementById("firstName").value;
            const errorFirstname = document.getElementById("errorFirstName");
            var isValid = false;
            if (!namePattern.test(firstname)) {
                errorFirstname.innerHTML = "First name must be between 2 and 15 characters";
                isValid = false;
            } else {
                errorFirstname.innerHTML = "";
                isValid = true;
            }
            return isValid;
        }

        function isSurnameValid() {
            const lastname = document.getElementById("lastName").value;
            const errorLastName = document.getElementById("errorLastName");
            var isValid = false;
            if (!namePattern.test(lastname)) {
                errorLastName.innerHTML = "Last name must be between 2 and 15 characters";
                isValid = false;
            } else {
                errorLastName.innerHTML = "";
                isValid = true;
            }
            return isValid;
        }

        function isEmailValid() {
            const email = document.getElementById("email").value;
            const errorEmail = document.getElementById("errorEmail");
            const patternIsEmail = /\S+@\S+\.\S+/;
            var isValid = false;
            if (!patternIsEmail.test(email)) {
                errorEmail.innerHTML = "Email should be like - example@mail.com"
                isValid = false;
            } else {
                errorEmail.innerHTML = "";
                isValid = true;
            }
            return isValid;
        }

    </script>
</head>

<body>

<header>
    <jsp:include page="header.jsp"/>
    <br>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <c:if test="${user != null}">
            <form action="updateUser" method="post" onsubmit="return validateUser()">
                </c:if>
                <c:if test="${user == null}">
                <form action="addUser" method="post" onsubmit="return validateUser()">
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
                                                         class="form-control" id="firstName"
                                                         name="firstName" required="required">
                        <span class="error-firstname" id="errorFirstName"></span>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Last Name</label> <input type="text"
                                                        value="<c:out value='${user.lastName}' />"
                                                        class="form-control" id="lastName"
                                                        name="lastName" required="required">
                        <span class="error-lastname" id="errorLastName"></span>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="password"
                                                       value="<c:out value='${user.password}' />"
                                                       class="form-control" id="password"
                                                       name="password" required="required">
                        <span class="error-password" id="errorPassword"></span>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Date Of Birth</label> <input type="date"
                                                            value="<c:out value='${user.dateOfBirth}' />"
                                                            class="form-control" id="dateOfBirth"
                                                            name="dateOfBirth" required="required">
                        <span class="error-dob" id="errorDob"></span>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email</label> <input type="text"
                                                    value="<c:out value='${user.email}' />"
                                                    class="form-control" id="email"
                                                    name="email" required="required">
                        <span class="error-email" id="errorEmail"></span>
                    </fieldset>

                    <c:if test="${user != null}">
                        <fieldset class="form-group">
                            <label>User role</label> <input title="User, Admin, Anonymous" type="text"
                                                            value="<c:out value='${user.userRole.name}' />"
                                                            class="form-control" id="userRole"
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
