inputBool = {"category_name": false};

/**
 * 类别添加
 */
function categoryAdd() {
    if (inputBool.category_name) {
        var data = {
            "category_name": $("input[name=\"category_name\"]").val(),
            "category_description":
                $("input[name=\"category_description\"]").val() == "" ? "无" : $("input[name=\"category_description\"]").val()
        };
        var datas = {
            "url": "CatelogAdd",
            "data": data,
            "type": "post",
            "dataType": "json",
            "success": "onCatelogAddResult"
        };
        publicAjax(datas)
    } else {
        return warning()
    }
}

/**
 * 添加之后 回调函数
 * @param data 提示信息
 */
function onCatelogAddResult(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            $(".w3-show a").eq(0).click();
        }, 2 * 1000);
    } else {
        error(data.msg)
    }
}
