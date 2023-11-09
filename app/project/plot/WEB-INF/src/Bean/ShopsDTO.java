package Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ShopsDTO implements Serializable {
    private ArrayList<ShopsBean> list;

    public ShopsDTO() {
        list = new ArrayList<ShopsBean>();
    }

    public void add(ShopsBean sb) {
        list.add(sb);
    }

    public ArrayList<ShopsBean> get() {
        return list;
    }

    public ShopsBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public Iterator<ShopsBean> iterator() {
        return list.iterator();
    }

    public boolean contains(ShopsBean obj) {
        return list.contains(obj);
    }
}

