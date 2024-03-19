package Controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import DAO.ShopsDAO;

import javax.servlet.annotation.*;

import Bean.ShopsDTO;

@WebServlet("/ShopInfo")
public class ShopInfoServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        req.setCharacterEncoding("utf-8");
        String forwardURL = "user/shopInfo.jsp";
        
        int shop_id = Integer.parseInt(req.getParameter("shop_id"));

        // ShopsDAOオブジェクトを生成
        ShopsDAO sdao = new ShopsDAO();

        ShopsDTO sdto;
        sdto = sdao.selectWithId(shop_id);

        req.setAttribute("sdto", sdto);

        // JSPにフォワード
        RequestDispatcher rd = req.getRequestDispatcher(forwardURL);
        rd.forward(req, res);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        doPost(req, res);
    }
}
