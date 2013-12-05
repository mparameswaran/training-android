package com.madan.training;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

public class TrainingMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.training_main, menu);
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
            case R.id.sign_in_menu:
                Intent intent = new Intent(TrainingMainActivity.this,SignInActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
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
            viewByTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("Click", "sign in");
                    Intent intent = new Intent(getMainMactivity(), SignInActivity.class);
                    startActivity(intent);
                }
            });
            EditText search = (EditText) rootView.findViewById(R.id.input_search);
            SharedPreferences preferences = this.mainMactivity.getApplicationContext().getSharedPreferences("default_preferences", getMainMactivity().MODE_PRIVATE);
            Boolean loggedIn = preferences.getBoolean("isLoggedIn", false);
            String username = preferences.getString("username", getResources().getString(R.string.registered_user));
            Log.i("Is Logged in", loggedIn.toString() );
            if (loggedIn){

                Toast.makeText(this.mainMactivity.getApplicationContext(), "Logged in as "+username, Toast.LENGTH_LONG).show();
                search.setHint(getResources().getString(R.string.keeping_you_awake));

            }
            else{

                search.setHint(getResources().getString(R.string.search_KLOUD));

            }


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
