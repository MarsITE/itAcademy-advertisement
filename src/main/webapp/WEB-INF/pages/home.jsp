<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Home</title>

    <style>
        body {
            width: 100%;
            margin: auto;
            max-width: 1400px;
        }
        h3 {
            display: block;
            background-color: darkgrey;
            padding-top: 50px;
            padding-bottom: 50px;
            height: fit-content;
        }
        table {
            counter-reset: rowNumber;
            width: fit-content;
        }

        table tr td:first-child::before {
            counter-increment: rowNumber;
            content: counter(rowNumber);
        }
        .inner {
            width: 700px;
        }
        span {
            margin-left: 40px;
            margin-right: 40px;
        }
        .col-md-4 .custom-file-input {
            margin-left: 200px;
            padding: 15px;
            width: 1000px;
        }
        #uploadFile {
            margin-left: 30px;
            cursor: pointer;
        }

    </style>
</head>

<body>
<h3 align="center">Adverts in Database</h3>
<div class="input-group mb-3">
    <a class="btn btn-info" href="${pageContext.request.contextPath}/advert?action=create" role="button">Create new advert</a>
</div>
<div style="display: none" id = "ajaxMessage"></div>
<table class="table table-striped table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Publishing date</th>
        <th scope="col">Ending date</th>
        <th scope="col">Genre
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Filter by</button>
            <ul class="dropdown-menu">
                <li><a href="/">All</a></li>
                <c:forEach var="genre" items="${genres}">
                    <li><a href="/?genreId=${genre.name}">${genre.name}</a></li>
                </c:forEach>
            </ul>
        </th>
        <th scope="col" colspan="3" align="center" width="fit-content">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="advert" items="${adverts}">
        <tr id="advertRow${advert.id}">
            <td ></td>
            <td>${advert.title}</td>
            <td>${advert.publishingDate}</td>
            <td>${advert.endingDate}</td>
            <td>${advert.advertGenre.name}</td>
            <td><a class="btn btn-info" href="${pageContext.request.contextPath}/advert?advertId=${advert.id}" role="button">Full info</a></td>
            <td><a class="btn btn-primary" href="${pageContext.request.contextPath}/advert?action=update&advertId=${advert.id}" role="button">Edit</a></td>
            <td>
                <a class="delete btn btn-danger" href="${pageContext.request.contextPath}/advert/delete?advertId=${advert.id}" role="button">Delete</a>
            </td>
        </tr>
    </c:forEach>



    </tbody>
</table>

</body>
</html>
