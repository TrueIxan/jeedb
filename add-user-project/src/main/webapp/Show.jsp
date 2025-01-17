<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="last.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="5px">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>email</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<User> users = (List<User>) request.getAttribute("users"); // Retrieve users from request
                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                %>
                    <tr>
                        <td><%= user.getUsername() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPassword() %></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="2">No users found in the database.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
</body>
</html>