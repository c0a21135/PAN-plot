var plot_list = [];
var startplot = null;
var startflag = false;
var goalplot = null;
var goalflag = false;
var route = null;


// // これはマップを表示するためのJavaScript

var mymap = L.map('map');
function initialize() {

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 20,
    attribution: '<a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(mymap);

    mymap.setView([35.62575, 139.34153], 15);
}


// スタートボタンとゴールボタンを取得
startbtn = document.getElementById("startbtn");
goalbtn = document.getElementById("goalbtn");
searchbtn = document.getElementById("search");

startbtn.addEventListener("click", function(){
    startflag = true;
    goalflag = false;
});

goalbtn.addEventListener("click", function(){
    startflag = false;
    goalflag = true;
});

searchbtn.addEventListener("click", function(){
    startflag = false;
    goalflag = false;

    if (startplot && goalplot){ // スタートとゴールが設定されていたら経路探索
        if (route){ // 既に経路探索されていたらその結果表示を消去
            mymap.removeControl(route);
        }

        route = L.Routing.control({ // 探索開始
            waypoints: [
                L.latLng(startplot.getLatLng().lat, startplot.getLatLng().lng),
                L.latLng(goalplot.getLatLng().lat, goalplot.getLatLng().lng)
            ],
            createMarker: function(i, waypoint, n) {
                // nullを返すことで新しいマーカーを作成せずに非表示にする
                return null;
            },
            
            routeWhileDragging: false,
        }).addTo(mymap);
    }


    
    
});
    


// マップをクリックした時のイベント
mymap.on('click', function (e) {
    if (startflag){ //スタート地点入力モードか判定
        if (mymap && startplot){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(startplot);
            startplot = null;
        }
        if (mymap && !startplot){
            startplot = L.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap).bindPopup("スタート地点");
        }
    }

    if (goalflag){ // ゴール地点入力モードか判定
        if (mymap && goalplot){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(goalplot);
            goalplot = null;
        }
        if (mymap && !goalplot){
            goalplot = L.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap).bindPopup("ゴール地点");
        }
    }
    
    });




window.onload = function(){ // initialize関数を呼び出して地図を表示
    initialize();
}






// class Map {
//     constructor() {
//         // // 表示するマップの情報（今回はオープンストリートマップ）
//         // this.map = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png';
//         // this.attribute = '<a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>';
//         // // 表示するマップの設定
//         // this.mymap = L.map("map");
//         // this.tileLayer = L.tileLayer(this.map, {
//         //     maxZoom: 20,
//         //     attribution: this.attribute,
//         // }).addTo(this.mymap);
//         // this.mymap.setView([35.62575, 139.34153], 15);

        
//         this.startplot = null;
//         this.goalplot = null;
//     }


//     // マップを表示する
//     viewMap() {
//     // this.mymap.setView([35.62575, 139.34153], 15);
// }

//     startPlot(locate_x, locate_y) {
//     // もしスタート地点がプロットされていたら消す
//     if (mymap && this.startplot) {
//         mymap.removeLayer(startplot);
//     }
//     startplot = L.marker([locate_x, locate_y]).addTo(mymap).bindPopup(String(e.latlng.lat) + "," + String(e.latlng.lng));
// }
//     goalPlot = function (e) {
//     // もしゴール地点がプロットされていたら消す
//     if (mymap && this.goalplot) {
//         mymap.removeLayer(goalplot);
//     }
//     goalplot = L.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap).bindPopup(String(e.latlng.lat) + "," + String(e.latlng.lng));
// }


// }


// // export {Map};