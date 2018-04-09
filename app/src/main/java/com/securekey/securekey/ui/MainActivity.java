package com.securekey.securekey.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.securekey.securekey.R;
import com.securekey.securekey.ui.bitmanipulation.BitFragment;
import com.securekey.securekey.ui.textparser.ParserFragment;

public class MainActivity extends AppCompatActivity implements ParserFragment.OnFragmentInteractionListener{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener () {

        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId ()) {
                case R.id.navigation_home:
                    Fragment parserFragment = new ParserFragment ();
                    transaction.replace(R.id.main_fragment, parserFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_second:
                    Fragment bitFragment = new BitFragment ();
                    transaction.replace(R.id.main_fragment, bitFragment);
                    transaction.commit();
                    return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        BottomNavigationView navigation=(BottomNavigationView) findViewById (R.id.navigation);
        navigation.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId (R.id.navigation_home);
    }

    @Override
    public void onFragmentInteraction (Uri uri) {

    }
}
