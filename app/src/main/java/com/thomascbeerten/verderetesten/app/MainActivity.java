package com.thomascbeerten.verderetesten.app;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
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
    }
}
