package com.madan.training;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by citrisys on 12/6/13.
 */
public class SignedInHomeScreenFragment extends Fragment {

    private Activity homeActivity;

    public SignedInHomeScreenFragment(Activity activity){
        setHomeActivity(activity);
    }

    public Activity getHomeActivity() {
        return homeActivity;
    }

    public void setHomeActivity(Activity homeActivity) {
        this.homeActivity = homeActivity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_signed_in_home, container, false);
        rootView.requestFocus();
        ImageView market = (ImageView) rootView.findViewById(R.id.market);
        ImageView finance = (ImageView) rootView.findViewById(R.id.finance);
        ImageView learning = (ImageView) rootView.findViewById(R.id.learning);
        final Intent intent = new Intent(getHomeActivity(), TrainingDetail.class);
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title","Market");
                startActivity(intent);
            }
        });
        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title","Finance");
                startActivity(intent);
            }
        });
        learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("title","Learning");
                startActivity(intent);
            }
        });

        return rootView;
    }
}
