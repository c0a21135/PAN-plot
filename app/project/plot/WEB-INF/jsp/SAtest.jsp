<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, DAO.*, Bean.*" %> <%-- javaでインポートするパッケージを指定 --%>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>テストページ</title>
        </head>

        <body>
        <%-- Javaのプログラム書く --%>
            <%
                // Servletでセットした値を取得する　型指定忘れずに！
                ArrayList<TableNameBean> beanlist = (ArrayList<TableNameBean>)request.getAttribute("SAtest");
                // iterator型に変換する
                Iterator<TableNameBean> beanite = beanlist.iterator();            
            %>

        <%-- 値を表示する --%>
            <table>
                <tr><td>employee_id</td><td>first_name</td><td>last_name</td></tr>

                <%-- 値を表示するためにjavaを書く --%>
                <% 
                while(beanite.hasNext()){ // 要素を一つずつ取っていく
                    TableNameBean bean = beanite.next(); // 変数beanに要素を入れる
                %>
                <%-- この下はHTML。tdの中にJavaのプログラムを埋め込む --%>
                    <tr>
                        <td> <%=bean.getId()%> </td>
                        <td> <%=bean.getName()%> </td>
                        <td> <%=bean.getPath()%> </td>
                    </tr>

                <%
                } // 25行目の while(beanite.hasNext()){ を閉じる括弧
                %>
            
            </table>
            

        </body>

        </html>