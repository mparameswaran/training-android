package com.madan.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
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
        if (id == R.id.action_settings) {
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
            TextView signIn = (TextView) rootView.findViewById(R.id.sign_in);

            SharedPreferences preferences = this.mainMactivity.getApplicationContext().getSharedPreferences("default_preferences", getMainMactivity().MODE_PRIVATE);
            Boolean loggedIn = preferences.getBoolean("isLoggedIn", false);
            String username = preferences.getString("username", getResources().getString(R.string.registered_user));
            Log.i("Is Logged in", loggedIn.toString() );
            if (loggedIn){

                Toast.makeText(this.mainMactivity.getApplicationContext(), "Logged in as "+username, Toast.LENGTH_LONG).show();
                signIn.setVisibility(View.INVISIBLE);
            }
            else{


                signIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.i("Click", "sign in");
                        Intent intent = new Intent(getMainMactivity(),SignInActivity.class);
                        startActivity(intent);
                    }
                });
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
