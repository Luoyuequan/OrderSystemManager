<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="zh">
<head>
    <title>后台登录</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="js/jquery-3.4.1.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/publicAjax.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/checkStandard.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/login.js" type="text/javascript" charset="UTF-8"></script>
    <link href="css/w3.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery.growl.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.growl.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/publicGrowl.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div style="width: 30%;margin: 10% auto;" class="w3-card-4">
    <div class="w3-container w3-blue">
        <h2>后台管理登录</h2>
    </div>
    <div class="w3-container">
        <p>
            <label for="user_name">账号</label>
            <input class="w3-input" type="text"
                   placeholder="字母开头，允许5-16字符，允许字母数字下划线"
                   name="user_name" id="user_name" onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <label for="user_pw">密码</label>
            <input class="w3-input" type="password"
                   placeholder="只能输入6-20个字母、数字、下划线"
                   name="user_pw" id="user_pw" onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <input class="w3-blue w3-btn-block" type="button" onclick="login()" value="登录">
        </p>
    </div>
</div>
<input type="hidden" value="admin" id="identity">
</body>
</html>
