import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.TableNameBean;
import DAO.TableNameDAO;

import javax.servlet.RequestDispatcher;

@WebServlet("/SAtest") // SAtestでアクセスできるようにする
public class SearchTestServlet extends HttpServlet {
    public SearchTestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // HTTP応答のエンコード設定
        response.setContentType("text/html; charset=UTF-8");
        // 転送するURLを指定する
        String forwardURL = null; 

        // DAOオブジェクトを生成(データベースに接続する)
        TableNameDAO dao = new TableNameDAO();

        // 接続したデータベースの要素をすべて取得する
        ArrayList<TableNameBean> dto = dao.select();

        // requestへセットする．
        request.setAttribute("SAtest", dto);
        forwardURL = "/WEB-INF/jsp/SAtest.jsp";

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
