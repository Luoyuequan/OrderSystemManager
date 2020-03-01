<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/category/add.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../js/category/update.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>类别添加</h2>
</div>
<div class="w3-container">
    <p>
        <label for="categoryName">类型名称</label>
        <input class="w3-input" type="text"
               placeholder="请输入类型名称"
               name="category_name" id="categoryName" onblur="onCatelogCheckExist(this.name,this.value)">
    </p>
    <p>
        <label for="categoryDescription">类型描述</label>
        <input class="w3-input" type="text"
               placeholder="请输入类型相应的描述,限50字符内（可选填）"
               name="category_description" id="categoryDescription">
    </p>
    <p>
        <input class="w3-blue w3-btn-block" type="button" onclick="categoryAdd()" value="添加">
    </p>
</div>
</body>
</html>
