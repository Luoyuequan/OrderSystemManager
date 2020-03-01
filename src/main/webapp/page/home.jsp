<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>网上订餐系统后台管理</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/w3.css" rel="stylesheet"/>
    <script src="../js/jquery-3.4.1.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../js/publicAjax.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../js/checkStandard.js" type="text/javascript" charset="UTF-8"></script>
    <link href="../css/jquery.growl.css" rel="stylesheet">
    <script src="../js/jquery.growl.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../js/publicGrowl.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../js/nav/nav.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<ul class="w3-ul w3-card-4 w3-blue">
    <li class="w3-padding-16">
        <span onclick="logout()"
              class="w3-btn w3-red w3-large w3-closebtn w3-padding w3-margin-right w3-medium">注销</span>
        <img src="../img/img_avatar2.png" class="w3-left w3-circle w3-margin-right" style="width:60px"/>
        <span class="w3-xlarge">管理员</span><br>
        <span id="username"><%= session.getAttribute(session.getId())%>,你好!</span>
    </li>
</ul>
<nav class="w3-sidenav w3-white w3-card-2 w3-animate-left" style="width: 30%;overflow: hidden;">
    <div class="w3-accordion">
        <a class="w3-blue-grey w3-xlarge w3-right-align" href="javascript:void(0)" onclick="w3_close()">菜单&times;</a>
    </div>
</nav>
<div id="main" style="margin-left: 30%;">
    <span class="w3-opennav w3-xlarge w3-blue-grey" onclick="w3_open()" style="display: none">&#9776;</span>
    <div id="content">
    </div>
</div>
</body>
</html>
