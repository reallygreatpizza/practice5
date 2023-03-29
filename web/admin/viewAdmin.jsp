<%-- 
    Document   : viewAdmin
    Created on : Mar 27, 2023, 7:17:52 PM
    Author     : cb717546
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    
        <a href="Public">View All Movies</a>
        <a href="reviews/revController">REVIEWERS</a>
        <a href="admin/adminController">ADMINS</a>
        
    <body>
        <table>
          <thead>
            <tr>
              <th>User ID</th>
              <th>Email</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Username</th>
              <th>Roles</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="user" items="${users}">
              <tr>
                <td>${user.userID}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.username}</td>
                <td>
                  <c:forEach var="role" items="${user.roles}">
                    ${role}
                  </c:forEach>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

    </body>
</html>
