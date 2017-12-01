<%--
  Created by IntelliJ IDEA.
  User: w
  Date: 2017/9/28
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/taglibs.jsp" %>
    <title>Title</title>

    <script type="text/javascript">
        $(function () {
//            $("#realDataDialogDiv").draggable();
//            $("#cboxLoadedContent").draggable({ containment: 'document' });
//            $("#cboxLoadedContent").draggable({ appendTo: 'body' });
        });
    </script>
    <style type="text/css">
        .block-div {
            border: 1px solid black;
            /*padding-top: 20px;*/
            margin: 20px 20px;
        }

        .block-div button {
            padding: 20px 20px 20px 20px;
        }
    </style>
</head>
<body>
<div id="realDataDialogDiv" class="ui-widget-content">
    <div class="block-div">
        <div class="h4"><strong>设备信息</strong></div>
        <table class="table">
            <tr>
                <td>编号：${entity.devName}</td>
                <td>船首向：${entity.heading}</td>
            </tr>
            <tr>
                <td>纬度：${entity.lat / 1e6 }° N</td>
                <%--<td>纬度：${(int)(entity.lat / 1000000)}°${entity.lat % 1000000 / 10000}'${entity.lat % 1000000 % 60}'' N</td>--%>
                <td>航迹向：${entity.course}</td>
            </tr>
            <tr>
                <td>经度：${entity.lon / 1e6}° E</td>
                <td>船速：${entity.speed / 100} km/h</td>
            </tr>
            <tr>
                <td>信号：${entity.signal}</td>
                <td>电量：${entity.bat}</td>
            </tr>
            <tr>
                <td>时间：<fmt:formatDate value="${entity.postime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td></td>
            </tr>
        </table>
    </div>
</div>
<div class="block-div">
    <div class="h4"><strong>船舶信息</strong></div>
    <table class="table">
        <tr>
            <td>船名：${shipInfo.name}</td>
            <td>类型：${shipInfo.type}</td>
        </tr>
        <tr>
            <td>单位：${shipInfo.unit}</td>
            <td>常泊港：${shipInfo.port}</td>
        </tr>
        <tr>
            <td>用途：${shipInfo.purpose}</td>
            <td>材质：${shipInfo.material}</td>
        </tr>
        <tr>
            <td>吨位：${shipInfo.tonnage}</td>
            <td>马力：${shipInfo.power}</td>
        </tr>
        <tr>
            <td>船主：${shipInfo.owner}</td>
            <td>电话：${shipInfo.tele}</td>
        </tr>
        <tr>
            <td colspan="2">地址：${shipInfo.addr}</td>
        </tr>
    </table>
</div>
<div class="">
    <button class="btn" id="report">报警</button>
    <button class="btn" id="near">附近</button>
    <button class="btn" id="focus">关注</button>
    <button class="btn" id="track">轨迹</button>
</div>
</body>
</html>
