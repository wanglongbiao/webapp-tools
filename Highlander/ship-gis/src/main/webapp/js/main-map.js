//require(["esri/geometry/Extent"], function(Extent) { /* code goes here */ });
var map, view;


var precisionLength = 6; // 将经纬度的精度显示为小数点后 6 位
var updateShipInterval = 5; // 定时更新海图上的目标间隔，单位是秒

// ZZ
// var mapServerUrl = "http://localhost:8080/arcgis/rest/services/ZhangZhou1MapService/MapServer";
var mapServerUrl = "http://localhost:6080/arcgis/rest/services/ZhangZhou1MapService/MapServer";

// var mapServerUrl = "http://172.16.60.93:8080/arcgis/rest/services/ZhangZhou1MapService/MapServer";
require(["esri/Map", "esri/Basemap",
        "esri/Graphic",
        "esri/PopupTemplate",
        "esri/views/MapView",
        "esri/layers/TileLayer",
        "esri/layers/FeatureLayer",
        "esri/layers/GraphicsLayer",
        "esri/symbols/SimpleLineSymbol",
        "esri/symbols/PictureMarkerSymbol",
        "esri/geometry/Extent",
        "esri/geometry/Point", "esri/geometry/Polyline", "esri/geometry/SpatialReference", "esri/geometry/support/webMercatorUtils", "dojo/dom", "dojo/domReady!"],

    function (Map,
              Basemap,
              Graphic,
              PopupTemplate,
              MapView,
              TileLayer,
              FeatureLayer,
              GraphicsLayer,
              SimpleLineSymbol,
              PictureMarkerSymbol,
              Extent,
              Point,
              Polyline,
              SpatialReference, webMercatorUtils, dom) {

        // 瓦片图层
        var tileLayer = new TileLayer({
            url: mapServerUrl,
        });

        // 用来创建自定义基图
        var customBasemap = new Basemap({
            baseLayers: [tileLayer],
            title: "Custom Basemap Title",
            id: "myBasemap"
        });

        // 用来保存，加载，管理图层，是一个图层容器
        map = new Map({
            // basemap : "streets"
            basemap: customBasemap,
        });

        // 目标图层，用来存放目标
        var shipLayer = new GraphicsLayer({
            id: "shipLayer",
        });
        // 电子围栏图层
        var railLayer = new GraphicsLayer({
            id: "railLayer",
        });

        map.add(shipLayer);
        map.add(railLayer);
        // 用来显示图层和与图层交互
        view = new MapView({
            // center: [118.007104, 24.227488], // 漳州
            // center: [117.629242, 24.395882], // 漳州
            center: [117.985611, 24.312253], // 漳州
            container: "viewDiv",
            map: map,
            zoom: 3,
        });
        view.ui.remove("zoom");// 去掉放大，缩小按钮


        // 定时更新目标数据
        // 每隔 5s 获取一次最新数据，并移动目标
        updateShipObject(updateShipInterval);

        function updateShipObject(interval) {
            showRealData();
            setInterval(function () {
                // 这里放一个 ajax 方法，获取目标，返回一个 json 数组，json 对象应包括船舶的 id，经纬度
                showRealData();
            }, interval * 1e3);
        }

        // 显示实时数据, 先删除旧的，再添加新的
        // 不能删除，只能根据ID一个个地更新目标，保留目标的一些属性
        function showRealData() {
            var url = ctx + "/ship/getRealDataList.do";
            $.ajax({
                url: url,
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    deleteGraphics(shipLayer);
                    var list = data.list;
                    for (var i in list) {
                        var realData = list[i];

                        var oldGraphics = shipLayer.graphics;
                        if (oldGraphics) {
                            for (var j in oldGraphics) {
                                var oldGraphicsId = oldGraphics.id;
                                if (realData.id == oldGraphicsId) {// 如果找到旧的，则更新坐标等信息


                                    // oldGraphics 找到后在数组中删除
                                    break;
                                }
                            }

                            // 找不到旧的，则添加新的
                        }

                        //删除最新数据里没有的目标

                        addGraphic(realData);// 东山县附近
                        // addOrUpdateGraphic(realData);
                    }
                }
            })
        }

        // 在海图上添加一个目标
        function addGraphic(realData) {
            // console.log('add graphic:' + realData)
            var shipInfo = realData.shipInfo;

            if (shipInfo == null) {
                console.log("null....");
            }
            // lon = lon.toFixed(6);
            // console.log("realData.lon / 1e6: " + realData.lon / 1e6);
            // console.log("realData.devName: " + realData.devName);
            // ################
            var point = new Point({
                // spatialReference : 3857,
                // wkid : 3857,
                longitude: realData.lon / 1e6,
                latitude: realData.lat / 1e6,
            });

            point = offset(point, 'obj');

            var pictureSymbol = new PictureMarkerSymbol({
                url: ctx + "/img/blue-circle.png",
                width: "10px",
                height: "10px"
            });

            // 1. create pointGraphic
            var pointGraphic = new Graphic({// A Graphic is a vector representation
                id: realData.id,
                // console.log()
                // of real world geographic phenomena

                geometry: point,
                symbol: pictureSymbol,
                attributes: {
                    // id: pointGraphicId,
                    // id: realData.id,
                    title: '目标详情',
                    '目标': realData.devName,
                    '电量': realData.bat / 100 + '%',
                    '地址': shipInfo != null ? shipInfo.addr : '',
                    '最后发送时间': realData.postime,
                    '最后心跳时间': realData.httime,
                    '经度': realData.lon / 1e6,//.toFixed(precisionLength)
                    '纬度': realData.lat / 1e6,
                },
                popupTemplate2: {
                    title: "{title}",
                    content: [{
                        type: "fields",
                        fieldInfos: [{
                            fieldName: "目标"
                        }, {
                            fieldName: "电量"
                        }, {
                            fieldName: "地址"
                        }, {
                            fieldName: "最后发送时间"
                        }, {
                            fieldName: "最后心跳时间"
                        }, {
                            fieldName: "经度"
                        }, {
                            fieldName: "纬度"
                        },]
                    }]
                }
            });


            // updateShipGraphic(shipLayer, oldGraphic, newGraphic);
            console.log("id:" + pointGraphic.id);
            shipLayer.graphics.add(pointGraphic);
        }


        view.on("click", function (event) {
            view.hitTest(event).then(getGraphics);
        });

        // 点击目标后弹框
        function getGraphics(response) {
            // do something with the result graphic
            var graphic = response.results[0].graphic;
            updateShipGraphic(shipLayer, graphic, null);
            return;
            if (graphic != null) {
                // 将图标变色
                // graphic.remove();
                graphic.symbol.url = ctx + "/img/a1.png";
                // graphic.symbol.url = ctx + "/img/a1.png";
                graphic.symbol.set("url", ctx + "/img/a1.png");
                var url = ctx + "/ship/colorbox/getRealData.do?id=" + graphic.attributes.id;
                console.log("get real data url:" + url);
                $.colorbox({
                    href: url,
                    iframe: true,
                    width: "30%",
                    height: "50%",
                }).draggable();
                // graphic.symbol.width = 100;
                // graphic.symbol.set('url', ctx + "/img/a2.png");
                // console.log(pointGraphic.customm);
                // view.center =view.center;

            }
        }

        // 开始画长方形, 显示漳州范围
        var polyline = new Polyline({
            paths: [[116.633281230926, 22.956352615356], [116.633281230926, 24.455133152008], [118.665751934051, 24.455133152008], [118.665751934051, 22.956352615356],
                [116.633281230926, 22.956352615356],]
        });

        var lineSymbol = new SimpleLineSymbol({
            color: [226, 119, 40], // RGB color values as an array
            width: 1
        });

        var polylineGraphic = new Graphic({
            geometry: polyline, // Add the geometry created in step 4
            symbol: lineSymbol, // Add the symbol created in step 5
            // attributes: lineAtt // Add the attributes created in step 6
        });

        view.graphics.add(polylineGraphic);


// 添加电子围栏
        var getRailsUrl = ctx + "/ship/getRails.do";
        $.ajax({
            url: getRailsUrl,
            type: 'post',
            dataType: 'json',
            // async:false,
            success: function (data) {
                var list = data.list;
                for (var i in list) {
                    var pointArr = [];
                    var rail = list[i];
                    pointArr.push([rail.startLon / 1e6, rail.startLat / 1e6]);
                    pointArr.push([rail.endLon / 1e6, rail.endLat / 1e6]);
                    addRail(pointArr);
                }
            }
        });


        var railSymbol = new SimpleLineSymbol({
            color: 'green', // RGB color values as an array
            width: '2px',
        });


// 添加一个电子围栏线，加了个偏移
        function addRail(pointArr) {
            // console.log("pointArr:" + pointArr);
            for (var i  in pointArr) {
                var p = pointArr[i];
                p[1] -= 0.145000;
                pointArr[i] = p;
            }
            // offset(pointArr);
            console.log("pointArr:" + pointArr);
// console.log("add rail##" + pointArr);
            var polyline = new Polyline({
                paths: pointArr,
            });

            var railGraphic = new Graphic({
                geometry: polyline,
                symbol: railSymbol,
            });

            railLayer.graphics.add(railGraphic);
        }


// 添加坐标显示
// dojo.connect(map, "onMouseMove", showCoordinates);
        // 鼠标移动事件
        view.on("pointer-move", ["Shift"], showCoordinates);


        // 拖拽事件
        view.on("drag", handleDrag);

        function handleDrag(evt) {
            // console.log("view drag" + evt);
        }

// console.log("start connect23");

        function showCoordinates(evt) {

            var point = view.toMap({x: evt.x, y: evt.y});
            point = offset(point, 'sea');
            // console.log("move",point);
            //the map is in web mercator but display coordinates in geographic (lat, long)
            // var mp = webMercatorUtils.webMercatorToGeographic(evt.mapPoint);
            // point = webMercatorUtils.webMercatorToGeographic(point);
            // console.log("mp:" + mp );
            //display mouse coordinates
            // dom.byId("info").innerHTML = mp.x.toFixed(3) + ", " + mp.y.toFixed(3);
            dom.byId("info").innerHTML = point.longitude.toFixed(precisionLength) + ", " + point.latitude.toFixed(precisionLength);
            console.log(point.longitude.toFixed(precisionLength) + ", " + point.latitude.toFixed(precisionLength));
        }

        // graphics click event
        view.on("click", function (event) {
            view.hitTest(event.screenPoint).then(function (response) {
                var graphics = response.results;
                console.log("graphics length:" + graphics.length());
                alert("ok");
                graphics.forEach(function (graphic) {
                    console.log(graphic);
                });
            });
        });

    });


// 更新目标在海图上的显示位置
function updateShipGraphic(layer, old, newGraphic) {
    if (old) {
        layer.remove(old);
    }
    if (newGraphic) {
        layer.add(newGraphic);
    }
}

// 修正偏移，lati差了145000，如果处理海图是加，那处理目标就是减
function offset(point, type) {
    if (point) {
        if (type && type == 'sea') {
            point.latitude += 0.145000;
        } else {
            point.latitude -= 0.145000;
        }
    }

    return point;
}

// 更新所有的图层
function deleteGraphics(layer) {
    // console.log("before remove all")
    layer.graphics.removeAll();
    // console.log("after remove all")
}


