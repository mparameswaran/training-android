package com.madan.training;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Madan on 12/5/13.
 */
public class LearningObjectiveAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResourceId;
    private List<String> listOfObjectives;

    public LearningObjectiveAdapter(Context context,int textViewResourceId, List<String> objects) {
        super(context,textViewResourceId, objects);

        setContext(context);
        setLayoutResourceId(textViewResourceId);
        setListOfObjectives(objects);
    }


    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getListOfObjectives() {
        return listOfObjectives;
    }

    public void setListOfObjectives(List<String> listOfObjectives) {
        this.listOfObjectives = listOfObjectives;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public class CellViewHolder{
        public TextView objective;
    }

    public View getView(int position, View converterView, ViewGroup parent){
        View cellView = converterView;
        CellViewHolder holder;
        if(cellView==null){
            LayoutInflater layoutInflater = ((Activity)getContext()).getLayoutInflater();
            cellView = layoutInflater.inflate(getLayoutResourceId(), parent, false);
            holder = new CellViewHolder();
            holder.objective = (TextView) cellView.findViewById(R.id.learning_objective);
            cellView.setTag(holder);
        }
        else {
            holder = (CellViewHolder)cellView.getTag();
        }

        String learningObjective = getListOfObjectives().get(position);
        holder.objective.setText(learningObjective.toString());


        return cellView;
    }
}
