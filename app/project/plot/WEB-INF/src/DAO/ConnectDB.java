package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectDB {
    /**
     * DB接続オブジェクト
     */
    protected Connection con;

    /**
     * SQL実行用オブジェクト
     */
    protected PreparedStatement stmt;

    /**
     * DB接続用ID
     */
    protected String db_id;

    /**
     * DB接続用パスワード
     */
    protected String db_password;

    public ConnectDB(){
        this.db_id = "pan";
        this.db_password = "pan";

        try {
            // Java->MySQLへお話をするための準備
            Class.forName("com.mysql.jdbc.Driver"); // com.mysql.jdbc.Driver com.mysql.cj.jdbc.Driver
            // DB接続
            this.con = DriverManager.getConnection("jdbc:mysql://pan_3db:3306/pan_3db",
                    this.db_id, this.db_password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getStmt(String sql) {
        try {
            this.stmt = this.con.prepareStatement(sql); //, java.sql.Statement.RETURN_GENERATED_KEYS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stmt;
    }
}
