<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/admin/add.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div id="adminAdd" class="page">
    <div class="w3-container w3-blue-grey">
        <h2>管理员添加</h2>
    </div>
    <div class="w3-container">
        <p>
            <label for="user_name">账号</label>
            <input class="w3-input" type="text"
                   placeholder="字母开头，允许5-16字符，允许字母数字下划线"
                   name="user_name" id="user_name" onblur="standardAndOnly(this.name,this.value)">
        </p>
        <p>
            <label for="user_pw">密码</label>
            <input class="w3-input" type="password"
                   placeholder="只能输入6-20个字母、数字、下划线"
                   name="user_pw" id="user_pw" onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <label for="repeat_pw">确认密码</label>
            <input class="w3-input" type="password"
                   placeholder="只能输入6-20个字母、数字、下划线"
                   name="repeat_pw" id="repeat_pw" onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <input class="w3-blue w3-btn-block" type="button" onclick="adminAdd()" value="添加">
        </p>
    </div>
</div>
</body>
</html>
