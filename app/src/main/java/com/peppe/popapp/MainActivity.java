package com.peppe.popapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.peppe.popapp.fragments.FilmFragment;
import com.peppe.popapp.fragments.InfoFragment;
import com.peppe.popapp.fragments.BigliettiFragment;
import com.peppe.popapp.fragments.PosterFragment;
import com.peppe.popapp.fragments.ProgrammazioneFragment;
import com.peppe.popapp.fragments.SaleFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProgrammazioneFragment programmazioneFragment = new ProgrammazioneFragment();
        fragmentTransaction.replace(R.id.fragment_container, programmazioneFragment).commit();
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_programmazione) {
            ProgrammazioneFragment programmazioneFragment = new ProgrammazioneFragment();
            fragmentTransaction.replace(R.id.fragment_container, programmazioneFragment).commit();

        } else if (id == R.id.nav_prezzi) {
            BigliettiFragment bigliettiFragment = new BigliettiFragment();
            fragmentTransaction.replace(R.id.fragment_container, bigliettiFragment).commit();

        } else if (id == R.id.nav_sale) {
            SaleFragment saleFragment = new SaleFragment();
            fragmentTransaction.replace(R.id.fragment_container, saleFragment).commit();

        } else if (id == R.id.nav_info) {
            InfoFragment infoFragment = new InfoFragment();
            fragmentTransaction.replace(R.id.fragment_container, infoFragment).commit();

        } else if (id == R.id.nav_registrazione) {
            Intent registrazione = new Intent(this, RegistrazioneActivity.class);
            startActivity(registrazione);

        } else if (id == R.id.nav_accesso) {
            Intent accesso = new Intent(this, AccessoActivity.class);
            startActivity(accesso);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
