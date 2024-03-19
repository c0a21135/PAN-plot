package DAO;

import java.sql.*;
import java.util.ArrayList;

import Bean.TableNameBean;

public class TableNameDAO { // TableNameは接続するデータベースのテーブル名で作る
    /*
     * データベースとJavaを接続するクラス
     */

    /*
     * 接続するデータベースのURL, ユーザとパスワードを設定する
     */
    
    private final String URL = "jdbc:mysql://pan_3db/pan_3db"; // jdbc:mysql://接続するサーバー名/接続するデータベース名 
    // private final String URL = "jdbc:mysql://pan_3db/" + 接続するデータベース名; // jdbc:mysql://接続するサーバー名/接続するデータベース名 
                                                                                 // ↑↑接続するURLの指定方法
    private final String USER = "pan"; // データベースサーバにアクセスするユーザ名
    private final String PASS = "pan"; // 上で接続するユーザのパスワード
    private Connection con = null;

    public void connect() {
        try {
            // Java->MySQLへお話をするための準備
            Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver com.mysql.cj.jdbc.Driver
            con = DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TableNameBean> select() {
        /*
         * データベースの要素を全て取得する関数
         */
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TableNameBean> dto = new ArrayList<TableNameBean>(); // データベースから取得した要素達を入れておくリスト
        String sql = "select * from table_name"; // データベースサーバで実行するSQL文
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);                        // ここでSQLを実行
            while (rs.next()) {                                 // 要素が見つかった場合、1つずつ要素を取り出す
                TableNameBean bean = new TableNameBean();       // 要素を入れるオブジェクトを作成
                bean.setId(rs.getInt("id"));        // データベースのカラム名から値を取得してセット
                bean.setName(rs.getString("name")); // 上に同じく
                bean.setPath(rs.getString("path")); // 上に同じく  
                dto.add(bean);                                  // データベースから取得した要素をリストに加える
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally { // データベースとの接続を解除したりする
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();

        return dto;
    }

    public ArrayList<TableNameBean> selectWithId(int id) {
        /*
         * データベースから、引数で指定したidの要素を取得する関数
         */
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<TableNameBean> dto = new ArrayList<TableNameBean>();
        String sql = "select * from table_name WHERE id = " + id; // 実行するSQL文。
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);                        // ここでSQLを実行
            while (rs.next()) {                                 // 要素が見つかった場合、1つずつ要素を取り出す
                TableNameBean bean = new TableNameBean();       // 要素を入れるオブジェクトを作成
                bean.setId(rs.getInt("id"));        // データベースのカラム名から値を取得してセット
                bean.setName(rs.getString("name")); // 上に同じく
                bean.setPath(rs.getString("path")); // 上に同じく  
                dto.add(bean);                                  // データベースから取得した要素をリストに加える
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        disconnect();
        return dto;
    }



    public void disconnect() {
        /*
         * データベースの接続を切断する
         */
        try {
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
