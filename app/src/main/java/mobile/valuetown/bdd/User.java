package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class User {

    private static User instance = new User();;

    private  String name;
    private  String surname;
    private  String addr;
    private  String code;

    private User(){
        name = "Your name";
        surname = "Your Surname";
        addr = "Your Address";
        code = "Your city code";

    }


    public static User getInstance() {
        return instance;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
