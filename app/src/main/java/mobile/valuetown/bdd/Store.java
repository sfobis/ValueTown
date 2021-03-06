package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 04/01/2016.
 */
public class Store {

    private int id;
    private String ville;
    private int code;
    private String addr;
    private String tel;
    private double lat;
    private double lon;

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Store(int c, String v, String a, String t, Double la, Double lo){
        this.ville = v;
        this.code = c;
        this.addr = a;
        this.tel = t;

        this.lat = la;
        this.lon = lo;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Store(){}

    public String getTel() {
        return tel;
    }
}