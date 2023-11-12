package Create;

public class CreatePlot {
    public static String Plot(String name, double locate_x, double locate_y, int id){
        return 
        "var marker = L.marker(["+locate_x+","+ locate_y + "], {icon: yellowIcon}).addTo(mymap);" +
        "var popupContent = '<form action=\"/plot/ShopInfo\" method=\"post\" target=\"_blank\" rel=\"noopener noreferrer\" accept-charset=\"UTF-8\"><button name=\"shop_id\" value="+id+">"+name+"</form>';" +
        "marker.bindPopup(popupContent);";
    }


    public static String Plot(double locate_x, double locate_y){
        return
        "var marker = L.marker(["+locate_x+","+ locate_y + "]).addTo(mymap);" +
        "marker.bindPopup(\"移動経路\");";
    }
}
