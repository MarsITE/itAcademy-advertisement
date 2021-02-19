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
            <li><a href="" class="navbar-brand">${requestScope.user.firstName}</a></li>

        </ul>

    </nav>


</header>
<br>

<div class="row">

    <div class="container">

        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Advert</a>
            <div class="container text-left">

                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Publishing date</th>
                        <th>Ending date</th>
                        <th>Genre</th>
                        <th>Author</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="advert" items="${adverts}">

                        <tr>
                            <td><a title="${advert.description}"
                                   href="advert-info?advertId=<c:out value='${advert.id}' />">
                                <c:out value="${advert.title}"/> </a></td>
                            <td><c:out value="${advert.publishingDate}"/></td>
                            <td><c:out value="${advert.endingDate}"/></td>
                            <td><c:out value="${advert.advertGenre.name}"/></td>
                            <td><c:out value="${advert.author.email}"/></td>
                            <td>
                                <a href="edit?advertId=<c:out value='${advert.id}' />"> Edit </a>

                                <a style="margin-left: 20px" href="delete?advertId=<c:out value='${advert.id}' />">
                                    Delete </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
</body>
</html>
