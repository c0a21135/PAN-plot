package Create;

public class CreatePlot {
    public static String Plot(String name, double locate_x, double locate_y){
        return 
        "var marker = L.marker(["+locate_x+","+ locate_y + "]).addTo(mymap); marker.bindPopup('"+name+"');";
    }
}
