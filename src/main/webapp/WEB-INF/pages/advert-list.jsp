<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advert list</title>
    <script>
        function deleteAdvert(id) {
            var result = confirm('Do you want to delete advert?');
            if (result) {
                var f = document.form;
                f.method = "post";
                f.action = '/delete?advertId=' + id;
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
        <h3 class="text-center">List of Adverts</h3>
        <hr>
        <c:if test="${sessionScope.currentUser != null}">
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Advert</a>
            <div class="container text-left">
                </c:if>
                <br>
                <table class="table table-bordered">
                    <caption hidden>List of adverts</caption>
                    <thead>
                    <tr>
                        <th style="text-align: center;" id="title">Title</th>
                        <th style="text-align: center;" id="publishingDate">Publishing date</th>
                        <th style="text-align: center;" id="endingDate">Ending date</th>
                        <th style="text-align: center;" id="genre">Genre</th>
                        <th style="text-align: center;" id="author">Author</th>
                        <c:if test="${sessionScope.currentUser != null}">
                            <th style="text-align: center;" colspan="2" id="action">Actions</th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="advert" items="${adverts}">

                        <tr>
                            <td style="text-align: center;"><a title="${advert.description}"
                                   href="advert-info?advertId=<c:out value='${advert.id}' />">
                                <c:out value="${advert.title}"/> </a></td>
                            <td style="text-align: center;"><c:out value="${advert.publishingDate}"/></td>
                            <td style="text-align: center;"><c:out value="${advert.endingDate}"/></td>

                            <td style="text-align: center;"><a href="advertGenre?advertGenre=<c:out value='${advert.advertGenre.name}' />">
                                <c:out value="${advert.advertGenre.name}"/> </a>
                            </td>

                            <td style="text-align: center;"><a title="${advert.author.email}"
                                   href="listByAuthor?authorId=<c:out value='${advert.author.id}' />">
                                <c:out value="${advert.author.firstName} ${advert.author.lastName}"/> </a>
                            </td>

                            <c:if test="${sessionScope.currentUser.userRole.name.equals(\"Admin\")}">

                                <td style="text-align: center;">
                                    <div class="btn-group" role="group">
                                        <form method="get" action=edit>
                                            <input type="hidden" name="advertId" value="${advert.id}">
                                            <button type="submit" class="btn btn-success">Edit</button>
                                        </form>
                                    </div>
                                </td>
                                <td style="text-align: center;">
                                    <div class="btn-group" role="group">
                                        <form method="post" action="delete" onsubmit="return deleteAdvert(${advert.id});">
                                            <input type="hidden" name="advertId" value="${advert.id}">
                                            <button type="submit" class="btn btn-success">Delete</button>
                                        </form>
                                    </div>
                                </td>

                            </c:if>

                            <c:if test="${!sessionScope.currentUser.userRole.name.equals(\"Admin\")}">
                                <c:if test="${sessionScope.currentUser != null}">
                                    <td style="text-align: center;">
                                        <c:if test="${sessionScope.currentUser.id == advert.author.id}">
                                            <div class="btn-group" role="group">
                                                <form method="get" action=edit>
                                                    <input type="hidden" name="advertId" value="${advert.id}">
                                                    <button type="submit" class="btn btn-success">Edit</button>
                                                </form>
                                            </div>
                                        </c:if>
                                    </td>
                                    <td style="text-align: center;">
                                        <c:if test="${sessionScope.currentUser.id == advert.author.id}">
                                            <div class="btn-group" role="group">
                                                <form method="post" action="delete"
                                                      onsubmit="return deleteAdvert(${advert.id});">
                                                    <input type="hidden" name="advertId" value="${advert.id}">
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
    </div>
</div>
</body>
</html>
