<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Bean.*, Create.*"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
        integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
    <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css" />
    <link rel="stylesheet" href="/plot/css/style.css" type="text/css" />


    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
    integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
    <script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>


</head>

<body>
    <div id="map"></div>
    <p><input type="button" id="startbtn" value="スタート地点入力"></p>
    <p><input type="button" id="goalbtn" value="ゴール地点入力"></p>
    <p><input type="button" id="search" value="ルートから検索"></p>


    hogeeeee<br/>
    <% 
        if (session.getAttribute("testData") != null){
            RouteBean routeBean = (RouteBean)session.getAttribute("testData");
            double[] start = routeBean.getStart();
            double[] goal = routeBean.getGoal();
    %>
            <%-- <%=session.getAttribute("testData")%><br/>
            <%=session.getAttribute("testData").getClass()%> --%>
            <%=start[0]%>, <%=start[1]%><br/>
            <%=goal[0]%>, <%=goal[1]%><br/>

            <%
            ArrayList<double[]> routeList = routeBean.getRouteList();
            Iterator<double[]> route = routeList.iterator();
            while(route.hasNext()){
                double[] routes = route.next(); %>

                <%=routes[0]%>, <%=routes[1]%><br/>
            <%
            }
            %>

    <%    } %>


    <%-- 地図の表示 --%>
    <script src="/plot/js/resultmap.js"></script>
    <%-- javascriptでマップピンの生成と表示 --%>
    <%
    // javaプログラムの埋め込み
    if (request.getAttribute("restaurants") != null){
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
    }
    %>
     
</body>

</html>