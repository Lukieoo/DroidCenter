package com.anioncode.droidcenter.fragment;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.anioncode.droidcenter.R;

import static com.anioncode.droidcenter.fragment.ActualFragment.PUNKTY;


public class ProfileFragment extends Fragment {
    View view;

    private boolean isOpen = false ;
    private ConstraintSet layout1,layout2;
    private ConstraintLayout constraintLayout ;
    private ImageView imageViewPhoto;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.profilefragment_layout,container,false);




        Window w = getActivity().getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        imageViewPhoto = view.findViewById(R.id.photo);
        constraintLayout = view.findViewById(R.id.constraint_layout);




        layout2.clone(view.getContext(),R.layout.profile_expanded);
        layout1.clone(constraintLayout);

        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!isOpen) {
                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout2.applyTo(constraintLayout);
                    isOpen = !isOpen ;
                }

                else {

                    TransitionManager.beginDelayedTransition(constraintLayout);
                    layout1.applyTo(constraintLayout);
                    isOpen = !isOpen ;

                }






            }
        });

        return  view;
    }


}