<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link href="<c:url value="/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/page.css"/>" rel="stylesheet">
</head>
<body>
<div id="content">
    <div id="menu">
        <a href="${s:mvcUrl("DC#index").build()}" id="logo"><img src="<c:url value="/images/logo.jpg"/>"></a>
        <button id="create-button" type="button"><s:message code="button.create"/></button>
        <!-- free space -->
        <a href='${s:mvcUrl("DC#change").arg(0, "ru-RU").build()}'>Рус</a>
        <a href='${s:mvcUrl("DC#change").arg(0, "en-US").build()}'>Eng</a>
        <%--TODO add Search--%>
    </div>

    <div id="text">
        ${text}
    </div>
    <footer>
        <div id="author">${author}</div>
        -
        <div id="date">${date}</div>
    </footer>
</div>

<!-- Bootstrap scripts -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
