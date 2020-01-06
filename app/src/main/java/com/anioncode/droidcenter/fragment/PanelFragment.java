package com.anioncode.droidcenter.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v4.app.FragmentTransaction;

import com.anioncode.droidcenter.R;


public class PanelFragment extends Fragment {
    View view;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.panel_layout,container,false);
        imageView1=view.findViewById(R.id.fb);
        imageView2=view.findViewById(R.id.in);
        imageView3=view.findViewById(R.id.sk);
        imageView4=view.findViewById(R.id.gi);



        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, new WebViewFragment(1)).commit();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                transaction.replace(R.id.framelayout, new WebViewFragment(1)).commit();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                transaction.replace(R.id.framelayout, new WebViewFragment(2)).commit();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                transaction.replace(R.id.framelayout, new WebViewFragment(3)).commit();

                Intent intent = new Intent(getActivity(), Dashboard_Fragment.class);

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

                transaction.replace(R.id.framelayout, new WebViewFragment(4)).commit();
            }
        });

        return  view;
    }


}