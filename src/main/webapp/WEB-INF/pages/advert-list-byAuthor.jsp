<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advert list</title>
</head>

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
                    <caption hidden>List of adverts</caption>
                    <thead>
                    <tr>
                        <th id="title">Title</th>
                        <th id="publishingDate">Publishing date</th>
                        <th id="endingDate">Ending date</th>
                        <th id="genre">Genre</th>
                        <th id="author">Author</th>
                        <th id="action">Actions</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="advert" items="${advertsById}">

                        <tr>
                            <td><a title="${advert.description}"
                                   href="advert-info?advertId=<c:out value='${advert.id}' />">
                                <c:out value="${advert.title}"/> </a></td>
                            <td><c:out value="${advert.publishingDate}"/></td>
                            <td><c:out value="${advert.endingDate}"/></td>
                            <td><a href="advertGenre?advertGenre=<c:out value='${advert.advertGenre.name}' />">
                                <c:out value="${advert.advertGenre.name}"/> </a>
                            </td>
                            <td><a title="${advert.author.email}"
                                   href="listByAuthor?authorId=<c:out value='${advert.author.id}' />">
                                <c:out value="${advert.author.firstName} ${advert.author.lastName}"/> </a>
                            </td>
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
