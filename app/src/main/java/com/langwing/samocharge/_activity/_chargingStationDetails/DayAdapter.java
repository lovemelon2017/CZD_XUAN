package com.langwing.samocharge._activity._chargingStationDetails;

import com.langwing.samocharge._view._wheel.WheelAdapter;

/**
 * To Change The World
 * 2018-04-13 21:01
 * Created by Mr.Wang
 */
public class DayAdapter implements WheelAdapter<Integer> {

    private int[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};

    @Override
    public int getItemsCount() {
        return months.length;
    }

    @Override
    public Integer getItem(int index) {
        return index + 1;
    }

    @Override
    public String getItemString(int index) {
        return index + 1 + "月";
    }

    @Override
    public int indexOf(Integer object) {
        return object - 1;
    }


}
