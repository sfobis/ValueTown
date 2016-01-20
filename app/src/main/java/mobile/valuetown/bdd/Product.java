package mobile.valuetown.bdd;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class Product {

    String name;
    String categorie;
    int price;
    String ing;

    public Product(String n,String c,int p, String i){
        name = n;
        categorie = c;
        ing = i;
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

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }
}
