$(function () {
    loadCategoryList();
});

/**
 * 页面加载 类型列表
 */
function loadCategoryList() {
    var data = {
        "url": "CatelogList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onLoadCatelogList"
    };
    publicAjax(data)
}

/**
 * 列表加载成功后 回调函数
 * @param data 类型列表数据包
 */
var categoryListLength = 0;
var categoryListData = null;
var limit = 3;
var pageIndex = 1;
var pageLength = 0;

function onLoadCatelogList(data) {
    categoryListLength = data.length;
    categoryListData = data;
    pageLength = categoryListLength % limit === 0 ? categoryListLength / limit : Math.floor(categoryListLength / limit) + 1;
    onChangeLoadCategoryList();

    var pageObj = $(".w3-pagination");
    pageObj.empty();
    //加载页数按钮
    for (var Index = 1; Index <= pageLength; Index++) {
        if (Index == 1) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageUp(this),onChangeLoadCategoryList()'>«</a></li>")
        }
        pageObj.append("<li><a href=\"javascript:void(0)\" onclick=\"pageAction(this),onChangeLoadCategoryList() \">" + Index + "</a></li>");
        if (Index == pageLength) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageDown(this),onChangeLoadCategoryList()'>»</a></li>")
        }
    }
    if (pageLength > 0) {
        $(".w3-pagination li").eq(1).children().eq(0).click();
    }
}

/**
 * 点击页码 重新加载相应的数据
 */
function onChangeLoadCategoryList() {
    var liObj = $("#categoryList li");
    liObj.remove(".w3-padding-16");
    var listObj = $("#categoryList");
    for (var i = (pageIndex - 1) * limit; i < (pageIndex - 1) * limit + limit && i < categoryListLength; i++) {
        listObj.append("<li class=\"w3-padding-16\">" +
            "<span onclick=\"categoryUpdatePage(" + categoryListData[i].catelog_id + ")\" class=\"w3-btn w3-red w3-large w3-closebtn w3-padding w3-margin-right w3-medium\">编辑</span>" +
            "<span onclick=\"categoryDelete(" + categoryListData[i].catelog_id + ")\" class=\"w3-btn w3-red w3-large w3-closebtn w3-padding w3-margin-right w3-medium\">删除</span>" +
            "<span class=\"w3-xlarge\">类型:" + categoryListData[i].catelog_name + "</span><br>" +
            "<span>类型描述:" + categoryListData[i].catelog_description + "</span>" +
            "</li>")
    }
    if (pageLength == 0) {
        listObj.append("<li class=\"w3-padding-16\">无</li>")
    }
}

/**
 * 点击页码 改变点击的当前页码样式 重置其他页码样式
 * @param obj 当前点击页码元素
 */
function pageAction(obj) {
    pageIndex = parseInt($(obj).text());

    for (var i = 1; i <= pageLength; i++) {
        $(".w3-pagination li").eq(i).children().eq(0).attr("class", "");
    }

    obj.className += " w3-blue";
}

/**
 * 上一页 按钮
 * @param obj
 */
function pageUp(obj) {
    if (pageIndex == 1) {
        return warning("已经是首页啦!")
    }
    pageIndex--;
    $(".w3-pagination li").eq(pageIndex).children().eq(0).click();
}

/**
 * 下一页 按钮
 * @param obj
 */
function pageDown(obj) {
    if (pageIndex == pageLength) {
        return warning("已经是末尾啦!")
    }
    pageIndex++;
    $(".w3-pagination li").eq(pageIndex).children().eq(0).click();
}

/**
 * 删除指定类型
 * @param id 被删除的类型id
 */
function categoryDelete(id) {
    if (confirm("请确定是否删除指定类型?，删除后，可从回收站恢复！")) {
        var data = {
            "url": "CatelogDelete?catelog_id=" + id,
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onCategoryDelete"
        };
        publicAjax(data)
    }
}

/**
 * 删除成功后 回调函数
 * @param data 提示信息
 */
function onCategoryDelete(data) {
    if (eval(data.state)) {
        notice(data.msg);
        loadCategoryList()
    } else {
        error(data.msg)
    }
}

/**
 * 进入编辑类型内容页面
 * @param id 类型id
 */
function categoryUpdatePage(id) {
    var content = $("#content");
    //添加一个隐藏dom 用于存储 类型 id
    if ($("#category_id").length == 0){
        $("#main").append("<input type=\"hidden\" value=\"" + id + "\" id=\"category_id\">");
    }
    else {
        $("#category_id").val(id)
    }
    content.load("category/update.jsp");
}