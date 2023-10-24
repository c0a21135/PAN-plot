package Bean;

public class PanTestBean {
     /**
     * パンのID
     */
    private int panId;

    public PanTestBean() {
    }

    public PanTestBean(int panId){
        this();
        this.panId = panId;
    }

    public int getPanId() {
        return panId;
    }

    public void setPanId(int panId) {
        this.panId = panId;
    }
    

    
}
