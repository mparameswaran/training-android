package com.madan.training;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Madan on 12/9/13.
 */
public class SearchListAdapter extends ArrayAdapter<Training>{
    public SearchListAdapter(Context context, int textViewResourceId, List<Training> objects) {
        super(context, textViewResourceId, objects);
    }
}
