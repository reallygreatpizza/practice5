<!DOCTYPE html>
<html>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>

<a href="revController">REVIEWERS ONLY</a>

<table>
    
    <tr>
        <th>title</th>
        <th>director</th>
        <th>year</th>
        <th>rating</th>
        <th>delete</th>
    </tr>
    
<c:forEach var="review" items="${reviews}">
    <tr>
        <td>${movies[review.value.movieID].title}</td>
        <td>${movies[review.value.movieID].director}</td>
        <td>${movies[review.value.movieID].year}</td>
        <td>${review.value.rating}</td>
        <td>
            <form action="revController" method="post">
                <input type="hidden" name="action" value="deleteReview">
                <input type="hidden" name="ratingID" value="${review.value.ratingID}">
                <input type="submit" value="delete">
 
            </form>
        </td>
    </tr>
</c:forEach>
    
</table>

</html>
