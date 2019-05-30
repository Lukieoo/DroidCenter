package com.anioncode.droidcenter.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.anioncode.droidcenter.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ActualFragment extends Fragment {
    View view;
    static int PUNKTY=0;
    private ImageView rotateImage;
    private TextView cash;
    private TextView info;
    private SharedPreferences preferences;

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.losuj_layout,container,false);

        rotateImage = (ImageView) view.findViewById(R.id.image2);
        cash=(TextView) view.findViewById(R.id.cash);
        info=(TextView) view.findViewById(R.id.texttytul);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        final String data= String.valueOf(localDate);

        preferences = view.getContext().getSharedPreferences("acess", Activity.MODE_PRIVATE);
        preferences = view.getContext().getSharedPreferences("ilosc", Activity.MODE_PRIVATE);

        final SharedPreferences.Editor preferencesEditor = preferences.edit();

        String textFromPreferences = preferences.getString("acess", "");
        String ilosc = preferences.getString("ilosc", "0");

        PUNKTY=Integer.parseInt(ilosc);





        if(data.equals(textFromPreferences)){
            cash.setTextColor(Color.parseColor("#ff669900"));
            rotateImage.setImageResource(R.drawable.check);
            rotateImage.setEnabled(false);
            info.setText("Dziękujemy na dziś !\n Twój bonus został zebrany");


        }
        else{

            rotateImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation startRotateAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.rotato);
                    rotateImage.startAnimation(startRotateAnimation);



                    startRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Random generator = new Random();
                            int losowana=generator.nextInt(100);


                            System.out.println(losowana);
                            if(losowana >90){
                                rotateImage.setImageResource(R.drawable.andr);
                                Animation move = AnimationUtils.loadAnimation(getContext(), R.anim.move);
                                cash.setText("+5 droidek");
                                cash.startAnimation(move);
                                PUNKTY+=5;
                            }
                            else{
                                rotateImage.setImageResource(R.drawable.and);
                                Animation move = AnimationUtils.loadAnimation(getContext(), R.anim.move);
                                cash.setText("+1 droidek");
                                PUNKTY+=1;
                                cash.startAnimation(move);
                                rotateImage.setImageResource(R.drawable.and);
                            }

                            preferencesEditor.putString("acess",data);
                            preferencesEditor.putString("ilosc",String.valueOf(PUNKTY));
                            preferencesEditor.commit();



                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    cash.setText("");
                                    info.setText("Dziękujemy na dziś !\n Twój bonus został zebrany");
                                    cash.setTextColor(Color.parseColor("#ff669900"));
                                    rotateImage.setImageResource(R.drawable.check);
                                    rotateImage.setEnabled(false);
                                }
                            },2000);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            animation.setRepeatCount(Animation.ABSOLUTE);
                        }
                    });
                }
            });

        }

        return  view;
    }


}