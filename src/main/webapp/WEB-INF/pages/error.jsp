<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error</title>
</head>

<header>
    <c:if test="${sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
        <jsp:include page="header.jsp"/>
    </c:if>
    <c:if test="${!sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
        <jsp:include page="header-user.jsp"/>
    </c:if>
    <br>
</header>
<body>
<h3 style="margin-left: 100px" href=""
   class="navbar-brand">${ERROR}</h3>
</body>

</html>

