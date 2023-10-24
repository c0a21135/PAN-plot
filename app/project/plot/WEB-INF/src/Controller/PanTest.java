package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.PanTestBean;
import DAO.ConnectDB;

import javax.servlet.RequestDispatcher;

@WebServlet("/pan")
public class PanTest extends HttpServlet {
    public PanTest() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTTP応答のエンコード設定
        response.setContentType("text/html; charset=UTF-8");

        ResultSet rs;
        String forwardURL = "/test.jsp";

        try {
            /** DB接続に関する共通部 START **/
            // DB接続してStatementを取得する．
            ConnectDB db = new ConnectDB();



            // Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver com.mysql.cj.jdbc.Driver // 入れるとエラーになる
                        
            // Connection con = DriverManager.getConnection("jdbc:mysql://pan_3db:3306/database", "pan", "pan");
            // SQL文の作成(プレースホルダーを使うのがわかりやすい)
            String sql = "select * from pan_test";

            PreparedStatement stmt = db.getStmt(sql);
            /** DB接続に関する共通部 END **/

            // PreparedStatement stmt = null;

            // stmt = con.prepareStatement(sql);
            // forwardURL = "/hello.jsp";

            // 実行結果取得
            rs = stmt.executeQuery();
            // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
            int cnt = 0;
            ArrayList<PanTestBean> infoArray = new ArrayList<PanTestBean>();
            while (rs.next()) {
                // カラムの値を取得する．
                int id = rs.getInt("pan_id");
                // beanを生成
                PanTestBean bean = new PanTestBean(id);
                // bean.setPanId(id);
                // Listへbeanを追加する．
                infoArray.add(bean);
                // 見つかった
                cnt++;
                forwardURL = "/pantest/pantest.jsp";
            }
            // requestへセットする．
            request.setAttribute("pantest", infoArray);
            

        } catch (Exception e) {
            // forwardURL = "/hello.jsp";
            // request.setAttribute("er", e.getLocalizedMessage());
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
