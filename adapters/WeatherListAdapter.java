package com.demo.alopgudhate.weatherapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.alopgudhate.weatherapp.R;
import com.demo.alopgudhate.weatherapp.fragments.SingleCityPageFragment;
import com.demo.alopgudhate.weatherapp.valueobjects.WeatherData;

import java.util.ArrayList;


/**
 * Created by Alop Gudhate on 8/25/15.
 */
public class WeatherListAdapter extends BaseAdapter {
    private ArrayList<WeatherData> result;
    private Context context;
    private static LayoutInflater inflater = null;

    public WeatherListAdapter(Context context, ArrayList<WeatherData> WeatherInfo) {
        this.result = WeatherInfo;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public WeatherListAdapter(SingleCityPageFragment SingleCityPageFragment, ArrayList<WeatherData> WeatherInfo) {
        this.result = WeatherInfo;
        this.context = SingleCityPageFragment.getActivity();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return result == null ? 0 : result.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView descriptionIcon;
        TextView cityName;
        TextView date;
        TextView dayTemp;
        TextView minimumTemp;
        TextView maximumTemp;
        TextView pressure;
        TextView humidity;
        TextView cloud;
        TextView rain;
        TextView description;
        RelativeLayout relativeLayout;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_item_bautified, null);
        holder.descriptionIcon = (ImageView) rowView.findViewById(R.id.descriptionIcon);
        holder.cityName = (TextView) rowView.findViewById(R.id.txtCityName);
        holder.date = (TextView) rowView.findViewById(R.id.txtDateValue);
        holder.dayTemp = (TextView) rowView.findViewById(R.id.txtDayTempValue);
        holder.minimumTemp = (TextView) rowView.findViewById(R.id.txtMinTempValue);
        holder.maximumTemp = (TextView) rowView.findViewById(R.id.txtMaxTempValue);
        holder.pressure = (TextView) rowView.findViewById(R.id.txtPressureValue);
        holder.humidity = (TextView) rowView.findViewById(R.id.txtHumidityValue);
        holder.cloud = (TextView) rowView.findViewById(R.id.txtCloudsValue);
        holder.rain = (TextView) rowView.findViewById(R.id.txtRainValue);
        holder.description = (TextView) rowView.findViewById(R.id.txtDescriptionValue);


        holder.cityName.setText(result.get(position).getCity());
        holder.date.setText(result.get(position).getDate());
        holder.dayTemp.setText(String.valueOf((int) result.get(position).getDayTemp()) + " \u2103");
        holder.minimumTemp.setText(String.valueOf((int) result.get(position).getMinTemp()) + " \u2103");
        holder.maximumTemp.setText(String.valueOf((int) result.get(position).getMaxTemp()) + " \u2103");
        holder.pressure.setText(Float.toString(result.get(position).getPressure()) + "hPa");
        holder.humidity.setText(Float.toString(result.get(position).getHumidity()) + "%");
        holder.cloud.setText(Float.toString(result.get(position).getCloud()) + "%");
        holder.rain.setText(Float.toString(result.get(position).getRain()) + "%");
        holder.description.setText(result.get(position).getDescription());
        setWeatherDescriptiveImage(holder.descriptionIcon, result.get(position).getId());
        /*rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        return rowView;
    }

    private void setWeatherDescriptiveImage(ImageView descriptionIcon, int weatherConditionId) {
        if (weatherConditionId >= 200 && weatherConditionId <= 232) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_11d);
        } else if ((weatherConditionId >= 300 && weatherConditionId <= 321) || (weatherConditionId >= 520 && weatherConditionId <= 531)) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_09d);
        } else if (weatherConditionId >= 500 && weatherConditionId <= 504) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_10d);
        } else if (weatherConditionId >= 511 || (weatherConditionId >= 600 && weatherConditionId <= 622)) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_13d);
        } else if ((weatherConditionId >= 701 && weatherConditionId <= 781)) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_50d);
        } else if (weatherConditionId == 800) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_01d);
        } else if (weatherConditionId == 801) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_02d);
        } else if (weatherConditionId == 802) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_03d);
        } else if (weatherConditionId == 803 || weatherConditionId == 804) {
            descriptionIcon.setImageResource(R.mipmap.w_ic_04d);
        }
    }

}