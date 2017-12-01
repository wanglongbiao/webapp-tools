<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/arcgisSettings.js"></script>
    <script type="text/javascript" src="${ctx}/webjars/jquery/1.12.4/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery-ui.js"></script>
    <link rel='stylesheet' href='${ctx}/css/jquery-ui.css'>

    <script type="text/javascript" src="${ctx}/webjars/bootstrap/3.1.0/js/bootstrap.js"></script>
    <script type="text/javascript" src="${ctx}/webjars/jquery-colorbox/1.6.4/jquery.colorbox.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.ztree.all.js"></script>
    <link rel='stylesheet' href='${ctx}/webjars/bootstrap/3.1.0/css/bootstrap.css'>

    <link rel="stylesheet" type="text/css" href="${ctx}/js/arcgis_js_api/library/3.22/esri/css/esri.css"/>
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

    <style type="text/css">
        html, body, .container {
            height: 100%;
            width: 100%;
        }

    </style>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
    <%--<title>漳州市乡镇船舶管理服务平台</title>--%>

    <%--<script src="${ctx}/js/main-map.js"></script>--%>
    <script src="${ctx}/js/ship-view.js"></script>
    <%--<script src="${ctx}/js/ship/ship-ztree.js"></script>--%>

</head>
<body>
<div id="viewDiv">
        <span id="info"
              style="position:absolute; right:15px; bottom:5px; color:#000; z-index:50;">按住 shift 移动鼠标，可显示坐标</span>
</div>

</body>
</html>
