<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, Bean.*, Create.*"%>
<jsp:useBean id ="shopsDTO" scope="session" class="Bean.ShopsDTO" />
<jsp:useBean id ="routeBean" scope="session" class="Bean.RouteBean" />
<jsp:useBean id ="shop_locations" scope="request" class="Bean.ShopsDTO" />
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

    <%-- JavaScriptのグローバル変数を定義 --%>
    <script>
        var g_start;
        var g_goal;
        var g_route;
        var g_startflag;
        var g_goalflag;
        // スタートボタンとゴールボタンを取得
        var g_startbtn = document.getElementById("startbtn");
        var g_goalbtn = document.getElementById("goalbtn");
        var g_searchbtn = document.getElementById("search");
    </script>

    <%
        // RouteBean routeBean;
        double[] start;
        double[] goal;
    
    %>

</head>

<body>

    <header>
        <h2>Map</h2>
        <div class="header-btn">
            <button type="button" id="startbtn"><div class="map-pin img-blue"></div></button>
            <button type="button" id="goalbtn"><div class="map-pin img-red"></div></button>
            <button type="button" id="search"><div class="map-pin img-search"></div></button>
        </div>
    </header>

    <div class="map">
        <div id="map"></div>
    </div>

    hogeeeee<br/>




    <% 
        if (routeBean != null){
            start = routeBean.getStart();
            goal = routeBean.getGoal();
    %>
            <%-- <%=session.getAttribute("testData")%><br/>
            <%=session.getAttribute("testData").getClass()%> --%>
            <%=start[0]%>, <%=start[1]%><br/>
            <%=goal[0]%>, <%=goal[1]%><br/>

            <div hidden>
                <p id="start_plotx"><%=start[0]%></p>
                <p id="start_ploty"><%=start[1]%></p>
                <p id="goal_plotx"><%=goal[0]%></p>
                <p id="goal_ploty"><%=goal[1]%></p>
            </div>
            
            <script>
                g_start = [<%=start[0]%>, <%=start[1]%>];
                g_goal = [<%=goal[0]%>, <%=goal[1]%>];
            </script>

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


    <script>
        
    
    </script>


    <%-- 地図の表示 --%>
    <script src="/plot/js/resultmap.js"></script>


    <%-- javascriptでマップピンの生成と表示 --%>
    <%
    // javaプログラムの埋め込み
    if (shop_locations != null){
        Iterator<ShopsBean> ite = shop_locations.iterator();
    %>
        locations-atta <br/>
        <%=shop_locations.size()%> 
        <%
        while(ite.hasNext()){
            ShopsBean bean = ite.next();
        %>
    
            <%=bean.getShopName()%>, <%=bean.getLocationX()%>, <%=bean.getLocationY()%><br/>
        <script>
            <%=CreatePlot.Plot(bean.getShopName(),bean.getLocationX(),bean.getLocationY(), bean.getShopId())%> 
        </script>
    <%
        }
    }
    %>


    <%-- 経路付近の店舗情報を表示する --%>
    <%
    if (shopsDTO != null){
         Iterator<ShopsBean> ite = shopsDTO.iterator();
    %>

        shopsDTO-atta<br/>
        <%=shopsDTO.size()%>
        <%
        while(ite.hasNext()){
            ShopsBean bean = ite.next();
        %>
        
            <%=bean.getShopName()%>, <%=bean.getLocationX()%>, <%=bean.getLocationY()%><br/>
            <script>
                <%=CreatePlot.Plot(bean.getShopName(),bean.getLocationX(),bean.getLocationY(), bean.getShopId())%> 
            </script>
        <%
            }
        }
        %>

    <footer>
        <h2>Footer</h2>
    </footer>

</body>

</html>