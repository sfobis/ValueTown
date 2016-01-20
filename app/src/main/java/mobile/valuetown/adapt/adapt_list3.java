package mobile.valuetown.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.valuetown.R;
import mobile.valuetown.bdd.Product;

/**
 * Created by stacyqt on 20/01/2016.
 */
public class adapt_list3 extends ArrayAdapter<Product> {

    private ArrayList<Product> product = new ArrayList<>();
    private int layoutID;
    private Context mcontext;

    public adapt_list3(Context c, int i, ArrayList<Product> a){
        super(c,i,a);
        this.layoutID = i;
        this.mcontext = c;
        this.product = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater =  (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layoutID,parent,false);

        TextView textView = (TextView) v.findViewById(R.id.label);
        ImageView imageView = (ImageView) v.findViewById(R.id.icon);
        textView.setText(product.get(position).getName());
        imageView.setImageResource(R.drawable.ic_add_shopping_cart_black_48dp);
        return v;
    }

}
