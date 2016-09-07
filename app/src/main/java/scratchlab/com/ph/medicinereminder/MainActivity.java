package scratchlab.com.ph.medicinereminder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.Fragment;

import scratchlab.com.ph.medicinereminder.activities.SettingsActivity;
import scratchlab.com.ph.medicinereminder.fragments.Measurements;
import scratchlab.com.ph.medicinereminder.fragments.MedicineBox;
import scratchlab.com.ph.medicinereminder.fragments.NavigationDrawerFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //home button as either icon or logo
        /*
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        */
        //object of the fragment, id of the fragment drawer is added here
        //MedicineBox mb = new MedicineBox();


        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
        getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout)findViewById(R.id.activity_main), toolbar);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()){
        case R.id.action_settings:
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/

}
