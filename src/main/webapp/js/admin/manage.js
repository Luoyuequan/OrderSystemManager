$(function () {
    loadAdminList();
});

/**
 * 页面加载 管理员列表
 */
function loadAdminList() {
    var data = {
        "url": "AdminList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onLoadAdminList"
    };
    publicAjax(data)
}

/**
 * 列表加载成功后 回调函数
 * @param data 管理员列表数据包
 */
var AdminListLength = 0;
var adminListData = null;
var limit = 3;
var pageIndex = 1;
var pageLength = 0;

function onLoadAdminList(data) {
    AdminListLength = data.length;
    adminListData = data;
    pageLength = AdminListLength % limit === 0 ? AdminListLength / limit : Math.floor(AdminListLength / limit) + 1;
    onChangeLoadAdminList();
    var pageObj = $(".w3-pagination");
    pageObj.empty();
    //加载页数按钮
    for (var Index = 1; Index <= pageLength; Index++) {
        if (Index == 1) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageUp(this),onChangeLoadAdminList()'>«</a></li>")
        }
        pageObj.append("<li><a href=\"javascript:void(0)\" onclick=\"pageAction(this),onChangeLoadAdminList() \">" + Index + "</a></li>");
        if (Index == pageLength) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageDown(this),onChangeLoadAdminList()'>»</a></li>")
        }
    }
    if (pageLength > 0) {
        $(".w3-pagination li").eq(1).children().eq(0).click();
    }
}

function onChangeLoadAdminList() {
    var listObj = $("#adminList");
    listObj.empty("");
    listObj.append("<li><h2>管理员列表</h2></li>");
    for (var i = (pageIndex - 1) * limit; i < (pageIndex - 1) * limit + limit && i < AdminListLength; i++) {
        listObj.append("<li class=\"w3-padding-16\">" +
            "<span onclick=\"adminDelete(this," + adminListData[i].user_id + ")\" class=\"w3-btn w3-red w3-large w3-closebtn w3-padding w3-margin-right w3-medium\">删除</span>" +
            "<img src=\"../img/img_avatar2.png\" class=\"w3-left w3-circle w3-margin-right\" style=\"width:60px\" />" +
            "<span class=\"w3-xlarge\">账户:" + adminListData[i].user_name + "</span><br>" +
            "<span>密码:" + adminListData[i].user_pw + "</span>" +
            "</li>")
    }
    if (pageLength == 0){
        listObj.append("<li class=\"w3-padding-16\">无</li>")
    }
}

function pageAction(obj) {
    pageIndex = parseInt($(obj).text());

    for (var i = 1; i <= pageLength; i++) {
        $(".w3-pagination li").eq(i).children().eq(0).attr("class", "");
    }

    obj.className += " w3-blue";
}

function pageUp(obj) {
    if (pageIndex == 1) {
        return warning("已经是首页啦!")
    }
    pageIndex--;
    $(".w3-pagination li").eq(pageIndex).children().eq(0).click();
}

function pageDown(obj) {
    if (pageIndex == pageLength) {
        return warning("已经是末尾啦!")
    }
    pageIndex++;
    $(".w3-pagination li").eq(pageIndex).children().eq(0).click();
}

/**
 * 删除指定管理员
 * @param obj 当前点击对象
 * @param id 被删除的管理员id
 */
function adminDelete(obj, id) {
    if (confirm("请确定是否删除指定用户?")) {
        var data = {
            "url": "AdminDelete?user_id=" + id,
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onAdminDelete"
        };
        publicAjax(data)
    }
}

/**
 * 删除成功后 回调函数
 * @param data 提示信息
 */
function onAdminDelete(data) {
    if (eval(data.state)) {
        notice(data.msg);
        loadAdminList()
    } else {
        error(data.msg)
    }
}