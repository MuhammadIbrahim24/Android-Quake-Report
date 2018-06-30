package com.example.android.quakereport;

import java.net.URL;
import java.util.Date;

public class Earthquake {
    private String location;
    private double mag;
    private long timeInMilliseconds;
    private String url;

    public Earthquake(String _location, double _mag, long _timeInMillisecoonds, String _url) {
        location = _location;
        mag = _mag;
        timeInMilliseconds = _timeInMillisecoonds;
        url = _url;
    }

    public String getLocation() {
        return location;
    }

    public double getMag() {
        return mag;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
