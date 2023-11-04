
var start = null;
var startflag = false;
var goal = null;
var goalflag = false;
var route = null;
var mymap = L.map('map');

// スタートボタンとゴールボタンを取得
startbtn = document.getElementById("startbtn");
goalbtn = document.getElementById("goalbtn");
searchbtn = document.getElementById("search");

var start_x = parseFloat(document.getElementById("start_plotx").textContent);
var start_y = parseFloat(document.getElementById("start_ploty").textContent);
var goal_x = parseFloat(document.getElementById("goal_plotx").textContent);
var goal_y = parseFloat(document.getElementById("goal_ploty").textContent);



// マップを表示する
function initialize() {
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 20,
    attribution: '<a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
    }).addTo(mymap);

    start = L.marker([start_x, start_y]).addTo(mymap).bindPopup("スタート地点");
    goal = L.marker([goal_x, goal_y]).addTo(mymap).bindPopup("ゴール地点");

    mymap.setView([35.62575, 139.34153], 15);

    route = L.Routing.control({ // 探索開始
        waypoints: [
            L.latLng(start.getLatLng().lat, start.getLatLng().lng),
            L.latLng(goal.getLatLng().lat, goal.getLatLng().lng)
        ],
        createMarker: function(i, waypoint, n) {
            // nullを返すことで新しいマーカーを作成せずに非表示にする
            return null;
        },
        routeWhileDragging: false,
    }).addTo(mymap);   
}

// AJAXを使用してサーバーにcoordinatesデータを送信
function sendCoordinatesToServlet(coordinates, start, goal) {
    var xhr = new XMLHttpRequest();
    var url = "/plot/catch-route"; // 送信先のURL
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // サーバーからの応答を必要に応じて処理
            console.log("応答1");
            var response = xhr.responseText;
            console.log(response);
            console.log("応答2");
            console.log(document.getElementById("start_plotx").textContent);

            // window.location.href="/plot/search";
        } else {
            console.error("エラー:"+ xhr.status);
        }
    };

    // 送信するデータオブジェクトを作成
    var data = {
        coordinates: coordinates,
        start: start,
        goal: goal
    };

    // データをJSON文字列に変換
    var jsonData = JSON.stringify(data);
    // データ確認
    console.log(jsonData);

    // データをサーバーに送信
    xhr.send(jsonData);
}


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

    if (start && goal){ // スタートとゴールが設定されていたら経路探索
        if (route){ // 既に経路探索されていたらその結果表示を消去
            mymap.removeControl(route);
        }

        console.log(start.getLatLng());
        route = L.Routing.control({ // 探索開始
            waypoints: [
                L.latLng(start.getLatLng().lat, start.getLatLng().lng),
                L.latLng(goal.getLatLng().lat, goal.getLatLng().lng)
            ],
            createMarker: function(i, waypoint, n) {
                // nullを返すことで新しいマーカーを作成せずに非表示にする
                return null;
            },
            routeWhileDragging: false,
        }).addTo(mymap);

        // 経路の座標情報取得
        route.on("routesfound", function(e){
            var route1 = e.routes[0]; // 最初の経路を取得
            var coordinates = route1.coordinates; // 経路の座標を取得
            console.log(coordinates);
            console.log(coordinates.contentType);

            var start1 = {lat: start.getLatLng().lat, lng: start.getLatLng().lng};
            var goal1 = {lat: goal.getLatLng().lat, lng: goal.getLatLng().lng};

            // coordinatesの経路情報をServletに送信
            sendCoordinatesToServlet(coordinates, start1, goal1);
        })
    }


    
    
});
    


// マップをクリックした時のイベント
mymap.on('click', function (e) {
    // console.log(Object.prototype.toString.call(e));
    if (startflag){ //スタート地点入力モードか判定
        if (mymap && start){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(start);
            start = null;
        }
        if (mymap && !start){
            // start = L.marker([139.3, 35.6]).addTo(mymap).bindPopup("スタート地点");
            start = L.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap).bindPopup("スタート地点");
            // console.log(typeof 139.4=== typeof e.latlng.lat);

        }
    }

    if (goalflag){ // ゴール地点入力モードか判定
        if (mymap && goal){
            // もしスタート地点がプロットされていたら消す
            mymap.removeLayer(goal);
            goal = null;
        }
        if (mymap && !goal){
            goal = L.marker([e.latlng.lat, e.latlng.lng]).addTo(mymap).bindPopup("ゴール地点");
        }
    }
    
    });




window.onload = function(){ // initialize関数を呼び出して地図を表示
    initialize();
}
