<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
    <title>Test Map</title>
    <script type="text/javascript">
        var ctx = "${ctx}";

    </script>


</head>
<body>
<div>
    <table class="table table-hover">
        <tr>
            <th>序号</th>
            <th>名称</th>
            <th>经度</th>
            <th>纬度</th>
            <th>最后活跃时间</th>
        </tr>

        <c:forEach items="${list }" var="item" varStatus="status">
            <tr>
                <td>${status.index +1 }</td>
                <td>${item.shipName }</td>
                <td>${item.lon / 1e6 }</td>
                <td>${item.lat / 1e6}</td>
                <td><fmt:formatDate value="${item.lastActiveDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>