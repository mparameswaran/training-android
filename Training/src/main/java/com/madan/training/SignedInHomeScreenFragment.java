package com.madan.training;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        return rootView;
    }
}
