package Controller;

import java.util.ArrayList;

import java.util.Iterator;

public class pointList {
  private double reg_min = 2;
  private double reg_max = 4;

  public ArrayList<double[]> pickPoint(ArrayList<double[]> list) {

    ArrayList<double[]> pointlist = new ArrayList<>();

    pointlist.add(list.get(0));
    int skp=0;

    System.out.println("listsize: "+list.size());
    Iterator<double[]> ite = list.iterator();

    double[] base = list.get(0);
    while (ite.hasNext()) {    // 基点を移す
      System.out.println("----------");

      if (skp==0) {
        double[] nxt = ite.next();
        double dist = Math.sqrt(Math.pow(Math.abs(base[0]-nxt[0]),2)+Math.pow(Math.abs(base[1]-nxt[1]),2));
      
        System.out.println("base: "+base[0]+","+base[1]+",  nxt: "+nxt[0]+","+nxt[1]);
        System.out.println("dist: "+dist);

        // 近すぎたら
        if (dist < reg_min) {
          System.out.println("break: "+nxt[0]+","+nxt[1]);
          skp += 1;
          continue;  // 次の比較点へ
        }

        // 遠すぎたら
        if (dist > reg_max) {
          // 必要な中継地点の数を算出
          int rep_count = (int) (dist/reg_max);
          // 中継地点をリストに追加
          double[] rep;
          for (int k=1; k<=rep_count; k++) {
            rep = new double[2];
            rep[0] = base[0]+Math.abs(base[0]-nxt[0])/(rep_count+1)*k;
            rep[1] = base[1]+Math.abs(base[1]-nxt[1])/(rep_count+1)*k;
            pointlist.add(rep);
            System.out.println("add(rep): "+rep[0]+","+rep[1]);
          }
        }

        // 比較点をリストに追加
        pointlist.add(nxt);
        base = nxt;
        System.out.println("add: "+nxt[0]+","+nxt[1]);
        
      } else {
        skp -= 1;
        System.out.println("skip: "+base[0]+","+base[1]);
      }

    }

    return pointlist;
  }



}
