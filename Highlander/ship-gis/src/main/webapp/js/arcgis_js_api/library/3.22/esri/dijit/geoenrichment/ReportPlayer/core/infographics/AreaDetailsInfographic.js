// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.22/esri/copyright.txt for details.
//>>built
require({cache:{"url:esri/dijit/geoenrichment/ReportPlayer/core/templates/AreaDetailsInfographic.html":'\x3cdiv class\x3d"esriGEAreaDetailsInfographic"\x3e\r\n    \x3cdiv data-dojo-attach-point\x3d"noDetailsPlaceHolder" class\x3d"areaDetails_noDetailsPlaceHolder"\x3e\r\n        \x3cdiv class\x3d"areaDetails_noDetailsPlaceHolderImage"\x3e\x3c/div\x3e\r\n        \x3cdiv class\x3d"areaDetails_noDetailsPlaceHolderLabel"\x3e${nls.noDetails}\x3c/div\x3e\r\n    \x3c/div\x3e\r\n    \x3cdiv data-dojo-attach-point\x3d"contentDiv"\x3e\x3c/div\x3e\r\n\x3c/div\x3e\r\n\r\n\r\n'}});
define("esri/dijit/geoenrichment/ReportPlayer/core/infographics/AreaDetailsInfographic","dojo/_base/declare dojo/_base/lang dojo/dom-construct dojo/dom-style dijit/_WidgetBase dijit/_TemplatedMixin esri/dijit/geoenrichment/_Invoke ./infographicUtils/InfographicThemeUtil ./infographicUtils/InfographicLayoutResizer ../supportClasses/TableUtil esri/dijit/geoenrichment/utils/DomUtil esri/dijit/geoenrichment/utils/ObjectUtil ./supportClasses/AreaDetailsLayouts esri/dijit/geoenrichment/ReportPlayer/core/sections/SectionTypes dojo/text!../templates/AreaDetailsInfographic.html dojo/i18n!../../../../../nls/jsapi".split(" "),
function(u,q,H,l,v,w,x,y,z,g,A,B,t,C,D,m){function E(){return r?r:r={getAttributes:function(){return[{alias:"Building Area (sq. feet)",value:1E3},{alias:"Frontage (feet)",value:100},{alias:"Parking",value:30},{alias:"Site Area (sq. feet)",value:500.5},{alias:"Number of Employees",value:300},{alias:"Sales Volume ($)",value:15E5}]},getNotes:function(){return[{text:"This is a placeholder note. Here will be displayed some information about the area."},{text:"This is a placeholder note. Here will be displayed some information about the area."}]}}}
m=m.geoenrichment.dijit.ReportPlayer.ReportPlayer;var r,F={_calcBuildInfo:function(a,b){if(!a)return null;var h=a.getAttributes()||[],c=a.getNotes()||[];!1===b.showAttributes&&(h=[]);!1===b.showNotes&&(c=[]);if(!h.length&&!c.length)return null;var n=b.attributesLayout==t.ATTRS_2COLUMNS,k=n?Math.round(h.length/2):h.length,k=(k?k+1:0)+(c.length?c.length+1:0),d=b.style.height-g.getTableHeight(b.header)-1;return{attrs:h,notes:c,numRows:k,numColumns:n?4:2,width:b.style.width,rowH:Math.max(g.DEFAULT_ROW_HEIGHT,
d/k)}},buildStack:function(a,b,h,c,n){function k(a,b,d){b=q.mixin({verticalAlign:"middle",horizontalAlign:b?"right":void 0,horizontalPadding:a?5:20},d?h:c);a&&(b.fontSize*=1.2);n&&(b.backgroundColor=n);return b}var d=this._calcBuildInfo(a,b);if(!d)return null;var e=g.createTable({numColumns:d.numColumns,numRows:d.numRows,style:{width:d.width},useDefaultTheme:!1});e.scaleToFitHeight=!0;var f=0;if(d.attrs.length){a=function(a,b){b=b||0;g.modifyTableJson(e,f,0+b,{text:a?a.alias:"",cellStyle:k(!1,!1,
!0),height:d.rowH});g.modifyTableJson(e,f,1+b,{text:a?B.formatNumber(a.value):"",cellStyle:k(!1,!0,!0)})};b=b.attributesLayout==t.ATTRS_2COLUMNS;g.modifyTableJson(e,f,0,{text:m.attributes,columnSpan:d.numColumns,cellStyle:k(!0,!1,!0),height:d.rowH});f++;for(var l=0;l<d.attrs.length;l++){var p=d.attrs[l];a(p);b&&(p=d.attrs[++l],a(p,d.numColumns/2));f++}}d.notes.length&&(g.modifyTableJson(e,f,0,{text:m.notes,columnSpan:d.numColumns,cellStyle:k(!0),height:d.rowH}),f++,d.notes.forEach(function(a){g.modifyTableJson(e,
f,0,{text:a.text,columnSpan:d.numColumns,cellStyle:k(),height:d.rowH});f++}));return[e]}},G={adjustContent:function(a){var b=[];a.getFieldCells().forEach(function(a){if(a.valueLabel&&a.valueLabel.innerHTML){var c=b[a.gridData.index]=b[a.gridData.index]||{};c.cells=c.cells||[];c.cells.push(a);c.h=Math.max(c.h||0,a.getHeight());c.minH=Math.max(c.minH||0,l.get(a.valueLabel,"height"))}});var h=0,c=0,g=[],k=[],d;for(d in b){var e=b[d];if(e.h>e.minH){var f=e.h-e.minH,h=h+f;g.push({cells:e.cells,maxHR:f,
rowH:e.h})}else e.h<e.minH&&(f=e.minH-e.h+4,c+=f,k.push({cells:e.cells,hInc:f,rowH:e.h}))}if(0<c){var m=Math.min(1,c/h),p=Math.min(1,h/c);g.forEach(function(b){b.cells.forEach(function(c){a.setCellHeight(c,b.rowH-b.maxHR*m)})});k.forEach(function(b){b.cells.forEach(function(c){a.setCellHeight(c,b.rowH+b.hInc*p)})})}}};return u([v,w,x],{templateString:D,nls:m,viewModel:null,themeContext:null,theme:null,_sections:null,_currentInfographicJson:null,postCreate:function(){this.inherited(arguments);this._showEmptyView(!1)},
_getInfographicBackgroundColor:function(){var a=this.viewModel.getStaticInfographicDefaultStyles(this.theme||this.themeContext);return this._currentInfographicJson.style.backgroundColor||a&&a.backgroundColor},updateInfographic:function(a){this.domNode&&(this._currentInfographicJson=this._applyThemeToJson(a),l.set(this.domNode,"backgroundColor",this._getInfographicBackgroundColor()),this.invoke("_doUpdateContent",50))},_resizeContent:function(){z.resizeLayout(this._currentInfographicJson,{w:this.width,
h:this.height});l.set(this.domNode,{width:this.width+"px",height:this.height+"px"});setTimeout(function(){l.set(this.noDetailsPlaceHolder,"paddingTop",(this.height-l.get(this.noDetailsPlaceHolder,"height"))/2+"px")}.bind(this))},_doUpdateContent:function(){this._resizeContent();this._createSections()},_getDetailsSectionStack:function(){var a=this.viewModel.dynamicReportInfo?this.viewModel.dynamicReportInfo.attachmentsStore:E();return F.buildStack(a,this._currentInfographicJson,this._getAttributesStyle(),
this._getNotesStyle(),this._getInfographicBackgroundColor())},_createSections:function(){this._destroySections();var a=this._getDetailsSectionStack();if(a){this._showEmptyView(!1);var b=this._currentInfographicJson.header;this._createSection([b],0,!0);this._createSection(a,g.getTableHeight(b))}else this._showEmptyView(!0)},_createSection:function(a,b,h){var c={"class":"infographicContainer_Section"};c.initialWidth=g.getTableWidth(a[0]);c.json={type:C.DETAILS,stack:a};c.viewModel=this.viewModel;c.themeContext=
this.themeContext;c.theme=this.theme;c.tableClass="infographicContainerAreaDetailsTable";c.tableParams={trimTextIfOverflows:!0};c.hasFixedLayout=!0;q.mixin(c,this._prepareSectionCreationParams());c=this.viewModel.layoutBuilder.createElement("section",c,this.contentDiv);c.setResizedHeight(g.getTableHeight(a[0]));l.set(c.domNode,{position:"absolute",left:"0px",top:(b||0)+"px"});this._sections.push(c);!h&&G.adjustContent(c.getTables()[0])},_prepareSectionCreationParams:function(a){return null},_destroySections:function(){this._sections=
this._sections||[];this._sections.forEach(function(a){a.destroy()});this._sections.length=0},_getAttributesStyle:function(){return this.__getContentStyle("attributesStyle")},_getNotesStyle:function(){return this.__getContentStyle("notesStyle")},__getContentStyle:function(a){var b=this.viewModel.getDocumentDefaultStyles(this.theme||this.themeContext);return q.mixin({},b,this.viewModel.getTableDefaultStyles(this.theme||this.themeContext,"Default"),this._currentInfographicJson[a])},_showEmptyView:function(a){A[a?
"show":"hide"](this.noDetailsPlaceHolder)},width:null,height:null,resize:function(a,b){void 0!==a&&(this.width=a,this.height=b);this.invoke("_doUpdateContent",50)},notifyShown:function(){this.resize()},_applyThemeToJson:function(a){var b=this.viewModel.getStaticInfographicDefaultStyles(this.theme||this.themeContext);return y.applyThemeSettingsToJson(a,b)},_undoThemeFromJson:function(a){return a},toJson:function(){var a=q.clone(this._currentInfographicJson);return this._undoThemeFromJson(a)},destroy:function(){this._destroySections();
this.inherited(arguments)}})});