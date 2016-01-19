package mobile.valuetown.meta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mobile.valuetown.bdd.Cart;
import mobile.valuetown.bdd.StoreBdd;

/**
 * Created by stacyqt on 17/01/2016.
 */
public class BaseActivity extends AppCompatActivity {

    public static StoreBdd sBDD;
    public static Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    protected void onPause() {

        sBDD.close();
        super.onPause();
    }

    @Override
    protected void onResume() {

        //Create Bdd

        sBDD.open();
        super.onResume();

    }

}
