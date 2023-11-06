package Bean;

import java.io.Serializable;

public class ShopsBean implements Serializable {
    private int shop_id;
    private String shop_name;
    private String category;
    private String access;
    private String address;
    private String tel;
    private String url;
    private String info;
    private int location_x;
    private int location_y;
    
    public int getShopId() {
      return shop_id;
    }
    public void setShopId(int shop_id) {
      this.shop_id = shop_id;
    }
    public String getShopName() {
      return shop_name;
    }
    public void setShopName(String shop_name) {
      this.shop_name = shop_name;
    }
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    public String getAccess() {
      return access;
    }
    public void setAccess(String access) {
      this.access = access;
    }
    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }
    public String getTel() {
      return tel;
    }
    public void setTel(String tel) {
      this.tel = tel;
    }
    public String getUrl() {
      return url;
    }
    public void setUrl(String url) {
      this.url = url;
    }
    public String getInfo() {
      return info;
    }
    public void setInfo(String info) {
      this.info = info;
    }
    public int getLocationX() {
      return location_x;
    }
    public void setLocationX(int location_x) {
      this.location_x = location_x;
    }
    public int getLocationY() {
      return location_y;
    }
    public void setLocationY(int location_y) {
      this.location_y = location_y;
    }

}
