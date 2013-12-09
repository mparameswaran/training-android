package com.madan.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TrainingMainActivity extends Activity {
    private Boolean isLoggedInFlag;
    private Menu menu;
    private Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_main);
        SharedPreferences preferences = this.getApplicationContext().getSharedPreferences("default_preferences", this.MODE_PRIVATE);
        setIsLoggedInFlag(preferences.getBoolean("isLoggedIn", false));
        PlaceholderFragment defaultHomeScreen = new PlaceholderFragment(this);
        SignInActivity.mCallingActivity = TrainingMainActivity.this;
        if(preferences.getBoolean("didLogOut",false)){
            Toast.makeText(this, getResources().getString(R.string.logged_out), Toast.LENGTH_SHORT).show();
            preferences.edit().putBoolean("didLogOut", false).commit();

        }
        EditText search = (EditText) findViewById(R.id.input_search);

        if (savedInstanceState == null) {
            if(!getIsLoggedInFlag()){
 
                    getFragmentManager().beginTransaction()
                        .replace(R.id.container, defaultHomeScreen)
                        .commit();
                search.setHint(getResources().getString(R.string.search_KLOUD));


            }
            else{


                getFragmentManager().beginTransaction().add(R.id.container, new SignedInHomeScreenFragment(this)).commit();
                search.setHint(getResources().getString(R.string.keeping_you_awake));
            }

        }
        else {
            this.savedInstanceState = savedInstanceState;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.

        this.menu = menu;
        if(getIsLoggedInFlag()){
            getMenuInflater().inflate(R.menu.training_main_logout, menu);

        }
        else{
            getMenuInflater().inflate(R.menu.training_main, menu);
        }


        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
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
            case R.id.sign_in_menu:
                Intent intent = new Intent(TrainingMainActivity.this,SignInActivity.class);
                startActivity(intent);
                this.invalidateOptionsMenu();
                return true;
            case R.id.log_out_menu:
                SharedPreferences preferences = (TrainingMainActivity.this).getApplicationContext().getSharedPreferences("default_preferences", this.MODE_PRIVATE);
                preferences.edit().putBoolean("isLoggedIn",false).commit();
                preferences.edit().putString("username", "").commit();
                preferences.edit().putBoolean("didLogOut",true).commit();

                invalidateOptionsMenu();

                
                Intent refresh = new Intent(TrainingMainActivity.this, TrainingMainActivity.class);
                startActivity(refresh);
                finish();


                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    public Menu getMenu() {
        return menu;
    }

    public Boolean getIsLoggedInFlag() {
        return isLoggedInFlag;
    }

    public void setIsLoggedInFlag(Boolean isLoggedInFlag) {
        this.isLoggedInFlag = isLoggedInFlag;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private Activity mainMactivity;
        public PlaceholderFragment(Activity activity) {
            setMainMactivity(activity);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View  rootView = inflater.inflate(R.layout.fragment_training_main, container, false);

            rootView.requestFocus();
            TextView viewByTheme = (TextView) rootView.findViewById(R.id.view_by_theme);
            LinearLayout carousel = (LinearLayout) rootView.findViewById(R.id.linear_layout_carousel);
            final int trainingPrograms = carousel.getChildCount();
            for (int i = 0; i < trainingPrograms; i++){

                RelativeLayout trainingProgram = (RelativeLayout) carousel.getChildAt(i);
                final TextView trainingTextView = (TextView) trainingProgram.getChildAt(1);
                trainingProgram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getMainMactivity(), TrainingDetail.class);
                        String training = trainingTextView.getText().toString();
                        intent.putExtra("title", training);
                        startActivity(intent);
                    }
                });

            }
            viewByTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });

            return rootView;
        }


        public Activity getMainMactivity() {
            return mainMactivity;
        }

        public void setMainMactivity(Activity mainMactivity) {
            this.mainMactivity = mainMactivity;
        }
    }

}
