<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<header>
    <jsp:include page="header-user.jsp"/>
    <br>
</header>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Adverts</h3>
        <hr>
        <c:if test="${sessionScope.currentUser != null}">
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Advert</a>
            <div class="container text-left">
                </c:if>

                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Publishing date</th>
                        <th>Ending date</th>
                        <th>Genre</th>
                        <th>Author</th>
                        <c:if test="${sessionScope.currentUser != null}">
                            <th>Actions</th>
                        </c:if>
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

                            <c:if test="${sessionScope.currentUser != null}">
                                <td>
                                <c:if test="${sessionScope.currentUser.id == advert.author.id}">

                                    <a href="edit?advertId=<c:out value='${advert.id}' />"> Edit </a>

                                    <a style="margin-left: 20px"
                                       href="delete?advertId=<c:out value='${advert.id}' />">
                                        Delete </a>
                                    </td>
                                </c:if>
                            </c:if>

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