package com.malwinakapala;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.malwinakapala.densitycalc.R;


public class MainActivity extends AppCompatActivity {

    private boolean dpActivityShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reloadCurrentFragment();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_dp_px) {
            reloadCurrentFragment();
        }
        return true;
    }

    private void reloadCurrentFragment() {
        dpActivityShown = !dpActivityShown;


        Fragment fragment;

        if (dpActivityShown) {
            fragment = new PxActivity();
        } else {
            fragment = new DpFragment();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, fragment);
        ft.commit();
    }
}

