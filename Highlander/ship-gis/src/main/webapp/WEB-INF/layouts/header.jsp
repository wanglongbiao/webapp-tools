<%@ page pageEncoding="UTF-8" %>
<%@ include file="/taglibs.jsp" %>
<div class="header h-100">
    <div>
            <%--<h2>漳州市乡镇船舶管理服务平台</h2>--%>
            <h2>平台</h2>
    </div>

    <div>
        <div class="col-md-3 form-inline align-bottom">
            <c:if test="${user ne null and user.id != null}">
                <input id="searchText" class="form-control" type="text" name="searchText">
                <button id="searchShip" class="btn btn-primary" type="button">搜索</button>
            </c:if>
        </div>
        <div class="col-md-5">

        </div>
        <div class="col-md-2">
            <c:if test="${user ne null  and user.id != null}">
                <%--<span>当前登录用户：${user.userName}</span>--%>
            </c:if>
        </div>
        <div class="col-md-2">
            <c:if test="${user ne null  and user.id != null}">
                <button class="btn" type="button" onclick="location.href='${ctx }/user/logout.do'">退出登录</button>
            </c:if>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        $("#searchShip").click(function () {
            var searchText = $("#searchText").val();
            if (searchText == null || searchText.length == 0) {
                console.log('search text can not be null.');
                alert("搜索内容不能为空");
                return;
            }
            var url = "${ctx}/ship/colorbox/shipSearch.do?searchText=" + searchText;
            $.colorbox({
                href: url,
                iframe: true,
                width: "80%",
                height: "60%",
            });
        })
    });
</script>