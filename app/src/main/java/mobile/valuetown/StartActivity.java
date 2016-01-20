package mobile.valuetown;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile.valuetown.async.DownloadTask;
import mobile.valuetown.bdd.CurrentStore;
import mobile.valuetown.bdd.Store;
import mobile.valuetown.meta.AsyncResponse;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        c = this;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_users) {
            Intent i = new Intent(StartActivity.this, UserActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toastusers), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(StartActivity.this, InfoActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toastsetting), Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public static class TitlesFragment extends ListFragment implements AsyncResponse {
        int mCurCheckPosition = 0;
        final ArrayList<Store> realStore = new ArrayList<>();
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            //Requete
            String stringQ = "select * from store";

            //thread asynctask pour la requete
            DownloadTask dt = new DownloadTask(this);

            //appel a doItBackground
            dt.execute(stringQ);

        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Store s = realStore.get(position);
            CurrentStore.getInstance().setCode(s.getCode());
            CurrentStore.getInstance().setVille(s.getVille());
            CurrentStore.getInstance().setAddr(s.getAddr());
            CurrentStore.getInstance().setTel(s.getTel());
            CurrentStore.getInstance().setLat(s.getLat());
            CurrentStore.getInstance().setLon(s.getLon());

            mCurCheckPosition = position;
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            Toast.makeText(c, getString(R.string.toast_mag)+" " + CurrentStore.getInstance().getVille(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }


        public void processFinish(String result) {
            final ArrayList<String> stores = new ArrayList<>();


            System.out.println(result);

            try {
                JSONArray res = new JSONArray(result);

                for (int i = 0; i < res.length(); i++) {
                    //Pour chaque row.
                    JSONObject row = res.getJSONObject(i);
                    int c = row.getInt("code");
                    String v = row.getString("ville");
                    String a = row.getString("adresse");
                    String t = row.getString("tel");
                    double la = row.getDouble("latitude");
                    double lo = row.getDouble("longitude");
                    Store s = new Store(c,v,a,t,la,lo);
                    realStore.add(s);
                }

            for (Store p : realStore){
                stores.add(p.getVille());
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, stores));
        }
    }
}


