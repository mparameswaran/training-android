package com.madan.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrainingDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_detail);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(getIntent().getStringExtra("title"));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this, getIntent().getStringExtra("title")))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.training_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private TrainingDetail detailActivity;
        private String detailTitle;
        public PlaceholderFragment(TrainingDetail trainingDetail, String title) {
            setDetailActivity(trainingDetail);
            setDetailTitle(title);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_training_detail, container, false);
            String [] learningObjectives = new String[]{
                    getResources().getString(R.string.learning_objective_one),
                    getResources().getString(R.string.learning_objective_two),
                    getResources().getString(R.string.learning_objective_three)
            };
            final List<String> listOfObjectives = new ArrayList<String>();
            for(int i = 0; i < learningObjectives.length; i++){
                listOfObjectives.add(learningObjectives[i]);
            }

            ListView listView = (ListView) rootView.findViewById(R.id.learning_objectives_list);
            LearningObjectiveAdapter adapter = new LearningObjectiveAdapter(this.getActivity(),R.layout.learning_objective_list_item,listOfObjectives);
            listView.setAdapter(adapter);

            SharedPreferences preferences = getDetailActivity().getApplicationContext().getSharedPreferences("default_preferences", getDetailActivity().MODE_PRIVATE);
            Boolean isLoggedIn = preferences.getBoolean("isLoggedIn",false);
            TextView myRatingText = (TextView) rootView.findViewById(R.id.my_rating_title);
            RatingBar myRating = (RatingBar) rootView.findViewById(R.id.my_rating);
            RatingBar overallRating = (RatingBar)rootView.findViewById(R.id.overall_rating);
            rate(overallRating);
            if(isLoggedIn){

                myRatingText.setText(R.string.signed_in_my_rating_title);
                myRating.setVisibility(View.VISIBLE);
                myRating(myRating);
            }
            else{
                myRatingText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        SignInActivity.mCallingActivity = getDetailActivity();
//                        Intent intent = new Intent(getDetailActivity(), SignInActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
                    }
                });
                myRating.setVisibility(View.INVISIBLE);
            }

            return rootView;
        }

        private void myRating(RatingBar myRating) {
            if(getDetailTitle().equals("Market")){
                myRating.setRating((float) 2.5);
            }
            if(getDetailTitle().equals("Finance")){
                myRating.setRating((float) 4.5);
            }
            if(getDetailTitle().equals("Learning")){
                myRating.setRating((float) 4.0);
            }
        }

        private void rate(RatingBar overallRating) {

            if(getDetailTitle().equals(R.string.training_one)){
                overallRating.setRating((float) 3.5);
            }
            if(getDetailTitle().equals(R.string.training_two)){
                overallRating.setRating((float) 4.5);
            }
            if(getDetailTitle().equals(R.string.training_three)){
                overallRating.setRating((float) 3.5);
            }
            if(getDetailTitle().equals(R.string.training_four)){
                overallRating.setRating((float) 3.0);
            }
            if(getDetailTitle().equals(R.string.training_five)){
                overallRating.setRating((float) 4.0);
            }
            if(getDetailTitle().equals(R.string.training_six)){
                overallRating.setRating((float) 4.0);
            }
            if(getDetailTitle().equals("Market")){
                overallRating.setRating((float) 3.0);
            }
            if(getDetailTitle().equals("Finance")){
                overallRating.setRating((float) 3.5);
            }
            if(getDetailTitle().equals("Learning")){
                overallRating.setRating((float) 4.5);
            }




        }

        public TrainingDetail getDetailActivity() {
            return detailActivity;
        }

        public void setDetailActivity(TrainingDetail detailActivity) {
            this.detailActivity = detailActivity;
        }

        public String getDetailTitle() {
            return detailTitle;
        }

        public void setDetailTitle(String detailTitle) {
            this.detailTitle = detailTitle;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TrainingDetail.this, TrainingMainActivity.class);
        startActivity(intent);
    }
}
