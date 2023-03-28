<!DOCTYPE html>
<html>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>

<a href="Public">View All Movies</a>
<a href="reviews/revController">REVIEWERS</a>
<a href="admin/adminController">ADMINS</a>
<br>
<a href="Public?action=regForm">Register a User</a>

<br>
<br>

<pre>
credentials for reviewer account:
billy
notpassword

admin account:
andrea
password123
</pre>



<table>
    
    <tr>
        <th>title</th>
        <th>director</th>
        <th>year</th>
    </tr>
<c:forEach var="movie" items="${movies}">
    <tr>
        <td>${movie.value.title}</td>
        <td>${movie.value.director}</td>
        <td>${movie.value.year}</td>
    </tr>
</c:forEach>
    
</table>

</html>
