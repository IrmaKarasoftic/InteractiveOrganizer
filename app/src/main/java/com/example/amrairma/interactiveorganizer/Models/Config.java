package com.example.amrairma.interactiveorganizer.Models;

/**
 * Created by Amra on 2016-11-01.
 */

public class Config {

    // Ovaj ip je samo kad lokalno koristis, jer je virutualna masina ne moze localhost
    // bio je addPerson.php, ali ste ga u php-u nazvali Register, pa eto :D
    public static final String URL_OK="http://10.0.2.2:8080/Register.php";
    public static final String URL_ADD="http://10.0.2.2:8080/AddEvent.php";
    public static final String URL_GET_EVENTS="http://10.0.2.2:8080/GetEvents.php";
    public static final String URL_GET_PERSON="http://10.0.2.2:8080/GetPerson.php";


    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_EMAIL = "email";
    //public static final String KEY_DATE = "birth";
    public static final String KEY_ENAME = "title";
    public static final String KEY_ETYPE = "type";
    public static final String KEY_EDESC = "description";
    public static final String KEY_EDT = "dateAndTime";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DESCRIPTION = "description";
    public static final String TAG_NAME = "title";
    public static final String TAG_BROJAC = "brojac";

    public static final String TAG_TIME = "dateAndTime";
    //public static final String TAG_EMAIL = "email";



}
