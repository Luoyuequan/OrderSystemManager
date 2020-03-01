$(function () {
    loadRecycleyList();
});

/**
 * 页面加载 回收站列表
 */
function loadRecycleyList() {
    var data = {
        "url": "GoodsRecycleList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onLoadRecycleList"
    };
    publicAjax(data)
}

/**
 * 列表加载成功后 回调函数
 * @param data 回收站列表数据包
 */
var recycleListLength = 0;
var recycleListData = null;
var limit = 8;
var pageIndex = 1;
var pageLength = 0;

function onLoadRecycleList(data) {
    recycleListLength = data.length;
    recycleListData = data;
    pageLength = recycleListLength % limit === 0 ? recycleListLength / limit : Math.floor(recycleListLength / limit) + 1;
    onChangeLoadRecycleList();
    var pageObj = $(".w3-pagination");
    pageObj.empty();
    //加载页数按钮
    for (var Index = 1; Index <= pageLength; Index++) {
        if (Index == 1) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageUp(this),onChangeLoadRecycleList()'>«</a></li>")
        }
        pageObj.append("<li><a href=\"javascript:void(0)\" onclick=\"pageAction(this),onChangeLoadRecycleList() \">" + Index + "</a></li>");
        if (Index == pageLength) {
            pageObj.append("<li><a href=\"javascript:void(0)\" onclick='pageDown(this),onChangeLoadRecycleList()'>»</a></li>")
        }
    }
    if (pageLength > 0) {
        $(".w3-pagination li").eq(1).children().eq(0).click();
    }
}

/**
 * 点击页码 重新加载相应的数据
 */
function onChangeLoadRecycleList() {
    $("#recycleGoodsList tr").remove(".tr_content");
    for (var i = (pageIndex - 1) * limit; i < (pageIndex - 1) * limit + limit && i < recycleListLength; i++) {
        $("#recycleGoodsList").append(
            "<tr class='tr_content'>" +
            "<td>" + recycleListData[i].goodsName + "</td>" +
            "<td>" + recycleListData[i].catelogName + "</td>" +
            "<td>" + recycleListData[i].price + "</td>" +
            "<td>" +
            "<a href='javascript:void(0)' onclick='showImg(\"" + recycleListData[i].img + "\")'>查看图片</a>" +
            "</td>" +
            "<td>" + recycleListData[i].description + "</td>" +
            "<td>" + recycleListData[i].renqun + "</td>" +
            "<td>" +
            "<a href=\"javascript:void(0)\" onclick=\"goodsRestore(" + recycleListData[i].id + ")\">恢复</a>" +
            "</td>" +
            "</tr>"
        )
    }
    if (pageLength == 0) {
        $("#recycleGoodsList").append("<tr><td colspan='7'>无</td></tr>")
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
 * 恢复指定菜品
 * @param id 被删除的菜品id
 */
function goodsRestore(id) {
    if (confirm("请确定是否恢复指定菜品?")) {
        var data = {
            "url": "GoodsRestore?goods_id=" + id,
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onGoodsRestore"
        };
        publicAjax(data)
    }
}

/**
 * 恢复成功后 回调函数
 * @param data 提示信息
 */
function onGoodsRestore(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            //进入菜品管理页面
            $(".w3-show a").eq(0).click();
        }, 2 * 1000);
    } else {
        error(data.msg)
    }
}

/**
 * 清空回收站
 */
function clearRecycle() {
    if (confirm("请确定是否清空回收站?,清空后不可恢复！")) {
        var data = {
            "url": "GoodsRecycleDelete",
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onRecycleDelete"
        };
        publicAjax(data)
    }
}

/**
 * 清空成功后 回调函数
 * @param data 提示信息
 */
function onRecycleDelete(data) {
    if (eval(data.state)) {
        notice(data.msg);
        loadRecycleyList()
    } else {
        error(data.msg)
    }
}

function showImg(img_name) {
    $("#goodsImg div img").attr("src", "../img/goods_img/" + img_name);
    $("#goodsImg").css("display", "block");
}
