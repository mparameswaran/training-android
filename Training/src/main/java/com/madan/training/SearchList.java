package com.madan.training;

import java.util.List;

/**
 * Created by Madan on 12/11/13.
 */
public class SearchList {

    private String type;
    private List<Training> trainingList;

    public SearchList(String type){

        setType(type);
        generateSearchListItems();

    }

    private void generateSearchListItems() {

        if(getType().equalsIgnoreCase("rails")||getType().equalsIgnoreCase("rails four")||getType().equalsIgnoreCase("ruby on rails")||getType().equalsIgnoreCase("rails 4")){
            //generate a list of rails training objects
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}
