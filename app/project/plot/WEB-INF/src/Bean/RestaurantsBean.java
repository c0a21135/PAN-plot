package Bean;

public class RestaurantsBean {
     /**
     * レストランのID
     */
    private int restaurantId;
     /**
     * レストランのX座標
     */
    private double locateX;
     /**
     * レストランのY座標
     */
    private double locateY;

    public RestaurantsBean(int restaurantId, double locateX, double locateY){
        this.restaurantId = restaurantId;
        this.locateX = locateX;
        this.locateY = locateY;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    public double getLocateX() {
        return locateX;
    }
    public void setLocateX(double locateX) {
        this.locateX = locateX;
    }
    public double getLocateY() {
        return locateY;
    }
    public void setLocateY(double locateY) {
        this.locateY = locateY;
    }

    
    
}
