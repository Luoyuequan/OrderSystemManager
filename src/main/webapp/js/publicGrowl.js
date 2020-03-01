function warning(m, t) {
    const title = arguments[1] ? arguments[1] : "警告";
    const msg = arguments[0] ? arguments[0] : "请输入符合要求的信息!";
    $.growl.warning({
        title: title,
        message: msg
    });
}

function notice(m, t) {
    const title = arguments[1] ? arguments[1] : "提醒";
    const msg = arguments[0] ? arguments[0] : "操作成功!";
    $.growl.notice({
        title: title,
        message: msg
    });
}

function error(m, t) {
    const title = arguments[1] ? arguments[1] : "错误";
    const msg = arguments[0] ? arguments[0] : "操作失败!";
    $.growl.error({
        title: title,
        message: msg
    });
}
