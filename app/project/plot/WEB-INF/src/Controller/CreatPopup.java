package Controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PanTestBean;
import Bean.RestaurantsBean;
import DAO.ConnectDB;

import javax.servlet.RequestDispatcher;

@WebServlet("/search")
public class CreatPopup extends HttpServlet {
    public CreatPopup() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTTP応答のエンコード設定
        response.setContentType("text/html; charset=UTF-8");

        ResultSet rs;
        String forwardURL = null;

        try {
            /** DB接続に関する共通部 START **/
            // DB接続してStatementを取得する．
            ConnectDB db = new ConnectDB();

            // SQL文の作成
            String sql = "select * from restaurants";

            PreparedStatement stmt = db.getStmt(sql);
            /** DB接続に関する共通部 END **/

            // 実行結果取得
            rs = stmt.executeQuery();
            // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
            int cnt = 0;
            ArrayList<RestaurantsBean> infoArray = new ArrayList<RestaurantsBean>();
            while (rs.next()) {
                // カラムの値を取得する．
                int id = rs.getInt("restaurant_id");
                String name = rs.getString("restaurant_name");
                double locate_x = rs.getDouble("locate_x");
                double locate_y = rs.getDouble("locate_y");
                // beanを生成
                RestaurantsBean bean = new RestaurantsBean(id, name, locate_x, locate_y);
                // bean.setPanId(id);
                // Listへbeanを追加する．
                infoArray.add(bean);
                // 見つかった
                cnt++;
            }
            // requestへセットする．
            request.setAttribute("restaurants", infoArray);
            // forwardURL = "/user/plotmap.jsp";
            forwardURL = "/user/resultmap.jsp";

        } catch (Exception e) {
            forwardURL = "/hello.jsp";
            request.setAttribute("er", e.getLocalizedMessage());
            e.printStackTrace();
        }
        
        // 外部ファイルに転送する準備
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardURL);
        // 外部ファイルに表示処理を任せる
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
