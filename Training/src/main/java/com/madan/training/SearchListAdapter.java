package com.madan.training;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Madan on 12/9/13.
 */
public class SearchListAdapter extends ArrayAdapter<Training>{

    private Context context;
    private int resourceId;
    private List<Training> listOfTrainingObjects;

    public SearchListAdapter(Context context, int textViewResourceId, List<Training> objects) {
        super(context, textViewResourceId, objects);
        setContext(context);
        setResourceId(textViewResourceId);
        setListOfTrainingObjects(objects);
    }

    public class CellViewHolder{
        public TextView name;
        public ImageView image;
        public RatingBar ratingBar;
    }

    public View getView(int position, View converterView, ViewGroup parent){
        View cellView = converterView;
        CellViewHolder holder;
        if(cellView == null){
            LayoutInflater layoutInflater = ((Activity)getContext()).getLayoutInflater();
            cellView = layoutInflater.inflate(getResourceId(), parent, false);
            holder = new CellViewHolder();
            holder.name = (TextView) cellView.findViewById(R.id.search_item_name);
            holder.image = (ImageView) cellView.findViewById(R.id.search_list_image);
            holder.ratingBar = (RatingBar) cellView.findViewById(R.id.search_item_rating);
            cellView.setTag(holder);
        }
        else{
            holder = (SearchListAdapter.CellViewHolder) cellView.getTag();
        }

        Training training = getListOfTrainingObjects().get(position);
        holder.name.setText(training.getName());
        holder.image.setImageDrawable(getContext().getResources().getDrawable(training.getImageResource()));
        holder.ratingBar.setRating(training.getRating());

        return cellView;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public List<Training> getListOfTrainingObjects() {
        return listOfTrainingObjects;
    }

    public void setListOfTrainingObjects(List<Training> listOfTrainingObjects) {
        this.listOfTrainingObjects = listOfTrainingObjects;
    }
}
