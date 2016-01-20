package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 04/01/2016.
 */
public class CurrentStore {

    //singleton
    private static CurrentStore instance = new CurrentStore();

    private int id;
    private String ville;
    private int code;
    private String addr;
    private String tel;
    private double lat;
    private double lon;

    private CurrentStore(){
        this.id = 0;
        this.ville = "";
        this.code = 0;
        this.addr = "";
        this.tel = "";
        this.lat = 0.0;
        this.lon = 0.0;
    }

    public static CurrentStore getInstance() {
        return instance;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}