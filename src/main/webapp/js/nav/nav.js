$(function () {
    // 用户会话已超时  重新登录
    // if ($("#username").text() == "null,你好!") {
    //     warning("连接已断开,请重新登录!<br>3秒后自动跳转页面...");
    //     var a = setTimeout(function () {
    //         window.location.href = "../index.jsp";
    //     }, 3 * 1000);
    // }

    var data = {
        "url": "NavList",
        "type": "post",
        "dataType": "json",
        "data": null,
        "success": "onLoadNavList"
    };
    publicAjax(data)
});

function onLoadNavList(data) {
    console.log(data);
    var nav_Html = "";
    for (var index in data) {
        //加载导航栏一级菜单
        for (var nav_1_text in data[index]) {
            nav_Html += "<a onclick=\"myAccFunc(this)\" href=\"javascript:void(0)\" class='w3-xlarge'>" + nav_1_text + "</a>";
            nav_Html += "<div class=\"w3-large w3-accordion-content w3-white w3-card-4\">";
            var nav_2 = data[index][nav_1_text];
            //加载导航栏二级菜单
            for (var nav_2_index in nav_2) {
                nav_Html += "<a href=\"javascript:void(0)\" style=\"padding-left:40px;\"  " +
                    "onclick=\"linkFunc(this,'" + nav_2[nav_2_index]["url"] + "')\">"
                    + nav_2[nav_2_index]["text"] + "</a>\n";
            }
            nav_Html += "</div>";
        }
    }
    $(".w3-accordion").append(nav_Html);

    var nav_1_obj = $(".w3-accordion a").eq(1);

    //改变导航栏样式
    // nav_1_obj.attr("class", function (i, value) {
    //     return value + " w3-green";
    // });
    // $(".w3-accordion div:first").attr("class", function (i, value) {
    //     return value + " w3-show";
    // });
    //加载第一个页面
    // nav_1_obj.click();
    // var a_first =  $(".w3-accordion div:first a:first");
    // // if (a_first.text() != "无")
    // a_first.click();
}

/**
 * 点击导航栏二级菜单 加载对应页面
 * @param obj 当前点击的对象
 * @param pageName 对应的页面地址
 */
function linkFunc(obj, pageName) {
    // alert(this.href)
    $("#content").load(pageName);
    $(obj).siblings().each(function (index, element) {
        element.className = element.className.replace(" w3-blue", "");
    });
    if (obj.className.indexOf("w3-blue") < 0) {
        obj.className += " w3-blue";
    }
}

/**
 * 点击导航栏一级菜单 打开对应的二级菜单
 * 关闭其他一级菜单 并改变对应的样式
 * @param obj 当前点击的对象
 */
function myAccFunc(obj) {
    $(obj).siblings('a').each(function (index, element) {
        element.className = element.className.replace(" w3-green", "");
    });
    $(obj).next().siblings('div').each(function (index, element) {
        element.className = element.className.replace(" w3-show", "");
        $(element).children().each(function (index, element) {
            element.className = element.className.replace(" w3-blue", "");
        });
    });
    var x = $(obj).next();
    if (x.attr("class").indexOf("w3-show") < 0) {
        x.attr('class', function (i, value) {
            return value += " w3-show";
        });
        $(obj).attr('class', function (i, value) {
            return value += " w3-green";
        });
    }
}

/**
 * 注销用户
 */
function logout() {
    if (confirm("请确定是否注销用户?")) {
        var data = {
            "url": "AdminLogout",
            "type": "get",
            "dataType": "json",
            "data": null,
            "success": "onLogout"
        };
        publicAjax(data)
    }
}

/**
 * 注销用户成功后 回调函数
 * @param data 返回的数据包
 */

function onLogout(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            window.location.href = "../index.jsp";
        }, 3 * 1000);
    } else {
        error(data.msg)
    }
}

function w3_open() {
    $("#main").css("margin-left", "30%");
    $(".w3-sidenav").css("width", "30%").css("display", "block").attr("class", function (i, value) {
        return value += " w3-animate-left";
    });
    $(".w3-opennav").css("display", "none");
}

function w3_close() {
    $("#main").css("margin-left", "0%");
    $(".w3-sidenav").css("display", "none").attr("class", function (i, value) {
        return value.replace(" w3-animate-left", "")
    });
    $(".w3-opennav").css("display", "block");
}