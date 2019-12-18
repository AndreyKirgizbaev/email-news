<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<head>
    <title>News</title>
</head>


<body>

<div>
    <h1>
        EmailNews
    </h1>

    <div>
        <c:if test="${not empty resultObject}">

            <table>
                <c:forEach var="newsVar" items="#{resultObject}">
                    <tr>
                        <td><b>Author: </b></td>
                        <td><c:out value="${newsVar.newsAuthor}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><h3><c:out value="${newsVar.newsHeadline}"/></h3></td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><c:out value="${newsVar.newsMain}"/></td>
                    </tr>
                    <tr>
                        <td>Date: </td>
                        <td><c:out value="${newsVar.newsDate}"/></td>
                    </tr>
                    <tr><td><br/></td></tr>
                    <tr><td><br/></td></tr>
                    <tr><td><br/></td></tr>
                </c:forEach>
            </table>

        </c:if>
    </div>

    <br/>


    <form name="news" action="/jdbcInsertNews" method="POST">
        <table>
            <tr>
                <td><b>Author: </b></td>
                <td><input type='text' name='newsAuthor' required/></td>
            </tr>
            <tr>
                <td><b>News Headline: </b></td>
                <td><input type='text' name="newsHeadline" required/></td>
            </tr>
            <tr>
                <td><b>News: </b></td>
                <td><input type='text' name="newsMain" required/></td>
            </tr>
        </table>

        <input type="submit" value="Send"/>
    </form>

</div>

</body>

</html>
