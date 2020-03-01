$(function () {
    loadGoodsList();
});

function loadGoodsList() {
    var data = {
        "url": "GoodsList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onLoadGoodsList"
    };
    publicAjax(data)
}

/**
 * 列表加载成功后 回调函数
 * @param data 菜品列表数据包
 */
var goodsListLength = 0;
var goodsListData = null;
var limit = 5;
var pageIndex = 1;
var pageLength = 0;

function onLoadGoodsList(data) {
    goodsListLength = data.length;
    goodsListData = data;
    pageLength = goodsListLength % limit === 0 ? goodsListLength / limit : Math.floor(goodsListLength / limit) + 1;
    onChangeLoadGoodsList();
    var pageObj = $(".w3-pagination");
    pageObj.empty();
    //加载页数按钮
    for (var Index = 1; Index <= pageLength; Index++) {
        if (Index == 1) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageUp(this),onChangeLoadGoodsList()'>«</a></li>")
        }
        pageObj.append("<li><a href=\"javascript:void(0)\" onclick=\"pageAction(this),onChangeLoadGoodsList() \">" + Index + "</a></li>");
        if (Index == pageLength) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageDown(this),onChangeLoadGoodsList()'>»</a></li>")
        }
    }
    if (pageLength > 0) {
        $(".w3-pagination li").eq(1).children().eq(0).click();
    }
}

/**
 * 点击页码 重新加载相应的数据
 */
function onChangeLoadGoodsList() {
    $("#goodsList tr").remove(".tr_content");
    for (var i = (pageIndex - 1) * limit; i < (pageIndex - 1) * limit + limit && i < goodsListLength; i++) {
        $("#goodsList").append(
            "<tr class='tr_content'>" +
            "<td>" + goodsListData[i].goodsName + "</td>" +
            "<td>" + goodsListData[i].catelogName + "</td>" +
            "<td>" + goodsListData[i].price + "</td>" +
            "<td>" +
            "<a href='javascript:void(0)' onclick='showImg(\"" + goodsListData[i].img + "\")'>查看图片</a>" +
            "</td>" +
            "<td>" + goodsListData[i].description + "</td>" +
            "<td>" + goodsListData[i].renqun + "</td>" +
            "<td>" +
            "<a href=\"javascript:void(0)\" onclick=\"goodsUpdatePage(" + goodsListData[i].id + ")\">编辑</a>&nbsp;" +
            "<a href=\"javascript:void(0)\" onclick=\"goodsDelete(" + goodsListData[i].id + ")\">删除</a>" +
            "</td>" +
            "</tr>"
        )
    }
    if (pageLength === 0) {
        $("#goodsList").append("<tr><td colspan='7'>无</td></tr>")
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
 * 删除指定菜品
 * @param id 被删除的菜品id
 */
function goodsDelete(id) {
    if (confirm("请确定是否删除指定菜品?，删除后，可从回收站恢复！")) {
        var data = {
            "url": "GoodsDelete?goods_id=" + id,
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onGoodsDelete"
        };
        publicAjax(data)
    }
}

/**
 * 删除成功后 回调函数
 * @param data 提示信息
 */
function onGoodsDelete(data) {
    if (eval(data.state)) {
        notice(data.msg);
        loadGoodsList()
    } else {
        error(data.msg)
    }
}

/**
 * 进入编辑菜品内容页面
 * @param id 菜品id
 */
function goodsUpdatePage(id) {
    var content = $("#content");
    //添加一个隐藏dom 用于存储 菜品 id
    if ($("#goods_id").length == 0){
        $("#main").append("<input type=\"hidden\" value=\"" + id + "\" id=\"goods_id\">");
    }
    else {
        $("#goods_id").val(id)
    }
    content.load("goods/update.jsp");
}

function showImg(img_name) {
    // document.getElementById("imgShow").src = "../img/goods_img/" + img_name;
    // $("#goodsImg div img").attr("src", "http://localhost:8080/OrderSystemManagement/img/goods_img/" + img_name);
    $("#goodsImg div img").attr("src", "../img/" + img_name);
    $("#goodsImg").css("display", "block");
}
