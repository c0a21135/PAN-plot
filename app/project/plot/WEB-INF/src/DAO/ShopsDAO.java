package DAO;

import java.sql.*;
import Bean.*;

public class ShopsDAO {
    private final String URL = "jdbc:mysql://pan_3db:3306/pan_3db";
    private final String USER = "pan";
    private final String PASS = "pan";
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

    public ShopsDTO seslect() {
        Statement stmt = null;
        ResultSet rs = null;
        ShopsDTO sdto = new ShopsDTO();
        String sql = "select shop_id,shop_name,categories.category_name as category,access,address,tel,url,info" + 
                "from shops join categories on shops.category_id = categories.category_id";
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShopsBean sb = new ShopsBean();
                sb.setShopId(rs.getInt("shop_id"));
                sb.setShopName(rs.getString("shop_name"));
                sb.setCategory(rs.getString("category"));
                sb.setAccess(rs.getString("access"));
                sb.setAddress(rs.getString("address"));
                sb.setTel(rs.getString("tel"));
                sb.setUrl(rs.getString("url"));
                sb.setInfo(rs.getString("info"));
                sdto.add(sb);
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
        return sdto;
    }

    public ShopsDTO seslectWithId(int id) {
        Statement stmt = null;
        ResultSet rs = null;
        ShopsDTO sdto = new ShopsDTO();
        String sql = "select shop_id,shop_name,categories.category_name as category,access,address,tel,url,info " + 
                "from shops join categories on shops.category_id = categories.category_id where shop_id="+id;
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShopsBean sb = new ShopsBean();
                sb.setShopId(rs.getInt("shop_id"));
                sb.setShopName(rs.getString("shop_name"));
                sb.setCategory(rs.getString("category"));
                sb.setAccess(rs.getString("access"));
                sb.setAddress(rs.getString("address"));
                sb.setTel(rs.getString("tel"));
                sb.setUrl(rs.getString("url"));
                sb.setInfo(rs.getString("info"));
                sdto.add(sb);
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
        return sdto;
    }

    public ShopsDTO seslectLocation() {
        Statement stmt = null;
        ResultSet rs = null;
        ShopsDTO sdto = new ShopsDTO();
        String sql = "select locations.location_id as location_id, shops.shop_name as shop_name, location_x, location_y from locations join shops on locations.location_id = shops.location_id";
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShopsBean sb = new ShopsBean();
                sb.setShopId(rs.getInt("location_id"));
                sb.setShopName(rs.getString("shop_name"));
                sb.setLocationX(rs.getDouble("location_x"));
                sb.setLocationY(rs.getDouble("location_y"));
                sdto.add(sb);
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
        return sdto;
    }

    public ShopsDTO seslectLocationWithId(int id) {
        Statement stmt = null;
        ResultSet rs = null;
        ShopsDTO sdto = new ShopsDTO();
        String sql = "select locations.location_id as location_id, shops.shop_name as shop_name, location_x, location_y from locations join shops on locations.location_id = shops.location_id where location_id="+id;
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShopsBean sb = new ShopsBean();
                sb.setShopId(rs.getInt("location_id"));
                sb.setShopName(rs.getString("shop_name"));
                sb.setLocationX(rs.getDouble("location_x"));
                sb.setLocationY(rs.getDouble("location_y"));
                sdto.add(sb);
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
        return sdto;
    }

    public ShopsDTO seslectLocationWithXY(double prex, double prey, double x, double y) {
        Statement stmt = null;
        ResultSet rs = null;
        ShopsDTO sdto = new ShopsDTO();

        // 前の座標と今の座標で小さい方を基準とする
        double base_x;
        double base_y;
        if (prex <= x){
            base_x = prex;
        } else{
            base_x = x;
        }
        if (prey <= y){
            base_y = prey;
        } else {
            base_y = y;
        }

        double d_x = Math.abs(prex - x);
        double d_y = Math.abs(prey - y);
        String sql = "select locations.location_id as location_id, shops.shop_name as shop_name, location_x, location_y from locations join shops on locations.location_id = shops.location_id where location_x between" + base_x + "and" + base_x+d_x + "and location_y between" + base_y + "and" + base_y+d_y;
        try {
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShopsBean sb = new ShopsBean();
                sb.setShopId(rs.getInt("location_id"));
                sb.setShopName(rs.getString("shop_name"));
                sb.setLocationX(rs.getDouble("location_x"));
                sb.setLocationY(rs.getDouble("location_y"));
                sdto.add(sb);
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
        return sdto;
    }

    public int insert(String name, int category_id, int locate_id) {
        String sql = "INSERT INTO shops (user_name, category_id, locate_id) VALUES ('" + name + "', "+category_id+", "+locate_id+")";
        return executeSql(sql);
    }

    // public int updateStatus(int docked_number, int status) {
    //     String sql = "UPDATE shops SET status = " + status + " WHERE docked_number = " + docked_number;
    //     return executeSql(sql);
    // }

    public int delete(int shop_id) {
        String sql = "DELETE FROM shops WHERE shop_id = " + shop_id;
        return executeSql(sql);
    }

    public int executeSql(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            connect();
            stmt = con.createStatement();
            result = stmt.executeUpdate(sql);
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
        return result;
    }

    public void disconnect() {
        try {
            if (con != null)
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
