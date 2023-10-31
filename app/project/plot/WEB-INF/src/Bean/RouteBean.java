package Bean;

import java.util.ArrayList;

public class RouteBean {
    /*
     * 始点
     */
    private double[] start;

    /*
     * 終点
     */
    private double[] goal;

    /*
     * 経路
     */
    private ArrayList<double[]> routeList;

    public RouteBean() {
    }

    public RouteBean(double[] start, double[] goal, ArrayList<double[]> routeList) {
        super();
        this.start = start;
        this.goal = goal;
        this.routeList = routeList;
    }

    public double[] getStart() {
        return start;
    }

    public void setStart(double[] start) {
        this.start = start;
    }

    public double[] getGoal() {
        return goal;
    }

    public void setGoal(double[] goal) {
        this.goal = goal;
    }

    public ArrayList<double[]> getRouteList() {
        return routeList;
    }

    public void setRouteList(ArrayList<double[]> routeList) {
        this.routeList = routeList;
    }

}
