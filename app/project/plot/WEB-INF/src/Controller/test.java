package Controller;

import java.util.ArrayList;

public class test {
  public static void main(String[] args) {

    ArrayList<double[]> list = new ArrayList<double[]>();

    double[] d = new double[2];   d[0]=0;   d[1]=0;   list.add(d);
             d = new double[2];   d[0]=1;   d[1]=1;   list.add(d);
             d = new double[2];   d[0]=2;   d[1]=1;   list.add(d);
             d = new double[2];   d[0]=3;   d[1]=3;   list.add(d);
             d = new double[2];   d[0]=6;   d[1]=8;   list.add(d);
             d = new double[2];   d[0]=7;   d[1]=9;   list.add(d);
             d = new double[2];   d[0]=10;  d[1]=9;   list.add(d);
             d = new double[2];   d[0]=12;  d[1]=10;  list.add(d);

    pointList pt = new pointList();
    list = pt.pickPoint(list);

    System.out.println("==========");
    System.out.println("result");
    System.out.println("==========");

    for (int i=0; i<list.size(); i++) {
      System.out.println(list.get(i)[0]+","+ list.get(i)[1]);
    }
    
    System.out.println("==========");
    System.out.println("listsize: "+list.size());
    System.out.println("==========");
    
  }
}
