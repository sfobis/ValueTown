package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class User {

    //singleton
    private static User instance = new User();

    private  String name;
    private  String surname;
    private  String addr;
    private  int code;
    private String tel;

    private User(){
        name = "";
        surname = "";
        addr = "";
        code = 0;
        tel = "";


    }

    public static User getInstance() {
        return instance;
    }

    //méthodes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
