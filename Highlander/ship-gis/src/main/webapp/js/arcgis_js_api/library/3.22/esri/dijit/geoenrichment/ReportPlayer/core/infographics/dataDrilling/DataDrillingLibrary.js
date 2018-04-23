// All material copyright ESRI, All Rights Reserved, unless otherwise specified.
// See http://js.arcgis.com/3.22/esri/copyright.txt for details.
//>>built
define("esri/dijit/geoenrichment/ReportPlayer/core/infographics/dataDrilling/DataDrillingLibrary","dojo/_base/lang ./ChartBuilder ./Population ./Households ./Income ./NetWorth ./HomeValue ./Spending ./Electronics ./Pets ./Businesses ./Employees ./Education ./Mortgage ../../supportClasses/TableUtil ../../supportClasses/templateJsonUtils/FieldInfoNameUtil".split(" "),function(m,v,h,r,t,w,x,y,z,A,B,C,D,E,u,n){var g={},c={TOTPOP_CY:[h.annualGrowth],MEDAGE_CY:[h.agePyramid],MEDHINC_CY:[t.householdsByIncome],
AVGHHSZ_CY:[r.avgHouseholdSize]},q={n:"#",p:"%",a:"Avg",i:"Index"};Object.keys(c).forEach(function(a){-1!==a.indexOf(",")&&(a.split(",").forEach(function(f){var d=c[a];d.forEach(function(a){a.fieldInfo.infographicJson&&(a.fieldInfo.infographicJson.style=a.fieldInfo.infographicJson.style||{})});c[f]=d}),delete c[a])});g.hasDrillingOptions=function(a){return!!g.getDrillingOptions(a)};g.getDrillingOptions=function(a){if(!a.variableID)return null;a=a.variableID.toUpperCase();for(var f in c){var d=n.createFieldNameFromVariable(f);
if(-1!==a.indexOf(f.toUpperCase())||-1!==a.indexOf(d.toUpperCase()))return c[f]}return null};g.getDrillingOptionInfo=function(a,f,d,c,k){k=k&&k.charAt(0)||"n";var b=g.getDrillingOptions(a).filter(function(a){return a.name==f})[0];b.stateData||(b.stateData={n:{fieldInfo:b.fieldInfo,tableJson:null}},b.fieldInfo.isChart&&b.fieldInfo.chartJson.seriesItems.forEach(function(a){a.points.forEach(function(a){var b=a.calculator.substr(a.calculator.indexOf("/")+1);a.fieldName=n.createFieldNameFromVariable(b,
a.calculator.charAt(0))})}),b.states&&(b.states=b.states.split(",").map(function(a){return a+"/"}),b.states.forEach(function(a){var e=a.charAt(0);if("n"!=e&&(a=m.clone(b.fieldInfo),b.stateData[e]={fieldInfo:a,tableJson:null},a.isChart)){a.chartJson.visualProperties.title.text+=" ("+q[e]+")";a.chartJson.seriesItems.forEach(function(a){a.points.forEach(function(a){var b=a.calculator.substr(a.calculator.indexOf("/")+1);a.fieldName=n.createFieldNameFromVariable(b,e);a.calculator=a.calculator.replace("n/",
e+"/")})});if("p"==e||"a"==e)a.chartJson.visualProperties.dataLabelsDecimals=2;"i"==e&&(a.chartJson.visualProperties.dataLabelsDecimals=0)}}),b.calcGroup={states:{ids:b.states,names:b.states.slice(),labels:b.states.map(function(a){return q[a.charAt(0)]})},value:b.states[0]},delete b.states),delete b.fieldInfo);a=b.stateData[k];a.tableJson||(a.tableJson=u.createSingleCellTable({fieldInfo:a.fieldInfo,width:d,height:c}));d=m.clone(a.tableJson);var h=[],l;for(l in b.stateData)c=b.stateData[l],c.fieldInfo.isChart&&
c.fieldInfo.chartJson.seriesItems.forEach(function(a){a.points.forEach(function(a){h.push({fieldName:a.fieldName,mapTo:a.calculator.substr(a.calculator.indexOf("/")+1)+("n"==l?"":"_"+l.toUpperCase())})})});var p;b.calcGroup&&(p=m.clone(b.calcGroup),p.value=k+"/");return{tableJson:d,fieldsToEnrich:h,calculationStatesGroup:p}};return g});