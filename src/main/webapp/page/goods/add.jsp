<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script src="../js/goods/add.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div class="w3-container w3-blue-grey">
    <h2>菜品添加</h2>
</div>
<div class="w3-container w3-margin-top">
    <label>
        <select class="w3-select w3-border w3-padding-4" name="goods_catelog">
            <option value="" disabled selected>--请选择菜品类型--</option>
        </select>
    </label>
    <p>
        <label for="goodsName">菜品名称</label>
        <input class="w3-input" type="text"
               placeholder="请输入菜品名称"
               name="goods_name" id="goodsName" onblur="onGoodsCheckExist(this.name,this.value)">
    </p>
    <p>
        <label for="goodsDescription">菜品描述</label>
        <input class="w3-input" type="text"
               placeholder="请输入菜品相应的描述,限45字符内（可选填）"
               name="goods_description" id="goodsDescription">
    </p>
    <p>
        <label for="goodsPrice">菜品单价</label>
        <input class="w3-input" type="text"
               placeholder="请输入菜品单价"
               name="goods_price" id="goodsPrice" onkeyup="value=value.replace(/[^\d]/g,'')">
    </p>
    <p>
        <label for="goodsImg">菜品图片(仅限png格式)</label>
        <input class="w3-input" type="file" name="goods_img" id="goodsImg" accept=".png,.jpg,.jpeg" size="10"
               formenctype="multipart/form-data">
    </p>
    <label>
        <select class="w3-select w3-border w3-padding-4" name="goods_renqun">
            <option value="" disabled selected>--请选择适合人群--</option>
        </select>
        <script>
            var goodsRenqun = ["所有人", "小孩", "老年人", "孕妇", "老少皆宜"];
            for (var index in goodsRenqun) {
                $("select[name=\"goods_renqun\"]").append(
                    "<option value=\"" + goodsRenqun[index] + "\">" + goodsRenqun[index] + "</option>"
                )
            }
        </script>
    </label>
    <p>
        <input class="w3-blue w3-btn-block" type="button" onclick="goodsAdd()" value="添加">
    </p>
</div>
</body>
</html>
