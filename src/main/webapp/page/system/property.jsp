<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>系统属性</h2>
</div>
<table class="w3-table w3-bordered w3-card-4 w3-xlarge" id="property">
</table>
<script>
    $(function () {
        var data = {
            "url": "SystemInfor",
            "type": "post",
            "dataType": "json",
            "data": null,
            "success": "onLoadSystemInfor"
        };
        publicAjax(data)
    });

    function onLoadSystemInfor(data) {
        for (var title in data) {
            $("#property").append(
                "<tr>" +
                "<td>" + title + "</td>" +
                "<td>" + data[title] + "</td>" +
                "</tr>"
            )
        }
    }
</script>
</body>
</html>
