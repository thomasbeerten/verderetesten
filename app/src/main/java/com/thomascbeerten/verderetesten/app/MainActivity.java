package com.thomascbeerten.verderetesten.app;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] menuOptions;
    private ActionBarDrawerToggle drawerToggle;

    private boolean draweropen;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (ListView) findViewById(R.id.drawerList);

        menuOptions = getResources().getStringArray(R.array.menuoptions);

        listView = (ListView) findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuOptions));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SelectItem(i);
                Log.d("select item", String.valueOf(i));

                Toast.makeText(getBaseContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        //check state drawer
        if (savedInstanceState != null) {
            boolean draweropen = savedInstanceState.getBoolean("drawerstate");
            if (draweropen) {
                drawerLayout.openDrawer(listView);
            } else {
                drawerLayout.closeDrawer(listView);
            }
        } else {
            //open state to start with
            drawerLayout.openDrawer(listView);
        }


    }

    private void SelectItem(int i) {
        listView.setItemChecked(i, true);
        getSupportActionBar().setTitle(menuOptions[i]);
        switch (i) {
            case 0:
                ReplaceFragment(new FragmentPage1());
                break;
            case 1:
                ReplaceFragment(new FragmentPage2());
                break;
            case 2:
                ReplaceFragment(new FragmentPage3());
                break;
        }
    }

    private void ReplaceFragment(Fragment fragment) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContent, fragment);
        fragmentTransaction.commit();

        drawerLayout.closeDrawer(listView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (drawerLayout.isDrawerOpen(listView)) {
            draweropen = true;
        } else {
            draweropen = false;
        }
        outState.putBoolean("drawerstate", draweropen);
        super.onSaveInstanceState(outState);

    }
}
