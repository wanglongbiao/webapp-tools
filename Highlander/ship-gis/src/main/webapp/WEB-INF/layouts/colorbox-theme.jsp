<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${ctx}/webjars/jquery/1.12.4/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/webjars/bootstrap/3.1.0/js/bootstrap.js"></script>
    <link rel='stylesheet' href='${ctx}/webjars/bootstrap/3.1.0/css/bootstrap.css'>
    <link rel='stylesheet' href='${ctx}/css/colorbox.css'>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="Expires" content="0"/>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <title><sitemesh:title/></title>
    <sitemesh:head/>
</head>

<body>
<sitemesh:body/>

</body>
</html>