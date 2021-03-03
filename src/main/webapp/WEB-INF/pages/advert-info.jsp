<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Advert info</title>
</head>
<body>

<header>
    <jsp:include page="header.jsp"/>
    <br>
</header>

<div class="container col-md-5">
    <div class="card">
        <div class="card-body">

            <caption>
                <h5 title="Advert title" class="form-group"><c:out value='${advert.title}'/>
                    <br>
                </h5>
            </caption>

            <h7 title="Advert description" class="form-group"><c:out value='${advert.description}'/></h7>
            <br>
            <br>

            <h6 title="Advert duration" class="form-control"><c:out
                    value='from ${advert.publishingDate} to ${advert.endingDate}'/></h6>

            <h6 title="Advert genre" class="form-control"><c:out value='${advert.advertGenre.name}'/></h6>

        </div>
    </div>
</div>
</body>
</html>