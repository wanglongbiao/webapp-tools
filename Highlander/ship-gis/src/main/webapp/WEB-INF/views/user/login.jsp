<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/taglibs.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--<link rel="stylesheet" href="${ctx}/css/signin.css">--%>
    <style type="text/css">
        .divcenter {
            position: absolute;
            top: 40%;
            left: 40%;
        }
    </style>
    <title>登录</title>
</head>
<body>
<div class="divcenter">
    <form action="${ctx }/user/login.do" method="post" class="form">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input id="userName" name="userName" type="text" value="zhangsan"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input id="password" name="password" type="password" value="123456"></td>
            </tr>
            <tr>
                <td colspan="2"><input class="btn-primary" type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>