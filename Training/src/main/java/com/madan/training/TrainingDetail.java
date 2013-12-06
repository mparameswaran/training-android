package com.madan.training;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
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
                    .add(R.id.container, new PlaceholderFragment(this))
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
        public PlaceholderFragment(TrainingDetail trainingDetail) {
            setDetailActivity(trainingDetail);
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

            return rootView;
        }

        public TrainingDetail getDetailActivity() {
            return detailActivity;
        }

        public void setDetailActivity(TrainingDetail detailActivity) {
            this.detailActivity = detailActivity;
        }
    }

}
