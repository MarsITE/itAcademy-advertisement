<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Adverts Form</title>
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
        </ul>
    </nav>

</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${advert != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${advert == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${advert != null}">
                                Edit Advert
                            </c:if>
                            <c:if test="${advert == null}">
                                Add New Advert
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${advert != null}">
                        <input type="hidden" name="advertId" value="<c:out value='${advert.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Advert Title</label> <input type="text"
                                                           value="<c:out value='${advert.title}' />"
                                                           class="form-control"
                                                           name="title" required="required">
                    </fieldset>

                    <fieldset class="form-control">
                        <label>Advert Description</label> <input type="text"
                                                                 value="<c:out value='${advert.description}' />"
                                                                 class="form-control"
                                                                 name="description" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Advert Publishing Date</label> <input type="text"
                                                                     value="<c:out value='${advert.publishingDate}' />"
                                                                     class="form-control"
                                                                     name="publishingDate" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Advert Ending Date</label> <input type="text"
                                                                 value="<c:out value='${advert.endingDate}' />"
                                                                 class="form-control"
                                                                 name="endingDate" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Advert Genre</label> <input type="text" maxlength="1000"
                                                           value="<c:out value='${advert.advertGenre.name}' />"
                                                           class="form-control"
                                                           name="advertGenre" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>