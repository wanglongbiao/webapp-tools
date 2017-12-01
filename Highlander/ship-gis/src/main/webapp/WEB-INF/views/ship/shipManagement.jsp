<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
    <%--<title>漳州市乡镇船舶管理服务平台</title>--%>

    <%--<script src="${ctx}/js/main-map.js"></script>--%>
    <script src="${ctx}/js/ship-map.js"></script>
    <script src="${ctx}/js/ship/ship-ztree.js"></script>

    <script type="text/javascript">
        $(function () {
//            $("#realDataDialogDiv").draggable();
//            $("#cboxLoadedContent").draggable();
//            $("#cboxLoadedContent").draggable({ appendTo: 'body' });


            $(document).bind('cbox_complete', function () {
                setTimeout(function () {
                    $("#cboxLoadedContent").draggable()
                }, 1500);
            });
        });


    </script>
</head>
<body>


<div id="center">
    <div id="left">
        <ul id="addrTree2" class="ztree"></ul>
    </div>

    <div id="right">

        <div id="viewDiv">
        <span id="info"
              style="position:absolute; right:15px; bottom:5px; color:#000; z-index:50;">按住 shift 移动鼠标，可显示坐标</span>
        </div>
    </div>
</div>


</body>
</html>
