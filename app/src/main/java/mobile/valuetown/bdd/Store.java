package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 04/01/2016.
 */
public class Store {

    private int id;
    private String ville;
    private String code;

    public Store(){}

    public Store(String v, String c){
        this.ville = v;
        this.code = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString(){
        return "id : "+id+"\nville : "+ ville +"\ncode : "+ code;
    }
}