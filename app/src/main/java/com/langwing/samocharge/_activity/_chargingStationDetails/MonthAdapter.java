package com.langwing.samocharge._activity._chargingStationDetails;

import com.langwing.samocharge._view._wheel.WheelAdapter;

/**
 * To Change The World
 * 2018-04-13 21:01
 * Created by Mr.Wang
 */
public class MonthAdapter implements WheelAdapter<Integer> {

    private int[] months = {5, 6, 7, 8, 9, 10, 11, 12};

    @Override
    public int getItemsCount() {
        return months.length;
    }

    @Override
    public Integer getItem(int index) {
        return months[index];
    }

    @Override
    public String getItemString(int index) {
        return months[index] + "月";
    }

    @Override
    public int indexOf(Integer object) {
        return object - 1;
    }
}
