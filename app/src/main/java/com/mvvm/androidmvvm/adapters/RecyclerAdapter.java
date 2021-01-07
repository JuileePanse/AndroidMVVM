package com.mvvm.androidmvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mvvm.androidmvvm.R;
import com.mvvm.androidmvvm.models.Place;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Place> places;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.image);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImage() {
            return imageView;
        }
    }

    public RecyclerAdapter(Context context, List<Place> places) {
        this.places = places;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        RequestOptions defaultOptions = new RequestOptions().error(R.drawable.ic_launcher_background);

        Glide.with(mContext).setDefaultRequestOptions(defaultOptions)
                .load(places.get(position).getImageUrl())
                .into(viewHolder.getImage());
        viewHolder.getTextView().setText(places.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}
