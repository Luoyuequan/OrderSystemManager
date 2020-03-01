inputBool = {"goods_name": false};
$(function () {
    var data = {
        "url": "CatelogList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onloadCategorySelect"
    };
    publicAjax(data)
});

/*var imgBase64 = null;
$("#goodsImg").on('change', function (e) {
    // 检查是否是图片
    var filePath = $(this).val();
    var fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();//获取文件后缀名
    //检查后缀名
    if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
        return warning('文件格式必须为：png/jpg/jpeg');
    }

    //获取并记录图片的base64编码
    var reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]); // 读出 base64
    reader.onloadend = function () {
        // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
        imgBase64 = reader.result;//base64
    };
});*/

function onloadCategorySelect(data) {
    for (var index = 0; index < data.length; index++) {
        $("select[name='goods_catelog']").append(
            "<option value=\"" + data[index].catelog_id + "\">" + data[index].catelog_name + "</option>"
        );
    }
}

function onGoodsCheckExist(inputName, inputValue) {
    var datas = {
        "url": "GoodsCheckExist?" + inputName + "=" + inputValue,
        "data": null,
        "type": "get",
        "dataType": "json",
        "success": "CheckInforResult"
    };
    publicAjax(datas)
}

function goodsAdd() {
    if (inputBool.goods_name) {
        var img = $("input[name='goods_img']");
        var goods_renqun = $("select[name='goods_renqun']").val();
        var goods_price = $("#goodsPrice").val();
        var goods_catelog_id = $("select[name='goods_catelog']").val();
        var goods_name = $("input[name='goods_name']").val();
        var description = $("#goodsDescription").val() == "" ? "无" : $("#goodsDescription").val();
        if (img == "") {
            return warning("请选择要上传的文件!");
        } else if (goods_price.length < 0) {
            return warning("请输入菜品单价!");
        } else if (goods_catelog_id == null) {
            return warning("请选择菜品类型!");
        } else if (goods_renqun == null) {
            return warning("请选择适应人群!");
        } else {
            var form = new FormData();
            form.append("goods_name", goods_name);
            form.append("goods_description", description);
            form.append("goods_img", img[0].files[0]);
            form.append("goods_market_price", goods_price);
            form.append("goods_catelog_id", goods_catelog_id);
            form.append("goods_renqun", goods_renqun);
            var datas = {
                "url": "GoodsAdd",
                "data": form,
                "success": "onGoodsAddAfter"
            };
            // console.log(datas);
            publicFileAjax(datas)
        }
    } else {
        return warning()
    }
}

function onGoodsAddAfter(data) {
    // console.log(data);
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            $(".w3-show a").eq(0).click();
        }, 2 * 1000);
    } else {
        error(data.msg)
    }
}