package mobile.valuetown.bdd;

import java.util.ArrayList;

/**
 * Created by stacyqt on 18/01/2016.
 */
public class Cart {

    private ArrayList<Product> products;

    public Cart(){
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

}
