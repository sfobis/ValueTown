package mobile.valuetown.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobile.valuetown.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeverageFragment extends Fragment {

    TextView tv;
    OnBeverageInteractionListener mCallback;

    // Container Activity must implement this interface
    public interface OnBeverageInteractionListener {
        public void onArticleSelected(int position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBeverageInteractionListener) {
            mCallback = (OnBeverageInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public BeverageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_beverage, container, false);
    }

}
