package com.example.amrairma.interactiveorganizer.RealmModels;

import io.realm.RealmObject;

/**
 * Created by Irma on 1/10/2017.
 */

public class RealmEventTypes extends RealmObject {

    private String description;
    private String color;

    public RealmEventTypes() {};
    public String getDescription() { return description;}
    public String getColor() {
        return color;
    }

    public void setDescription(String description) {this.description= description; }
    public void setColor(String color) {this.color= color; }
}