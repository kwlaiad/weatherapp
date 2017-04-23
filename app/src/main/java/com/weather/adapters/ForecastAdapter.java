package com.weather.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.R;
import com.weather.models.Forecast;
import com.weather.utils.MyUtils;

import java.util.List;

/**
 * Created by Ken Lai on 23/4/2017.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private List<Forecast> _forecasts;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maxTemp, minTemp, description, date;

        public ViewHolder(View itemView) {
            super(itemView);
            maxTemp = (TextView) itemView.findViewById(R.id.forecast_max_temp);
            minTemp = (TextView) itemView.findViewById(R.id.forecast_min_temp);
            description = (TextView) itemView.findViewById(R.id.forecast_description);
            date = (TextView) itemView.findViewById(R.id.forecast_date);
        }
    }

    public ForecastAdapter(List<Forecast> forecasts) {
        _forecasts = forecasts;
    }

    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.maxTemp.setText(String.format("Max: %.1f°C",
                MyUtils.getDegreesCelciusFromKelvin(_forecasts.get(position).getMainInformation().getMaxTemperature())));
        holder.minTemp.setText(String.format("Min: %.1f°C",
                MyUtils.getDegreesCelciusFromKelvin(_forecasts.get(position).getMainInformation().getMinTemperature())));
        holder.description.setText(_forecasts.get(position).getWeather().get(0).getDescription());
        holder.date.setText(_forecasts.get(position).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return _forecasts.size();
    }
}
