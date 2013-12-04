package com.madan.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

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
            View rootView = inflater.inflate(R.layout.fragment_training_main, container, false);
            TextView signIn = (TextView) rootView.findViewById(R.id.sign_in);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.i("Click", "sign in");
                    Intent intent = new Intent(getMainMactivity(),SignInActivity.class);
                    startActivity(intent);
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
