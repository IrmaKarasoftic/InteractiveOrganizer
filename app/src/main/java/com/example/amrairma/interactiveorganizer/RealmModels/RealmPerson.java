package com.example.amrairma.interactiveorganizer.RealmModels;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Irma on 1/8/2017.
 */

public class RealmPerson extends  RealmObject{
    private String firstName;
    private String lastName;
    private String email;


    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
