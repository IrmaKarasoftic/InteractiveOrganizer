package com.example.amrairma.interactiveorganizer.RealmModels;

import io.realm.RealmObject;

/**
 * Created by Irma on 1/10/2017.
 */

public class RealmMailToPersons extends RealmObject {

    private String name;
    private String mail;

    public RealmMailToPersons() {};
    public String getName() {
        return name;
    }
    public String getMail() {
        return mail;
    }

    public void setName(String name) {this.name = name; }
    public void setMail(String mail) {this.name = mail; }
}
