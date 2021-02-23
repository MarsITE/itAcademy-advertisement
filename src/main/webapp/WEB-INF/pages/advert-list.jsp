<%@ page import="academy.softserve.model.User" %>
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
        <h3 class="text-center">List of Adverts</h3>
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
    </div>
</div>
</body>
</html>
