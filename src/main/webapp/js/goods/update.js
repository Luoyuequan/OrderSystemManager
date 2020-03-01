inputBool = {"goods_name": true};

$(function () {
    var data = {
        "url": "CatelogList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onloadCategorySelect"
    };
    publicAjax(data);
    var datas = {
        "url": "GoodsSingle?goods_id=" + $("#goods_id").val(),
        "data": null,
        "type": "get",
        "dataType": "json",
        "success": "onGoodsSingleResult"
    };
    // console.log(datas);
    publicAjax(datas)
});

function onloadCategorySelect(data) {
    for (var index = 0; index < data.length; index++) {
        $("select[name='goods_catelog']").append(
            "<option value=\"" + data[index].catelog_id + "\">" + data[index].catelog_name + "</option>"
        );
    }
}

var old_goods_img = null;

function onGoodsSingleResult(data) {
    // console.log(data);
    $("#goods_id").val(data[0].goods_id);
    $("#old_goods_name").val(data[0].goods_name);

    $("#goodsName").val(data[0].goods_name);
    $("#goodsDescription").val(data[0].goods_description);
    $("#goodsPrice").val(data[0].price);
    old_goods_img = data[0].goods_img;

    $("#goodsRenqun").val(data[0].goods_renqun);
    $("#goods_catelog").val(data[0].goods_catelog_id)

}

/**
 * 检查 修改后的value 是否已修改
 * 若已修改 则判断菜品名称是否已存在
 * @param name input 的name
 * @param newValue 修改后的菜品名称
 */
function onCheckUpdate(name, newValue) {
    if ($("#old_goods_name").val() != newValue) {
        onGoodsCheckExist(name, newValue)
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

/**
 * 提交 菜品 相关信息 修改
 */
function goodsUpdate() {
    if (inputBool.goods_name) {
        var flag = $("input[name='goods_img']").val();
        var goods_renqun = $("select[name='goods_renqun']").val();
        var goods_price = $("#goodsPrice").val();
        var goods_catelog_id = $("select[name='goods_catelog']").val();
        var goods_name = $("input[name='goods_name']").val();
        var description = $("#goodsDescription").val() == "" ? "无" : $("#goodsDescription").val();
        if (goods_price.length < 0) {
            return warning("请输入菜品单价!");
        } else if (goods_catelog_id == null) {
            return warning("请选择菜品类型!");
        } else if (goods_renqun == null) {
            return warning("请选择适应人群!");
        } else {
            var form = new FormData();
            form.append("goods_name", goods_name);
            form.append("goods_description", description);
            form.append("goods_img", flag == "" ? old_goods_img : $("#goodsImg")[0].files[0]);
            form.append("goods_market_price", goods_price);
            form.append("goods_catelog_id", goods_catelog_id);
            form.append("goods_renqun", goods_renqun);
            form.append("goods_id", $("#goods_id").val());
            var datas = {
                "url": "GoodsUpdate",
                "data": form,
                "success": "onGoodsUpdateAfter"
            };
            // console.log(datas);
            publicFileAjax(datas)
        }
    } else {
        return warning()
    }
}

/**
 * 修改申请  回调函数
 * @param data 提示信息
 */
function onGoodsUpdateAfter(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            back()
        }, 2 * 1000);
    } else {
        error(data.msg)
    }
}

//进入菜品管理页面
function back() {
    //移除 存储菜品 id dom
    $("#goods_id").remove();

    $(".w3-show a").eq(0).click();
}

