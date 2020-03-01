inputBool = {"category_name": true};

$(function () {
    var datas = {
        "url": "CatelogSingle?catelog_id=" + $("#category_id").val(),
        "data": null,
        "type": "get",
        "dataType": "json",
        "success": "onCatelogSingleResult"
    };
    // console.log(datas);
    publicAjax(datas)
});

function onCatelogSingleResult(data) {
    // console.log(data);
    $("#category_id").val(data[0].catelog_id);
    $("#old_category_name").val(data[0].catelog_name);
    $("#categoryName").val(data[0].catelog_name);
    $("#categoryDescription").val(data[0].catelog_description)

}

/**
 * 检查 修改后的value 是否已修改
 * 若已修改 则判断类型名称是否已存在
 * @param name input 的name
 * @param newValue 修改后的类型名称
 */
function onCheckUpdate(name, newValue) {
    if ($("#old_category_name").val() != newValue) {
        onCatelogCheckExist(name, newValue)
    }
}

/**
 * 检查类型名称 是否已存在
 * @param inputName input控件 的name值
 * @param inputValue input控件 的value值
 */
function onCatelogCheckExist(inputName, inputValue) {
    var datas = {
        "url": "CatelogCheckExist?" + inputName + "=" + inputValue,
        "data": null,
        "type": "get",
        "dataType": "json",
        "success": "CheckInforResult"
    };
    publicAjax(datas)
}

/**
 * 提交 类型 相关信息 修改
 */
function categoryUpdate() {
    if (inputBool.category_name) {
        var data = {
            "category_name": $("input[name=\"category_name\"]").val(),
            "category_description":
                $("input[name=\"category_description\"]").val() == "" ? "无" : $("#categoryDescription").val(),
            "category_id": $("#category_id").val()
        };
        var datas = {
            "url": "CatelogUpdate",
            "data": data,
            "type": "post",
            "dataType": "json",
            "success": "onCatelogUpdateResult"
        };
        publicAjax(datas)
    } else {
        return warning()
    }
}

/**
 * 修改申请  回调函数
 * @param data 提示信息
 */
function onCatelogUpdateResult(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            back()
        }, 2 * 1000);
    } else {
        error(data.msg)
    }
}

//进入类型管理页面
function back() {
    //移除 存储类型 id dom
    $("#category_id").remove();

    $(".w3-show a").eq(0).click();
}

