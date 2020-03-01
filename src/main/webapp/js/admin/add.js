inputBool = {"repeat_pw": false, "user_pw": false, "user_name": false};

function standardAndOnly(name, value) {
    if (onCheckStandard(name)) {
        onCheckOnly(name, value);
    }
}

function adminAdd() {
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
    if (sendData.user_pw != repeat_pw) {
        return warning("两次输入的密码不一致!");
    }
    var data = {
        "url": "AdminAdd",
        "type": "post",
        "dataType": "json",
        "data": sendData,
        "success": "onSuccessAdd"
    };
    publicAjax(data)
}

function onSuccessAdd(data) {
    if (eval(data.state)) {
        notice(data.msg);
        var a = setTimeout(function () {
            $(".w3-show a").eq(1).click();
            // window.location.href = "./home.jsp";
        }, 2 * 1000);
        // newAlert("提示", data.msg, "../index.jsp")
    } else {
        error(data.msg)
    }
}