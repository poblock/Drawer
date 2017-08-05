package pl.poblocki.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.R;
import pl.poblocki.drawer.demo.AlbumsFragment;
import pl.poblocki.drawer.flights.FlightsFragment;
import pl.poblocki.drawer.flights.FlightsPresenter;
import pl.poblocki.drawer.view.ButtonFragment;

public class AirportActivity
        extends AppCompatActivity
//        extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        AirportApplication.component()//.plus(new ActivityModule(this))
                .inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment f = AlbumsFragment.newInstance();
        if(f!=null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.content_frame, f);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer!=null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        if(item.getItemId()==R.id.nav_arrivals || item.getItemId()==R.id.nav_departures) {
            FlightsFragment ff = (FlightsFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if(ff==null) {
                ff = FlightsFragment.newInstance(item.getItemId());
            }
            ft.replace(R.id.content_frame, ff);
            ft.commit();
            FlightsPresenter mPresenter = new FlightsPresenter(ff);
        } else if(item.getItemId()==R.id.nav_terminal) {
            ButtonFragment bf = (ButtonFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if(bf==null) {
                bf = ButtonFragment.newInstance(item.getItemId());
            }
            ft.replace(R.id.content_frame, bf);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
