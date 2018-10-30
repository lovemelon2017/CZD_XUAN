package com.langwing.samocharge._activity._chargingStationDetails;

import com.langwing.samocharge._view._wheel.WheelAdapter;

/**
 * To Change The World
 * 2018-04-13 21:01
 * Created by Mr.Wang
 */
public class YearAdapter implements WheelAdapter<String> {


    @Override
    public int getItemsCount() {
        return 1;
    }

    @Override
    public String getItem(int index) {
        return "2018";
    }

    @Override
    public String getItemString(int index) {
        return "2018";
    }

    @Override
    public int indexOf(String object) {
        return 0;
    }
}
