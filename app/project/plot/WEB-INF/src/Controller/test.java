package Controller;

import java.util.ArrayList;

public class test {
  public static void main(String[] args) {

    ArrayList<double[]> list = new ArrayList<double[]>();

    double[] d = new double[2];
    d[0]=0;   d[1]=0;   list.add(d);
    d = new double[2];
    d[0]=1;   d[1]=1;   list.add(d);
    d = new double[2];
    d[0]=2;   d[1]=1;   list.add(d);
    d = new double[2];
    d[0]=3;   d[1]=3;   list.add(d);
    d = new double[2];
    d[0]=6;   d[1]=8;   list.add(d);
    d = new double[2];
    d[0]=7;   d[1]=9;   list.add(d);
    d = new double[2];
    d[0]=10;  d[1]=9;   list.add(d);
    d = new double[2];
    d[0]=12;  d[1]=10;  list.add(d);


    // double reg_min = 2;
    // double reg_max = 4;

    // ArrayList<double[]> pointlist = new ArrayList<>();

    // pointlist.add(list.get(0));
    // int skp=0;

    // System.out.println("listsize: "+list.size());

    // for (int i=0; i<list.size();i++) {    // 基点を移す
    //   double[] base = list.get(i);
    //   System.out.println("----------");

    //   if (skp==0) {
    //     for (int j=i+1;j<list.size();j++) { // 比較する点を移す（基点の次から最後まで）
    //       double[] nxt = list.get(j);
    //       double dist = Math.sqrt(Math.pow(Math.abs(base[0]-nxt[0]),2)+Math.pow(Math.abs(base[1]-nxt[1]),2));

    //       System.out.println("base: "+base[0]+","+base[1]+",  nxt: "+nxt[0]+","+nxt[1]);
    //       System.out.println("dist: "+dist);

    //       // 近すぎたら
    //       if (dist < reg_min) {
    //         System.out.println("break: "+nxt[0]+","+nxt[1]);
    //         skp += 1;
    //         continue;  // 次の比較点へ
    //       }

    //       // 遠すぎたら
    //       if (dist > reg_max) {
    //         // 必要な中継地点の数を算出
    //         int rep_count = (int) (dist/reg_min);
    //         // 中継地点をリストに追加
    //         double[] rep;
    //         for (int k=1; k<=rep_count; k++) {
    //           rep = new double[2];
    //           rep[0] = base[0]+Math.abs(base[0]-nxt[0])/(rep_count+1)*k;
    //           rep[1] = base[1]+Math.abs(base[1]-nxt[1])/(rep_count+1)*k;
    //           System.out.println("add(rep): "+rep[0]+","+rep[1]);
    //           pointlist.add(rep);
    //         }
    //       }

    //       // 比較点をリストに追加
    //       pointlist.add(nxt);
    //       System.out.println("add: "+nxt[0]+","+nxt[1]);
    //       break;
          
    //     }
    //   } else {
    //     System.out.println("skip: "+base[0]+","+base[1]);
    //     skp -= 1;
    //   }
    // }

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
