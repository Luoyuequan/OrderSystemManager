function warning(m, t) {
    let title = arguments[1] ? arguments[1] : "警告";
    let msg = arguments[0] ? arguments[0] : "请输入符合要求的信息!";
    $.growl.warning({
        title: title,
        message: msg
    });
}

function notice(m, t) {
    let title = arguments[1] ? arguments[1] : "提醒";
    let msg = arguments[0] ? arguments[0] : "操作成功!";
    $.growl.notice({
        title: title,
        message: msg
    });
}

function error(m, t) {
    let title = arguments[1] ? arguments[1] : "错误";
    let msg = arguments[0] ? arguments[0] : "操作失败!";
    $.growl.error({
        title: title,
        message: msg
    });
}

function newAlert(title, msg, url) {
    let href = arguments[2] ? arguments[2] : null;
    let alertHtml = "<div id=\"alert\" class=\"w3-modal\" >\n" +
        "    <div class=\"w3-modal-content\" style=\"width: 30%;\">\n" +
        "      <header class=\"w3-container w3-red\">\n" +
        "        <span class=\"w3-closebtn\">&times;</span>\n" +
        "        <h2>Modal Header</h2>\n" +
        "      </header>\n" +
        "      <div class=\"w3-container\">\n" +
        "        <p>Some text..</p>\n" +
        "      </div>\n" +
        "    </div>\n" +
        "  </div>";
    $("body").append(alertHtml);
    let alertObj = $("#alert");
    alertObj.css("display", "block");
    // document.getElementById('alert').style.display='none';
    $("#alert div header h2").text(title);
    $("#alert div div p").text(msg);
    $("#alert div header span").click(function () {
        alertObj.css("display", "none");
        if (href != null) {
            window.location.href = href;
        }
    })
}