package com.madan.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrainingDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_detail);

        if(savedInstanceState == null)
        {
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
        switch (id){
            case R.id.action_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getResources().getString(R.string.about_title));
                builder.setMessage(getResources().getString(R.string.about_message));
                builder.setPositiveButton(getResources().getString(R.string.okay),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                AlertDialog alert = builder.create();
                alert.show();
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
            setClickListenerForImageViews(rootView);
            setImageViewBehavior(rootView);
            TextView aboutTraining = (TextView) rootView.findViewById(R.id.about_this_training);
            aboutTraining.setText(String.format("About %s Training", getDetailTitle()));
            ListView listView = (ListView) rootView.findViewById(R.id.learning_objectives_list);
            LearningObjectiveAdapter adapter = new LearningObjectiveAdapter(this.getActivity(),R.layout.learning_objective_list_item,listOfObjectives);
            listView.setAdapter(adapter);

            SharedPreferences preferences = getDetailActivity().getApplicationContext().getSharedPreferences("default_preferences", getDetailActivity().MODE_PRIVATE);
            Boolean isLoggedIn = preferences.getBoolean("isLoggedIn",false);
            TextView myRatingText = (TextView) rootView.findViewById(R.id.my_rating_title);
            RatingBar myRating = (RatingBar) rootView.findViewById(R.id.my_rating);

            rateOverall(rootView);
            TextView peopleAttended = (TextView) rootView.findViewById(R.id.people_also_attended);
            peopleAttended.setText(String.format("People who attended %s also attended...", getDetailTitle()));
            Button register = (Button) rootView.findViewById(R.id.register_button);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getDetailActivity());
                    builder.setTitle(getResources().getString(R.string.register_button_title));
                    builder.setMessage(getResources().getString(R.string.register_not_functional));
                    builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
            if(isLoggedIn){

                myRatingText.setText(R.string.signed_in_my_rating_title);
                myRating.setVisibility(View.VISIBLE);
                myRating(rootView);
            }
            else{
                myRatingText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getDetailActivity());
                        builder.setTitle(String.format("Sign In",getDetailTitle()));
                        builder.setMessage(R.string.sign_in_not_functional);
                        builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                });
                myRating.setVisibility(View.INVISIBLE);
            }

            return rootView;
        }

        private void setImageViewBehavior(View rootView) {


            RelativeLayout eCommerce = (RelativeLayout) rootView.findViewById(R.id.training_one);
            RelativeLayout rails = (RelativeLayout) rootView.findViewById(R.id.training_two);
            RelativeLayout dynamicsAX = (RelativeLayout)rootView.findViewById(R.id.training_three);
            RelativeLayout android = (RelativeLayout) rootView.findViewById(R.id.training_four);
            RelativeLayout git =(RelativeLayout) rootView.findViewById(R.id.training_five);
            RelativeLayout python = (RelativeLayout) rootView.findViewById(R.id.training_six);



            if(getDetailTitle().equals(getResources().getString(R.string.training_one))){

                rails.setVisibility(View.GONE);
                git.setVisibility(View.GONE);
                eCommerce.setVisibility(View.GONE);

            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_two))){

                rails.setVisibility(View.GONE);
                eCommerce.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_three))){

                rails.setVisibility(View.GONE);
                git.setVisibility(View.GONE);
                android.setVisibility(View.GONE);
                python.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_four))){

                eCommerce.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
                rails.setVisibility(View.GONE);
                android.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_five))){

                git.setVisibility(View.GONE);
                eCommerce.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
                android.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_six))){

                python.setVisibility(View.GONE);
                rails.setVisibility(View.GONE);
                android.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals("Market")){

                git.setVisibility(View.GONE);
                rails.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals("Finance")){

                android.setVisibility(View.GONE);
                python.setVisibility(View.GONE);
            }
            if(getDetailTitle().equals("Learning")){

                eCommerce.setVisibility(View.GONE);
                dynamicsAX.setVisibility(View.GONE);
            }

        }

        private void setClickListenerForImageViews(View rootView) {
            final View replaceView = rootView;
            LinearLayout carousel = (LinearLayout) rootView.findViewById(R.id.detail_screen_carousel);
            final int trainingPrograms = carousel.getChildCount();
            for (int i = 0; i < trainingPrograms; i++){

                RelativeLayout trainingProgram = (RelativeLayout) carousel.getChildAt(i);
                final TextView trainingTextView = (TextView) trainingProgram.getChildAt(1);
                trainingProgram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String training = trainingTextView.getText().toString();

                        getDetailActivity().getActionBar().setTitle(training);

                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, new TrainingDetail.PlaceholderFragment(getDetailActivity(), training));
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    }
                });

            }
        }

        private void myRating( View rootView) {
            RatingBar myRating = (RatingBar) rootView.findViewById(R.id.my_rating);
            if(getDetailTitle().equals("Market")){
                myRating.setRating((float) 2.5);
                return;
            }
            if(getDetailTitle().equals("Finance")){
                myRating.setRating((float) 4.5);
                return;
            }
            if(getDetailTitle().equals("Learning")){
                myRating.setRating((float) 4.0);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_one))){
                myRating.setRating((float) 0.0);
                notYetRated(rootView);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_two))){
                myRating.setRating((float) 4.5);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_three))){
                myRating.setRating((float) 2.0);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_four))){
                myRating.setRating((float) 0.0);
                notYetRated(rootView);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_five))){
                myRating.setRating((float) 4.5);
                return;
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_six))){
                myRating.setRating((float) 3.0);
                return;
            }
        }

        private void notYetRated(View rootView) {
            TextView myRatingTitle = (TextView) rootView.findViewById(R.id.my_rating_title);
            myRatingTitle.setText(R.string.not_yet_rated);
            myRatingTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getDetailActivity());
                    builder.setTitle(String.format("Rate %s",getDetailTitle()));
                    builder.setMessage(R.string.rating_not_functional);
                    builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }

        private void rateOverall(View rootView) {

            RatingBar overallRating = (RatingBar) rootView.findViewById(R.id.overall_rating);
            if(getDetailTitle().equals(getResources().getString(R.string.training_one))){
                overallRating.setRating((float) 3.5);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_two))){
                overallRating.setRating((float) 4.5);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_three))){
                overallRating.setRating((float) 3.5);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_four))){
                overallRating.setRating((float) 3.0);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_five))){
                overallRating.setRating((float) 4.0);
            }
            if(getDetailTitle().equals(getResources().getString(R.string.training_six))){
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
