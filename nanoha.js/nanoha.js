var ImgCount = 15;
var itv = [];
var dy = [];
var dx = [];
var imgX = [];
var imgY = [];
filename = new Array("riinzwei","nanoha","fatet","hayate","bibio","kyaro","sigunamu","syamaru","vita","thiana","subaru","agito","ginga","ruutesia","karimu");

var i = filename.length;
while (--i) {
var j = Math.floor(Math.random() * (i + 1));
if (i == j) continue;
var k = filename[i];
filename[i] = filename[j];
filename[j] = k;
}

for (var i = 0 ; i < ImgCount; i++) {
document.write('<img src="' + filename[i] + '.png" name="img' + [i] + '" style="position:absolute">');
}

for (var i=0; i<ImgCount; i++) {
itv.push( Math.random()*15 +2 );
}
for (var i=0; i<ImgCount; i++) {
dy.push( Math.random()*6 +1 );
}
for (var i=0; i<ImgCount; i++) {
dx.push( Math.random()*6 +1 );
}
for (var i=0; i<ImgCount; i++) {
imgX.push( Math.random()*320 + Math.random()*160 );
}
for (var i=0; i<ImgCount; i++) {
imgY.push( Math.random()*80 + Math.random()*40 );
}

var timerID = new Array(ImgCount);

function moveImg(index) {

imgX[index] += dx[index];
imgY[index] += dy[index];

eval("document.img"+index+".style.left= imgX["+index+"]");
eval("document.img"+index+".style.top = imgY["+index+"]");

if((imgX[index] < 1) || (imgX[index] > winW)) dx[index] = -dx[index];
if((imgY[index] < 1) || (imgY[index] > winH)) dy[index] = -dy[index];

}

function moveNanoha() {

if(document.all) {
winW = document.body.clientWidth;
winH = document.body.clientHeight;
}

if(!document.all && document.getElementById) {
winW = window.innerWidth;
winH = window.innerHeight;
}

winW = winW - document.img1.width;
winH = winH - document.img1.height;

for (var i=0; i<itv.length; i++) {
setInterval("moveImg("+i+")", itv[i]);
}

}