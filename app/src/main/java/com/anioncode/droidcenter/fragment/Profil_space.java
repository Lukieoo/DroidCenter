package com.anioncode.droidcenter.fragment;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anioncode.droidcenter.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.anioncode.droidcenter.SplashScreen.EMAIL;
import static com.anioncode.droidcenter.SplashScreen.NAME;
import static com.anioncode.droidcenter.SplashScreen.PHOTO_URL;
import static com.anioncode.droidcenter.fragment.ActualFragment.PUNKTY;

public class Profil_space extends AppCompatActivity {


    private boolean isOpen = false;
    private ConstraintSet layout1, layout2;
    private ConstraintLayout constraintLayout;
    private CircleImageView imageViewPhoto;
    private TextView textView1;
    private TextView textView2;
    private TextView droidki;


    private ImageButton imageButton;
    private Button sendDroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setAnimation();
        setContentView(R.layout.profilefragment_layout);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayoutsss,
                new ActualFragment()).commit();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        imageViewPhoto = findViewById(R.id.photo);
        constraintLayout = findViewById(R.id.constraint_layout);
        imageButton = findViewById(R.id.imageButton);
        sendDroid = findViewById(R.id.sendDroid);
        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView1.setText(NAME);
        textView2.setText(EMAIL);

        sendDroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,NAME+" Posiada już "+PUNKTY+" droidek ! ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "DroidCenter"));

            }
        });

        droidki = findViewById(R.id.droidki);
        droidki.setText(String.valueOf(PUNKTY) + " Droidki");

        Glide.with(Profil_space.this).load(PHOTO_URL).into(imageViewPhoto);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout2.clone(this, R.layout.profile_expanded);
        layout1.clone(constraintLayout);

        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!isOpen) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout2.applyTo(constraintLayout);
                    isOpen = !isOpen;
                } else {

                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout1.applyTo(constraintLayout);
                    isOpen = !isOpen;

                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //    public void setAnimation()
//    {
//        if(Build.VERSION.SDK_INT>20) {
//            Slide slide = new Slide();
//            slide.setSlideEdge(Gravity.RIGHT);
//            slide.setDuration(400);
//            slide.setInterpolator(new AccelerateDecelerateInterpolator());
//            getWindow().setExitTransition(slide);
//            getWindow().setEnterTransition(slide);
//        }
//    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}