package Bean;

public class TableNameBean { // TableNameは接続するデータベースのテーブル名で作る
    /*
     * データベースから取得した要素をオブジェクトとしてまとめる
     */

    /*
     * データベースのカラムを入れる変数を定義する
     */
    private int id; // id番号
    private String name; // 名前を入れる
    private String path; //　画像のパスを入れる？


    
    public TableNameBean() {
    }

    public TableNameBean(int id, String name, String path){
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}