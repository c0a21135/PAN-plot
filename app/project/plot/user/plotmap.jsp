<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Bean.*, Create.*"%>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
        integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
        integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
    <link rel="stylesheet" href="/plot/css/style.css" type="text/css"/>

</head>

<body>
    <div id="map"></div>


    hogeeeee
    <script src="/plot/js/map.js"></script>
    

    <%
    // javaプログラムの埋め込み
    ArrayList<RestaurantsBean> list = (ArrayList<RestaurantsBean>)request.getAttribute("restaurants");
    Iterator<RestaurantsBean> ite = list.iterator();
    %>


    <%
    while(ite.hasNext()){
        RestaurantsBean bean = ite.next();
    %>
        <script>
            <%=CreatePlot.Plot(bean.getRestaurantName(),bean.getLocateX(),bean.getLocateY())%> 
        </script>
    <%
        }
    %>
     
</body>

</html>