var setting = {
    view: {
        // dblClickExpand: false,
        showLine: true,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: "-1"
        },
        key: {
            name: "addrName"

        }
    },
    callback: {
        beforeClick: function (treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("addrTree");
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);
                return false;
            } else {
                // demoIframe.attr("src", treeNode.file + ".html");
                return true;
            }
        }
    }
};


var zNodes = [];

// alert(zNodes2);

// var zNodes = null;
var getTreeUrl = ctx + "/addr/getAddrTreeList.do"
$.ajax({
    url: getTreeUrl,
    type: 'post',
    dataType: 'json',
    async: false,
    success: function (data) {
        // console.log(data.addrList);
        var list = data.addrList;
        for (var i in list) {
            // list[i].name = list[i].addrName;
            if (list[i].addrLevel <= 1) {
                list[i].open = true;
            }
            // console.log(list[i].parentId);
        }
        // alert(list[0].addrName);


        // alert(1);
        zNodes = list;
    }
})
$(document).ready(function () {
    $.fn.zTree.init($("#addrTree"), setting, zNodes);
});