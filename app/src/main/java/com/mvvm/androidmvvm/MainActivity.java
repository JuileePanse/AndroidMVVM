package com.mvvm.androidmvvm;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mvvm.androidmvvm.adapters.RecyclerAdapter;
import com.mvvm.androidmvvm.models.Place;
import com.mvvm.androidmvvm.viewmodels.PlacesViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private ProgressBar mProgressBar;
    private PlacesViewModel mPlacesViewModel;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mFab = findViewById(R.id.fab);

        mPlacesViewModel = ViewModelProviders.of(this).get(PlacesViewModel.class);

        mPlacesViewModel.init();

        mPlacesViewModel.getNicePlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(@Nullable List<Place> nicePlaces) {
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });

        mPlacesViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    showProgress();
                }
                else{
                    hideProgress();
                    mRecyclerView.smoothScrollToPosition(mPlacesViewModel.getNicePlaces().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlacesViewModel.addNewValue(
                        new Place(
                                "https://sgl-assets.imgix.net/files/article_hero/yosemite-glacier-point-sunset-national-park-summer-activities-things-to-do-via-magazine-shutterstock_552174034.jpg?w=1440&h=720&crop=faces,edges",
                                "Yosemite"
                        )
                );
            }
        });
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerAdapter = new RecyclerAdapter(this, mPlacesViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void showProgress(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress(){
        mProgressBar.setVisibility(View.GONE);
    }

}