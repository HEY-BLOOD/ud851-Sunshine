package com.example.android.sunshine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// COMPLETED (15) Add a class file called ForecastAdapter
// COMPLETED (16) Create a class within ForecastAdapter called ForecastAdapterViewHolder
// COMPLETED (17) Extend RecyclerView.ViewHolder
// COMPLETED (22) Extend RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>
// COMPLETED (23) Create a private string array called mWeatherData
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private String[] mWeatherData;

    // COMPLETED (47) Create the default constructor (we will pass in parameters in a later lesson)
    public ForecastAdapter() {
    }

    // COMPLETED (24) Override onCreateViewHolder
    // COMPLETED (25) Within onCreateViewHolder, inflate the list item xml into a view
    // COMPLETED (26) Within onCreateViewHolder, return a new ForecastAdapterViewHolder with the above view passed in as a parameter
    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.forecast_list_item, viewGroup, false);
        ForecastAdapterViewHolder viewHolder = new ForecastAdapterViewHolder(view);
        return viewHolder;
    }

    // COMPLETED (27) Override onBindViewHolder
    // COMPLETED (28) Set the text of the TextView to the weather for this list item's position
    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder viewHolder, int i) {
        viewHolder.mWeatherTextView.setText(mWeatherData[i]);
    }

    // COMPLETED (29) Override getItemCount
    // COMPLETED (30) Return 0 if mWeatherData is null, or the size of mWeatherData if it is not null
    @Override
    public int getItemCount() {
        if (null == mWeatherData) {
            return 0;
        }
        return mWeatherData.length;
    }

    // COMPLETED (31) Create a setWeatherData method that saves the weatherData to mWeatherData
    // COMPLETED (32) After you save mWeatherData, call notifyDataSetChanged
    public void setWeatherData(String[] weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }


    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        // COMPLETED (18) Create a public final TextView variable called mWeatherTextView
        public final TextView mWeatherTextView;

        // COMPLETED (19) Create a constructor for this class that accepts a View as a parameter
        // COMPLETED (20) Call super(view) within the constructor for ForecastAdapterViewHolder
        // COMPLETED (21) Using view.findViewById, get a reference to this layout's TextView and save it to mWeatherTextView
        public ForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
        }

    }

}
