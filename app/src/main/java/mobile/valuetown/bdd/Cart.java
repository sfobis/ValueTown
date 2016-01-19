package mobile.valuetown.bdd;

import java.util.ArrayList;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class Cart {

    //Singleton
    private static Cart instance = new Cart();

    private ArrayList<Product> products;

    private Cart(){
        products = new ArrayList<>();
    }

    public static Cart getInstance() {
        return instance;
    }

    //methodes
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

}
