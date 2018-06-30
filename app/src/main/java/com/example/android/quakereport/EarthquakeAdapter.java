package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

public class EarthquakeAdapter extends ArrayAdapter {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        Earthquake currentEarthquake = (Earthquake) getItem(position);


        //magnitude
        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        double mag = currentEarthquake.getMag();
        String formattedMag = formatMagnitude(mag);
        magTextView.setText(formattedMag);

        //circle at magnitude background
        GradientDrawable magnitudeCircle = (GradientDrawable)  magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());
        magnitudeCircle.setColor(magnitudeColor);


        //location
        String location = currentEarthquake.getLocation();
        String offset;
        String primary;
        if (location.contains("km") && location.contains(" of")) {
            offset = location.substring(0, location.indexOf(" of ")+4);
            primary = location.substring(location.indexOf(" of ")+4, location.length());
        }
        else {
            offset = "Near the ";
            primary = location;
        }

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetTextView.setText(offset);

        TextView primaryTextView = (TextView) listItemView.findViewById((R.id.primary_location));
        primaryTextView.setText(primary);

        //Create new date object from time in milliseconds
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(mag);
        switch (magnitudeFloor) {
            case 0:
            case  1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case  2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case  3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case  4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case  5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case  6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case  7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case  8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case  9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private  String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private  String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

}
