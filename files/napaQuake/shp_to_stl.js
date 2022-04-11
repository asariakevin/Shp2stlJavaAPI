var fs = require('fs');
var shp2stl = require('shp2stl');
shp2stl.shp2stl('/home/asaria/IdeaProjects/Shp2stlJavaAPI/files/napaQuake/pga.shp',{
width: 100.0,height: 10.0,extraBaseHeight: 1.0,extrudeBy: "VALUE",simplification: 0.0,destSRS: 'EPSG:900913',binary: true,cutoutHoles: false,verbose: true,extrusionMode: 'straight'},
function(err,stl){ fs.writeFileSync('/home/asaria/IdeaProjects/Shp2stlJavaAPI/files/napaQuake/output.stl', stl)
}
);