//require(["esri/geometry/Extent"], function(Extent) { /* code goes here */ });
var map, view;


var precisionLength = 6; // 将经纬度的精度显示为小数点后 6 位
var updateShipInterval = 5; // 定时更新海图上的目标间隔，单位是秒
var offsetVal = 0.145000; //偏移值


require(["esri/map",
        "esri/basemaps",
        "esri/graphic",
        "esri/layers/ArcGISTiledMapServiceLayer",
        "esri/layers/GraphicsLayer",
        "esri/geometry/Point",
        "esri/geometry/Polyline",
        "esri/geometry/Circle",
        "esri/geometry/Extent",
        "esri/symbols/SimpleFillSymbol",
        "esri/symbols/PictureMarkerSymbol",
        "esri/symbols/SimpleLineSymbol",
        "esri/Color",
        "esri/SpatialReference",
        "esri/geometry/webMercatorUtils",
        "dojo/dom",
        "dojo/dom-attr",
        "dojo/domReady!"],
    function (Map,
              esriBasemaps,
              Graphic,
              ArcGISTiledMapServiceLayer,
              GraphicsLayer,
              Point,
              Polyline,
              Circle,
              Extent,
              SimpleFillSymbol,
              PictureMarkerSymbol,
              SimpleLineSymbol,
              Color,
              SpatialReference,
              webMercatorUtils,
              dom,
              domAttr) {



        map = new Map("viewDiv", {
            center: [117.385611, 23.412253], // 漳州
            zoom: 2,
            // extent: startExtent,
        });


        // map.spatialReference = new SpatialReference({wkid: 102100});
        // console.log(map.spatialReference);
        // map.set("spatialReference", new SpatialReference({wkid: 102100}));

        var tileLayer = new ArcGISTiledMapServiceLayer(mapServerUrl);
        // tileLayer.spatialReference = new SpatialReference({wkid:102100});

        var rangeLayer = new GraphicsLayer({// 用来显示边界范围
            id: "rangeLayer",
        });

        var shipLayer = new GraphicsLayer({// 目标图层，用来存放目标
            id: "shipLayer",
        });

        var railLayer = new GraphicsLayer({// 电子围栏图层
            id: "railLayer",
        });

        map.addLayer(tileLayer);
        // map.addLayer(shipLayer);
        // map.addLayer(railLayer);
        // map.addLayer(rangeLayer);


        // init();

        function init() {
            initRangeLayer();
            initRailLayer();
            updateShipObject(updateShipInterval);// 定时更新目标信息

            shipLayer.on("click", graphicClickEventHandler);// 点击地图事件
            map.on("mouse-move", showCoordinates);// 鼠标移动后显示坐标
            // map.on("click", clickMap);// 鼠标移动后显示坐标
        }

        function initRangeLayer() {// 开始画长方形, 显示漳州范围
            var rangePolyline = new Polyline({
                paths: [[[116.633281230926, 22.956352615356], [116.633281230926, 24.455133152008], [118.665751934051, 24.455133152008], [118.665751934051, 22.956352615356],
                    [116.633281230926, 22.956352615356]]]
            });

            var rangeSymbol = new SimpleLineSymbol({
                color: [226, 119, 40]
            });

            var rangeGraphic = new Graphic(rangePolyline, rangeSymbol);

            rangeLayer.add(rangeGraphic);
        }


        function initRailLayer() {// 添加一个电子围栏线，
            var railSymbol = new SimpleLineSymbol({
                color: new Color("green"), // RGB color values as an array
                // width: 2,
            });
            var getRailsUrl = ctx + "/ship/getRails.do";
            $.ajax({
                url: getRailsUrl,
                type: 'post',
                dataType: 'json',
                // async:false,
                success: function (data) {
                    var railList = data.list;
                    for (var i in railList) {
                        var pointArr = [];
                        var rail = railList[i];
                        pointArr.push([rail.startLon / 1e6, rail.startLat / 1e6]);
                        pointArr.push([rail.endLon / 1e6, rail.endLat / 1e6]);
                        for (var j  in pointArr) {
                            var p = pointArr[j];
                            p[1] -= 0.145000;//加了个偏移
                            pointArr[j] = p;
                        }

                        var railPolyline = new Polyline({
                            paths: [pointArr],
                        });

                        var railGraphic = new Graphic(railPolyline, railSymbol);
                        railLayer.add(railGraphic);
                    }
                }
            });

        }


        // #########
        function updateShipObject(interval) {
            showRealData();
            setInterval(function () {
                // 这里放一个 ajax 方法，获取目标，返回一个 json 数组，json 对象应包括船舶的 id，经纬度
                showRealData();
            }, interval * 1e3);
        }

        // 显示实时数据, 先删除旧的，再添加或更新新的
        function showRealData() {
            var url = ctx + "/ship/getRealDataList.do";
            $.ajax({
                url: url,
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    // deleteGraphics(shipLayer);
                    var oldGraphicArr = shipLayer.graphics;
                    var realDataList = data.list;

                    if (oldGraphicArr && oldGraphicArr.length > 0) {//检测并删除旧的（在新的列表里不存在的数据）
                        for (var i in oldGraphicArr) {
                            var oldGraphic = oldGraphicArr[i];
                            var find = false;
                            for (var j in realDataList) {
                                if (realDataList[j].id == oldGraphic.attributes.id) {
                                    // console.log("find old id,continue:" + realDataList[j].id);
                                    find = true;
                                    break;
                                }
                            }

                            if (!find) {
                                // console.log("will remove:" + oldGraphic.attributes.id);
                                shipLayer.remove(oldGraphic);
                            }
                        }
                    }

                    oldGraphicArr = shipLayer.graphics;

                    for (var i in realDataList) {
                        var realData = realDataList[i];

                        var find = false;
                        if (oldGraphicArr && oldGraphicArr.length) {
                            for (var j in oldGraphicArr) {
                                var oldGraphicId = oldGraphicArr[j].attributes.id;
                                if (realData.id == oldGraphicId) {// 如果找到旧的，则更新坐标等信息
                                    addOrUpdateGraphic(realData, oldGraphicId);
                                    find = true;
                                    // oldGraphics 找到后在数组中删除
                                    break;
                                }
                            }
                        }

                        if (!find) {
                            addOrUpdateGraphic(realData);// 找不到旧的，则添加新的
                        }
                    }

                    shipLayer.redraw();
                }
            });
        }

        function addOrUpdateGraphic(realData, oldGraphicId) {// 在海图上添加或者更新一个目标
            var point = new Point({
                longitude: realData.lon / 1e6,
                latitude: realData.lat / 1e6,
            });

            point = offset(point, 'obj');// 偏移目标

            if (oldGraphicId) {//update
                var oldGraphicArr = shipLayer.graphics;

                for (var i in oldGraphicArr) {
                    var oldGraphic = oldGraphicArr[i];
                    if (oldGraphic.attributes.id == oldGraphicId) {
                        // console.log("update old graphics...");
                        point.longitude += 0.1;
                        point.latitude += 0.1;
                        oldGraphic.setGeometry(point);
                        break;
                    }
                }


            } else {//add
                // console.log('add graphic:' + realData)
                var shipInfo = realData.shipInfo;


                var pictureSymbol = new PictureMarkerSymbol({
                    url: ctx + "/img/blue-circle.png",
                    width: 10,
                    height: 10,
                });

                var pointGraphic = new Graphic(point, pictureSymbol, {id: realData.id, isSelected: false});

                shipLayer.add(pointGraphic);
            }

        }

        function graphicClickEventHandler(event) {//目标左键点击事件
            var graphic = event.graphic;
            // updateShipGraphic(shipLayer, graphic, null);

            if (graphic != null) {
                graphic.symbol.url = ctx + "/img/red-circle.png";
                graphic.attributes.isSelected = true;
                shipLayer.redraw();

            }
            // return;
            showShipBox(graphic.attributes.id);
        }


        function showCoordinates(evt) {// 显示坐标
            if (!evt.shiftKey) {
                return;
            }

            var point = evt.mapPoint;

            offset(point, "map");

            dom.byId("info").innerHTML = point.getLongitude().toFixed(precisionLength) + ", " + point.getLatitude().toFixed(precisionLength);
            // console.log(point.longitude.toFixed(precisionLength) + ", " + point.latitude.toFixed(precisionLength));
        }

        // #### end ####
    }
);


// 修正偏移，lati 差了145000，如果显示地图坐标是加，地图上的目标是减
function offset(point, type) {
    if (point) {
        if (type && type == 'map') {
            point.setLatitude(point.getLatitude() + offsetVal)
        } else {
            point.setLatitude(point.getLatitude() - offsetVal)
        }
    }

    return point;
}


// 点击目标后弹窗，显示详细信息
function showShipBox(id) {
    var url = ctx + "/ship/colorbox/getRealData.do?id=" + id;
    $.colorbox({
        href: url,
        iframe: true,
        opacity: 0,
        closeButton: false,
        fastIframe: false,
        width: "30%",
        height: "50%",
        onComplete: function () {
            // alert("length:" + $("#cboxLoadedContent").height());
            // $("#colorbox").css("position","static");
            // $("#cboxLoadedContent").attr("position","static");
            $("#colorbox").css("background", "grey");
            $("#colorbox").draggable({iframeFix: true});
            // $("#realDataDialogDiv").draggable();
            // $("#cboxLoadedContent").draggable().prependTo("body");

        }
    });


}


function l(msg) {
    console.log(msg)
}