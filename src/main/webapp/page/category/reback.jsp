<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script src="../js/category/recycle.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>类别回收站</h2>
    <button onclick="clearRecycle()" class="w3-btn w3-red w3-large w3-padding w3-medium">清空回收站</button>
</div>
<ul class="w3-ul w3-card-4 w3-margin-8" id="recycleList">
</ul>
<div class="w3-center">
    <ul class="w3-pagination w3-border w3-round">
    </ul>
</div>
</body>
</html>
