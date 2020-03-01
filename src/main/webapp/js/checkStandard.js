function onCheckStandard(inputName) {
    // var inputName = obj.name;
    var inputValue = $("input[name=\"" + inputName + "\"]").val();
    // console.log(inputValue);
    if (inputValue.length > 0) {
        if (!regx(inputName, inputValue)) {
            warning()
        } else {
            inputBool[inputName] = regx(inputName, inputValue);
            return true;
        }

    }
}

function regx(inputName, s) {
    var r = null;
    switch (inputName) {
        case 'user_pw':
            r = "^(\\w){6,20}$";
            break;
        case 'old_pw':
            r = "^(\\w){6,20}$";
            break;
        case 'new_pw':
            r = "^(\\w){6,20}$";
            break;
        case 'repeat_pw':
            r = "^(\\w){6,20}$";
            break;
        case 'user_name':
            r = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
            break;
        case 'tel':
            //^((\+?[0-9]{2,4}\-[0-9]{3,4}\-)|([0-9]{3,4}\-))?([0-9]{7,8})(\-[0-9]+)?$
            //^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$
            //^1[3456789]\d{9}$
            r = "^1[3456789]\\d{9}$";
            break;
        case 'email':
            r = "\\w+([-+.]w+)*@\\w+([-.]\\w+)*.\\w+([-.]\\w+)*";
            break;
        case 'nickname':
            r = "[^x00-xff]";
            break;
    }
    if (r == null || r == "") {
        return false;
    }
    var patrn = new RegExp(r);
    return !!patrn.exec(s);
}

function onCheckOnly(inputName, inputValue) {
    var data = {};
    data[inputName] = inputValue;
    var datas = {
        "url": "CheckInfor",
        "data": data,
        "type": "post",
        "dataType": "json",
        "success": "CheckInforResult"
    };
    // console.log(datas);
    publicAjax(datas)
}


function CheckInforResult(datas) {
    if (!eval(datas.state)) {
        error(datas.msg);
        inputBool[datas.name] = false;
    } else {
        inputBool[datas.name] = true;
    }
}