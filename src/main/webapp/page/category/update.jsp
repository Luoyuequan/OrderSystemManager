<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script src="../js/category/update.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>类别编辑</h2>
    <button onclick="back()" class="w3-btn w3-red w3-large w3-padding w3-medium">返回</button>
</div>
<div class="w3-container">
    <p>
        <label for="categoryName">类型名称</label>
        <input type="hidden" value="" id="old_category_name">
        <input class="w3-input" type="text"
               placeholder="请输入类型名称"
               name="category_name" id="categoryName" onblur="onCheckUpdate(this.name,this.value)" value="">
    </p>
    <p>
        <label for="categoryDescription">类型描述</label>
        <input class="w3-input" type="text"
               placeholder="请输入类型相应的描述,限50字符内（可选填）"
               name="category_description" id="categoryDescription" value="">
    </p>
    <p>
        <input class="w3-blue w3-btn-block" type="button" onclick="categoryUpdate()" value="修改">
    </p>
</div>
</body>
</html>
