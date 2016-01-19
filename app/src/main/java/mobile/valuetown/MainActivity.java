package mobile.valuetown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

import mobile.valuetown.adapt.adapt_list2;
import mobile.valuetown.async.DownloadTask;
import mobile.valuetown.bdd.Cart;
import mobile.valuetown.meta.AsyncResponse;
import mobile.valuetown.meta.BaseActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,AsyncResponse {

    public ViewPager vp;
    public Context mContext;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cart = new Cart();
        //async
        //Requete
        String stringQ = "select ville from store";

        //thread asynctask pour la requete
        DownloadTask dt = new DownloadTask(this);

        //appel a doItBackground
        dt.execute(stringQ);



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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_store) {
            Intent i = new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toaststore), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_users) {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toastusers), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_mystore) {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toastmystore), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(MainActivity.this, UserActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), getString(R.string.toastsetting), Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void processFinish(String result) {
        final ArrayList<String> stores = new ArrayList<String>();
        System.out.println(result);
        try {
            JSONArray res = null;
            res = new JSONArray(result);

            for (int i = 0; i < res.length(); i++) {
                //Pour chaque row.
                JSONObject row = res.getJSONObject(i);
                String s = row.getString("ville");
                stores.add(s);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mContext = this;
        ListView listview1 = new ListView(mContext);
        ListView listview2 = new ListView(mContext);
        ListView listview3 = new ListView(mContext);

        Vector<View> pages = new Vector<View>();

        pages.add(listview1);
        pages.add(listview2);
        pages.add(listview3);

        vp = (ViewPager) findViewById(R.id.viewpager);

        adapt_list2 adapter = new adapt_list2(mContext,pages);
        vp.setAdapter(adapter);

        listview1.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,stores));
        listview2.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,stores));
        listview3.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,stores));

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });


    }


}
