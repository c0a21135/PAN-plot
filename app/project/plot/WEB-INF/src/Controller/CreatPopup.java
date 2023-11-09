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

import Bean.ShopsDTO;
import DAO.ConnectDB;
import DAO.ShopsDAO;

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

        String forwardURL = null;

        // ShopsDAOオブジェクトを生成
        ShopsDAO sdao = new ShopsDAO();

        ShopsDTO sdto;
        sdto = sdao.selectLocation();

        // requestへセットする．
        request.setAttribute("shop_locations", sdto);
        // forwardURL = "/user/plotmap.jsp";
        forwardURL = "/user/resultmap.jsp";

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
