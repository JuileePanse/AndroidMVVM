package com.mvvm.androidmvvm.viewmodels;

import android.os.AsyncTask;


import com.mvvm.androidmvvm.models.Place;
import com.mvvm.androidmvvm.repositories.PlaceRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlacesViewModel extends ViewModel {

    private MutableLiveData<List<Place>> mPlaces;
    private PlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mPlaces != null){
            return;
        }
        mRepo = PlaceRepository.getInstance();
        mPlaces = mRepo.getPlaces();
    }

    public void addNewValue(final Place nicePlace){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Place> currentPlaces = mPlaces.getValue();
                currentPlaces.add(nicePlace);
                mPlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Place>> getNicePlaces(){
        return mPlaces;
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}