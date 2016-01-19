package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class Product {

    String name;
    String categorie;
    int price;

    public Product(String n,String c,int p){
        name = n;
        categorie = c;
        price = p;
    }

    public String getName() {
        return name;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
