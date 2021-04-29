<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Editor</title>
    <!-- Include stylesheet -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

    <link href="<c:url value="/css/menu.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/editor.css"/>" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>

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
    <div id="errorsLabel"></div>
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
        <f:input path="text" cssStyle="display: none"/>
        <button type="submit"><s:message code="save-button"/></button>
    </f:form>
</div>

<!-- Bootstrap script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

<!-- Include the Quill library -->
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<!-- Initialize Quill editor -->
<script src="<c:url value="/js/initializeQuillEditor.js"/>"></script>

<!-- Own scripts -->
<script src="<c:url value="/js/jquery.redirect.js"/>"></script>
<script src="<c:url value="/js/plugin/validationPlugin.js"/>"></script>
<script>

    $("#form").addValidationCheck("${s:mvcUrl("DC#validationCheck").build()}", $("#errorsLabel"));

    $("#form").submit(function () {
        $("#text").val($(".ql-editor").first().html());
    });
</script>
</body>
</html>
