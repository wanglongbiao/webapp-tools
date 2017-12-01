<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/webjars/jquery/1.12.4/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui.js"></script>
    <link rel='stylesheet' href='${ctx}/css/jquery-ui.css'>

    <script type="text/javascript" src="${ctx}/webjars/bootstrap/3.1.0/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/webjars/jquery-colorbox/1.6.4/jquery.colorbox.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.ztree.all.js"></script>
    <link rel='stylesheet' href='${ctx}/webjars/bootstrap/3.1.0/css/bootstrap.css'>

    <link rel="stylesheet" type="text/css" href="${ctx}/js/arcgis_js_api/library/3.22/esri/css/esri.css"/>
    <script type="text/javascript" src="${ctx}/js/arcgisSettings.js"></script>
    <script src="${ctx}/js/arcgis_js_api/library/3.22/init.js"></script>

    <%--<script type="text/javascript" src="${ctx}/js/arcgis_js_api/library/4.4/dojo/dojo.js"></script>--%>
    <%--<link rel="stylesheet" href="${ctx}/js/arcgis_js_api/library/4.4/dijit/themes/claro/claro.css"/>--%>
    <%--<link rel="stylesheet" href="${ctx}/js/arcgis_js_api/library/4.4/esri/css/main.css"/>--%>

    <link rel='stylesheet' href='${ctx}/css/zTreeStyle/zTreeStyle.css'>
    <link rel='stylesheet' href='${ctx}/css/colorbox.css'>
    <link rel='stylesheet' href='${ctx}/css/layout.css'>
    <%-- <script type="text/javascript" src="${ctx }/static/js/common/utils.js"></script> --%>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="Expires" content="0"/>

    <title><sitemesh:title/></title>
    <sitemesh:head/>
    <style type="text/css">
        html, body, .container {
            height: 100%;
            width: 100%;
        }

    </style>
</head>

<body>
<div class="content fill">
    <%@include file="/WEB-INF/layouts/header.jsp" %>
    <sitemesh:body/>
</div>
<%-- 		<%@ include file="/WEB-INF/layouts/footer.jsp"%> --%>

</body>
</html>