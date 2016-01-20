package mobile.valuetown.adapt;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

import mobile.valuetown.R;

/**
 * Created by stacyqt on 11/01/2016.
 */
public class adapt_list2 extends PagerAdapter {

    private Context mContext;
    private Vector<View> pages;

    public adapt_list2(Context context, Vector<View> pages) {
        this.mContext=context;
        this.pages=pages;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = pages.get(position);
        container.addView(page);
        return page;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return mContext.getString(R.string.starter);
        }
        if (position == 1){
            return mContext.getString(R.string.meal);
        }
        if (position == 2){
            return mContext.getString(R.string.drink);
        }
        return "Title Here";
    }

}
