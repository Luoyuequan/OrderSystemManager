<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/goods/manage.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>菜品列表</h2>
</div>
<table class="w3-table w3-striped w3-bordered w3-card-4" id="goodsList">
    <thead>
    <tr class="w3-blue">
        <th>菜名</th>
        <th>分类</th>
        <th>单价</th>
        <th>菜品图片</th>
        <th>菜品描述</th>
        <th>适合人群</th>
        <th>操作</th>
    </tr>
    </thead>
</table>
<div class="w3-center">
    <ul class="w3-pagination w3-border w3-round">
    </ul>
</div>
<div id="goodsImg" class="w3-modal" onclick="this.style.display='none'">
    <span class="w3-closebtn w3-hover-red w3-container w3-padding-16 w3-display-topright">&times;</span>
    <div class="w3-modal-content w3-animate-zoom">
        <img id="imgShow" src="#" style="width:100%">
    </div>
</div>
</body>
</html>
