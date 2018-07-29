
<%@ page contentType="text/html;charset=US-ASCII" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Guest Book</title>
</head>
<body>
    <form method="post">
        <label>Enter message</label>
        <input title="message" name="message" type="text">
        <input title="submit" type="submit">
    </form>
    <table border="1">
        <%--@elvariable id="guestBook" type="java.util.List"--%>
        <thead>
            <tr>
                <td>ID</td><td>POST DATE</td><td>POST MESSAGE</td>
            </tr>
        </thead>
            <tbody>
                <c:forEach var="r" items="${guestBook}">
                    <tr>
                        <td> ${r.recordId}</td><td>${r.postDate}</td><td> ${r.postText}</td>
                    </tr>
                </c:forEach>
            </tbody>
    </table>
</body>
</html>
