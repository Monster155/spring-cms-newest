<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Editor</title>
    <!-- Include stylesheet -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

    <link href="<c:url value="/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/editor.css"/>" rel="stylesheet">

    <%--    <script>--%>
    <%--        let options = {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric'};--%>

    <%--        function sendData() {--%>
    <%--            $.redirect("${s:mvcUrl('DC#editorPost').build()}", {--%>
    <%--                'id': '10',--%>
    <%--                'name': $("#name-input").val(),--%>
    <%--                'author': $("#author-input").val(),--%>
    <%--                'date': new Date($.now()).toLocaleString('en-GB', options),--%>
    <%--                'text': $(".ql-editor").html(),--%>
    <%--                'tags': '1'--%>
    <%--            });--%>
    <%--        }--%>
    <%--    </script>--%>
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
    <c:if test="${not empty message}">
        <h4>${message}</h4>
    </c:if>
    <c:if test="${not empty duplicateArticle}">
        <h6>${duplicateArticle}</h6>
    </c:if>
    <br>
    <f:form method="POST" action='${s:mvcUrl("DC#editorPost").build()}' modelAttribute="article" id="form">
        <f:label path="name"><s:message code="article-name"/></f:label>
        <f:input path="name"/>
        <f:errors path="name"/>
        <br>
        <f:label path="author"><s:message code="author-name"/></f:label>
        <f:input path="author"/>
        <f:errors path="author"/>
        <br>
        <!-- Create the editor container -->
        <div id="editor">
            <p>Hello World!</p>
            <p>Some initial <strong>bold</strong> text</p>
            <p><br></p>
        </div>
        <f:input path="text" id="text" cssStyle="display: none"/>
        <button type="submit"><s:message code="save-button"/></button>
    </f:form>
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
<!-- Include the Quill library -->
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<!-- Initialize Quill editor -->
<script src="<c:url value="/js/initializeQuillEditor.js"/>"></script>
<!-- Own scripts -->
<script src="<c:url value="/js/jquery.redirect.js"/>"></script>
<script>
    $("#form").on("submit", function () {
        $("#text").val($(".ql-editor").first().html());
    });
</script>
</body>
</html>
