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

            <caption>
                <h5 title="Advert title" class="form-group"><c:out value='${advert.title}'/>
                    <br>
                </h5>
            </caption>

            <h6 title="Advert description" class="form-group"><c:out value='${advert.description}'/></h6>

            <h6 title="Advert duration" class="form-control"><c:out value='from ${advert.publishingDate} to ${advert.endingDate}'/></h6>

            <h6 title="Advert genre" class="form-control"><c:out value='${advert.advertGenre.name}'/></h6>

        </div>
    </div>
</div>
</body>
</html>