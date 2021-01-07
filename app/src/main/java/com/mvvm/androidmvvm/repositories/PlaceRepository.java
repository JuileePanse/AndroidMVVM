package com.mvvm.androidmvvm.repositories;


import com.mvvm.androidmvvm.models.Place;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;


/**
 * Singleton pattern
 */
public class PlaceRepository {

    private static PlaceRepository instance;
    private ArrayList<Place> dataSet = new ArrayList<>();

    public static PlaceRepository getInstance(){
        if(instance == null){
            instance = new PlaceRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<Place>> getPlaces(){
        setNicePlaces();
        MutableLiveData<List<Place>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces(){
        dataSet.add(
                new Place("https://www.artchitectours.com/wp-content/uploads/2018/11/golden-gate-bridge-01.jpg",
                        "San Francisco")
        );
        dataSet.add(
                new Place("https://www.planetware.com/wpimages/2020/02/california-los-angeles-to-lake-tahoe-best-ways-to-get-there-by-car.jpg",
                        "Lake Tahoe")
        );
        dataSet.add(
                new Place("https://www.planetware.com/photos-large/USNY/new-york-city-statue-of-liberty.jpg",
                        "New York")
        );
        dataSet.add(
                new Place("https://www.visittheusa.com/sites/default/files/styles/hero_l_x2/public/images/hero_media_image/2016-10/HERO%201_LosAngeles_EDITORIAL_shutterstock_334078379_CROP_Web72DPI.jpg?itok=GaNapNPu",
                        "Los Angeles")
        );
        dataSet.add(
                new Place("https://g.foolcdn.com/image/?url=https%3A//g.foolcdn.com/editorial/images/600161/las-vegas-sign-at-dusk.jpg&w=2000&op=resize",
                        "Las Vegas")
        );
        dataSet.add(
                new Place("https://www.hawaiimagazine.com/sites/default/files/field/image/bestday-honolulu-opener.jpg",
                        "Honolulu")
        );
        dataSet.add(
                new Place("https://www.fodors.com/assets/destinations/4717/united-states-capitol-building-capitol-hill-washington-dc-usa-north-america_980x650.jpg",
                        "Washington D.C.")
        );
        dataSet.add(
                new Place("https://travel.usnews.com/dims4/USNEWS/1b25aee/2147483647/resize/445x280%5E%3E/crop/445x280/quality/85/?url=https://travel.usnews.com/images/chicago_skyline_cropped_445x280_gUaO3Pk.jpg",
                        "Chicago")
        );
        dataSet.add(
                new Place("https://media.timeout.com/images/105695117/1372/772/image.jpg",
                        "Miami")
        );
    }
}

