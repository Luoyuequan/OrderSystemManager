$(function () {
    // 用户会话已超时  重新登录
    if ($("#user_name").val() == "null") {
        warning("连接已断开,请重新登录!<br>3秒后自动跳转页面...");
        var a = setTimeout(function () {
            window.location.href = "../index.jsp";
        }, 3 * 1000);
    }
});
var inputBool = {"new_pw": false, "repeat_pw": false, "user_pw": false, "user_name": true};

/**
 * 管理员修改密码
 */

function adminUpdate() {
    var sendData = {};
    for (var key1 in inputBool) {
        if (!inputBool[key1]) {
            return warning();
        }
    }
    var repeat_pw = null;
    for (var key2 in inputBool) {
        if (key2 == "repeat_pw") {
            repeat_pw = $("input[name=\"" + key2 + "\"]").val();
            continue;
        }
        sendData[key2] = $("input[name=\"" + key2 + "\"]").val()
    }
    if (sendData.new_pw != repeat_pw) {
        return warning("两次输入的密码不一致!");
    }
    var data = {
        "url": "AdminPasswdUpdate",
        "type": "post",
        "dataType": "json",
        "data": sendData,
        "success": "onSuccessUpdate"
    };
    publicAjax(data)
}

/**
 * 密码修改成功 回调函数
 * @param data 返回的数据包
 */
function onSuccessUpdate(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            window.location.href = "../index.jsp";
        }, 3 * 1000);
        // newAlert("提示", data.msg, "../index.jsp")
    } else {
        error(data.msg)
    }
}