package Bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopsDTO implements Serializable {
    private ArrayList<ShopsBean> list;

    public ShopsDTO() {
        list = new ArrayList<ShopsBean>();
    }

    public void add(ShopsBean sb) {
        list.add(sb);
    }

    public ShopsBean get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }
}
