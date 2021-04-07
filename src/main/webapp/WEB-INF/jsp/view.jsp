<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>View</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="<c:url value="/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/view.css"/>" rel="stylesheet">
    <script>
        function linkToPage(params) {
            window.location.replace("${s:mvcUrl('DC#pageGet').build()}?" + params);
        }
    </script>
</head>
<body>
<div id="main">
    <header id="menu">
        <a href="${s:mvcUrl("DC#index").build()}" id="logo"><img src="<c:url value="/images/logo.jpg"/>"></a>
        <button id="create-button" type="button"><s:message code="button.create"/></button>
        <!-- free space -->
        <a href='${s:mvcUrl("DC#change").arg(0, "ru-RU").build()}'>Рус</a>
        <a href='${s:mvcUrl("DC#change").arg(0, "en-US").build()}'>Eng</a>
        <%--TODO add Search--%>
        <a href="https://id.twitch.tv/oauth2/authorize?client_id=vzd9tewc0bd9z4pbjy0gmcwlfeqcmk&redirect_uri=https://spring-cms-dajjsand.herokuapp.com/twitch_access&response_type=code&scope=user:read:email">Login</a>
    </header>
    <div id="center">
        <c:if test="${not empty message}">
            <h4>${message}</h4>
        </c:if>
        <c:if test="${not empty allPages}">
            <div id="content">
                <c:forEach items="${allPages}" var="user" varStatus="loop">
                    <button class='blog' onclick="linkToPage('name=${user.name}&author=${user.author}')">
                        <div class="name">
                                ${user.name}
                        </div>
                        <div class="author">
                                ${user.author}
                        </div>
                    </button>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty allPages}">
            <h1><s:message code="view.sorry"/></h1>
        </c:if>
    </div>
    <footer>
        <c:if test="${pagesCount < 1}">
            <button class="page-button"
                    onclick="window.location.replace('${s:mvcUrl("DC#index").arg(0, "1").arg(1, limit).build()}');">1
            </button>
        </c:if>
        <c:if test="${pagesCount > 0}">
            <c:forEach begin="0" end="${pagesCount - 1}" step="1" varStatus="loop">
                <button class="page-button"
                        onclick="window.location.replace('${s:mvcUrl("DC#index").arg(0, loop.count).arg(1, limit).build()}');">
                    <c:out value="${loop.count}"/>
                </button>
            </c:forEach>
        </c:if>

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

<!-- Own scripts -->
<script>
    $("#create-button").click(function () {
        window.location.replace("${s:mvcUrl("DC#editorGet").build()}");
    });
</script>
</body>
</html>
