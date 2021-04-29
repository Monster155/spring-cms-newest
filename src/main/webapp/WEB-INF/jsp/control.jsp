<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
    <title>Control Panel</title>
    <link href="<c:url value="/css/control.css"/>" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>

<button id="reload-btn">Load Data</button>

<div id="table">
    <table>
        <thead>
        <tr>
            <th class="td">Name</th>
            <th class="td">Author</th>
            <th class="td">Date</th>
            <th class="td">Text</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>

<%--<div style="height: 2000px; background-color: red;"></div>--%>

<!-- Bootstrap script -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>

<script src="<c:url value='/js/plugin/validationPlugin.js'/>"></script>
<script>
    const table = $("#table");
    table.hide();

    $('#reload-btn').click(function () {
        const button = $(this);
        $.ajax({
            url: '${s:mvcUrl("AC#getTable").build()}0',
            method: 'GET',
            cache: false,
            type: "text/json",

            success: function (res) {
                table.show();
                console.log(res);
                setTimeout(function () {
                    const tbody = $("#tbody");
                    tbody.html(getDataFromResponse(res));
                }, 1000);
            },
            error: function (result) {
                alert('Error : Failed to reach API Url or check your connection');
            }
        })
            .always(function () {
                button.html('Loading Data...');
            })
            .then(function (evt) {
                setTimeout(function () {
                    button.html('Reload Data');
                }, 1000);
            });
    });

    function removeArticle(id) {

        $.ajax({
            url: '${s:mvcUrl("AC#post").build()}' + id,
            method: 'POST',
            // data: {
            //     id: id
            // },

            success: function (result) {
                // alert("Article removed successfully");
                let row = "#table-row-" + id;
                $(row).hide('slow', function () {
                    $(row).remove();
                });
            },
            error: function (result) {
                alert("Error : Failed to reach API Url or article doesn't exist");
            }
        });
    }

    $(document).ready(function () {
        let win = $(window);
        let i = 1;
        let isWaiting = false;
        win.scroll(function () {
            if (win.scrollTop() + win.height() >= $('body').height() - 100) {
                if (!isWaiting) {
                    isWaiting = true;
                    $.ajax({
                        url: '${s:mvcUrl("AC#getTable").build()}' + i,
                        method: 'GET',
                        cache: false,
                        type: "text/json",

                        success: function (res, textStatus, xhr) {
                            console.log(textStatus);
                            if (xhr.status === 200) {
                                i++;
                                console.log(res);
                                const tbody = $("#tbody");
                                tbody.append(getDataFromResponse(res));
                            }
                        },

                        error: function (result) {
                            alert('Error : Failed to reach API Url or check your connection');
                        }
                    })
                        .always(function () {
                            // button.html('Loading Data...');
                            // show loading preview
                        })
                        .then(function () {
                            isWaiting = false;
                        })
                }
            }
        });
    });

    function getDataFromResponse(res) {
        const result = JSON.parse(JSON.stringify(res));
        let html = "";
        for (let i = 0; i < result.length; i++) {
            html += '<tr id="table-row-' + result[i].id + '">'
                + '<td class="td-name td">' + result[i].name + '</td>'
                + '<td class="td-author td">' + result[i].author + '</td>'
                + '<td class="td-date td">' + new Date(result[i].date) + '</td>'
                + '<td class="td-text td">' + result[i].text.replaceAll("<", "&lt;").replaceAll(">", "&rt;") + '</td>'
                + '<td class="td-delete-btn td">'
                + '<button id="delete-btn" onclick="removeArticle(' + result[i].id + ')">Delete</button>'
                + '</td>'
                + '</tr>';
        }
        return html;
    }
</script>
</body>
</html>
