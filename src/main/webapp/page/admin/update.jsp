<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>密码修改</title>
    <script src="../js/admin/update.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<div id="adminUpdate" class="page" style="display: block;">
    <div class="w3-container w3-blue-grey">
        <h2>密码修改</h2>
    </div>
    <div id="adminUpdateForm" class="w3-container">
        <p>
            <label for="user_name">账号</label>
            <input title="不可修改" id="user_name" name="user_name" class="w3-input w3-border" type="text"
                   value="<%= session.getAttribute(session.getId())%>" readonly>
        </p>
        <p>
            <label for="old_pw">原密码</label>
            <input class="w3-input w3-border" type="password" name="user_pw" id="old_pw" placeholder="请输入原密码"
                   onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <label for="new_pw">新密码</label>
            <input class="w3-input w3-border" type="password" name="new_pw" id="new_pw" placeholder="请输入新密码"
                   onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <label for="repeat_pw">确认密码</label>
            <input class="w3-input w3-border" type="password" name="repeat_pw" id="repeat_pw" placeholder="请再次输入新密码"
                   onblur="onCheckStandard(this.name)">
        </p>
        <p>
            <input class="w3-btn w3-blue" value="修改" type="button" onclick="adminUpdate()"/>
        </p>
    </div>
</div>
</body>
</html>
