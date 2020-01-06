package com.anioncode.droidcenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anioncode.droidcenter.fragment.Dashboard_Fragment;
import com.anioncode.droidcenter.fragment.PanelFragment;
import com.anioncode.droidcenter.fragment.Profil_space;
import com.anioncode.droidcenter.scaledrone.MessangerFragment;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.anioncode.droidcenter.SplashScreen.EMAIL;
import static com.anioncode.droidcenter.SplashScreen.NAME;
import static com.anioncode.droidcenter.SplashScreen.PHOTO_URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle)
        drawer = findViewById(R.id.drawer_layout);
        linearLayout = findViewById(R.id.line1);



        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUseremail = (TextView) headerView.findViewById(R.id.useremail);
        TextView navUsername = (TextView) headerView.findViewById(R.id.username);
        CircleImageView circleImageView = headerView.findViewById(R.id.profile_pic);
        navUseremail.setText(EMAIL);
        navUsername.setText(NAME);
        Glide.with(MainActivity.this).load(PHOTO_URL).into(circleImageView);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                linearLayout.setTranslationX(slideX);

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Dashboard_Fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_panel);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_panel:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dashboard_Fragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.web:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PanelFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nawv_actual:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MessangerFragment()).commit();
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_profile:

                Intent intent = new Intent(this, Profil_space.class);
               /// if (Build.VERSION.SDK_INT > 20) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
//                    startActivity(intent, options.toBundle());
//                } else {
                    startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
//                }


//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ProfileFragment()).commit();
                break;
            case R.id.nav_settings:

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","pawkrzysciak@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, NAME);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Witaj");
                startActivity(Intent.createChooser(emailIntent, "Zapytanie"));

                break;
            case R.id.nav_out:

                        Intent intentx = new Intent(MainActivity.this, SplashScreen.class);
                        startActivity(intentx);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                        finish();


                break;
        }


        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}