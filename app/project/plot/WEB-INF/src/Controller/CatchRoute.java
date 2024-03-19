package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.ShopsDAO;
import Bean.RouteBean;
import Bean.ShopsBean;
import Bean.ShopsDTO;

@WebServlet("/catch-route")
public class CatchRoute extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストのコンテンツをJSON文字列として読み込む
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        // JSONデータを解析
        JSONObject jsonData = new JSONObject(jsonInput.toString());

        // データを処理
        double[] start = new double[2];
        double[] goal = new double[2];
        // double[] route = new double[2];
        ArrayList<double[]> routeList = new ArrayList<double[]>();

        // 始点の座標を取得
        JSONObject startData = jsonData.getJSONObject("start");
        start[0] = startData.getDouble("lat");
        start[1] = startData.getDouble("lng");

        routeList.add(start);


        // 経路の座標を取得
        JSONObject coordinate;
        JSONArray coordinatesArray = jsonData.getJSONArray("coordinates");
        Iterator<Object> coordinatesList = coordinatesArray.iterator();
        while (coordinatesList.hasNext()) {
            coordinate = (JSONObject) coordinatesList.next();
            double[] route = new double[2];
            route[0] = coordinate.getDouble("lat");
            route[1] = coordinate.getDouble("lng");
            routeList.add(route);
        }


        // 終点の座標を取得
        JSONObject goalData = jsonData.getJSONObject("goal");
        goal[0] = goalData.getDouble("lat");
        goal[1] = goalData.getDouble("lng");
        routeList.add(goal);


        // routeListの整形
        PointList pt = new PointList();
        routeList = pt.pickPoint(routeList);
        routeList.add(goal);

        
        RouteBean routeBean = new RouteBean();
        routeBean.setStart(start);
        routeBean.setGoal(goal);
        routeBean.setRouteList(routeList);

        // 例: データをセッションに保存
        HttpSession session = request.getSession();
        session.setAttribute("routeBean", routeBean); // routeBeanをセッションに入れる

        // 経路付近のお店を検索する
        ShopsDAO sdao = new ShopsDAO();
        ShopsDTO sdto = sdao.selectLocationWithXY(routeList);

        session.setAttribute("shopsDTO", sdto);




        // ページへのリダイレクト
        // response.sendRedirect("user/plotmap.jsp");
        // // 外部ファイルに転送する準備
        // RequestDispatcher dispatcher =
        // request.getRequestDispatcher("user/plotmap.jsp");
        // // 外部ファイルに表示処理を任せる
        // dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    // private static boolean checkBeforeWritefile(File file) {
    // if (file.exists()) {
    // if (file.isFile() && file.canWrite()) {
    // return true;
    // }
    // }
    // return false;
    // }
}
