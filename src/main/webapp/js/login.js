var inputBool = {"user_name": false, "user_pw": false};
$(document).ready(function () {
    $("#user_pw").keydown(function (e) {
        if (e.which == 13 || e.which == 108) {
            onCheckStandard($("#user_pw").attr('name'));
            login()
        }
    });
    $("#user_name").keydown(function (e) {
        if (e.which == 13 || e.which == 108) {
            onCheckStandard($("#user_name").attr('name'));
            login()
        }
    })
});


function login() {
    // console.log(inputBool);
    if (inputBool.user_name && inputBool.user_pw) {
        var vipName = $("#user_name").val();
        var passWd = $("#user_pw").val();
        var identity = $("#identity").val();
        var data = {
            "url": "LoginCheck",
            "dataType": "json",
            "type": "post",
            "data": {"user_name": vipName, "user_pw": passWd, "identity": identity},
            "success": "onReturnInfor"
        };
        publicAjax(data);
    } else {
        warning()
    }
}

function onReturnInfor(data) {
    console.log(data);
    // var alertObj = $("#alert");
    if (eval(data.state)) {
        notice(data.msg + "<br>"+"3秒后自动跳转页面...");
        var a = setTimeout(function () {
            window.location.href = "page/home.jsp";
        }, 3 * 1000);
    } else {
        error(data.msg);
    }
}
