package com.langwing.samocharge._activity._personalInfo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.langwing.samocharge.R;
import com.langwing.samocharge._utils.ToastUtil;
import com.langwing.samocharge._view._wheel.NumericWheelAdapter;
import com.langwing.samocharge._view._wheel.WheelView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By WYJ on 2017/12/7.
 */
public class SelectTimeDialog extends DialogFragment implements View.OnClickListener {

    private View view;
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private NumericWheelAdapter yearAdapter;
    private NumericWheelAdapter monthAdapter;
    private NumericWheelAdapter dayAdapter;
    private int currentYear;
    private int currentMonthIndex;
    private int currentDay;
    private final Calendar calendar;

    public SelectTimeDialog() {
        calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonthIndex = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.SelectAvatarDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.dialog_wheel_selector, container, false);
        }
        wvYear = view.findViewById(R.id.wheelView1);
        wvMonth = view.findViewById(R.id.wheelView2);
        wvDay = view.findViewById(R.id.wheelView3);

        yearAdapter = new NumericWheelAdapter(1900, 2018);
        monthAdapter = new NumericWheelAdapter(1, 12);
        wvYear.setAdapter(yearAdapter);
        for (int i = 0; i < yearAdapter.getItemsCount(); i++) {
            int year = (int) yearAdapter.getItem(i);
            if (currentYear == year) {
                wvYear.setCurrentItem(i);
            }
        }
        wvMonth.setAdapter(monthAdapter);
        wvMonth.setCurrentItem(currentMonthIndex);
        updateDays(currentYear, currentMonthIndex + 1);
        wvDay.setCurrentItem(currentDay - 1);

        wvYear.setOnItemSelectedListener(index -> updateDays((int) yearAdapter.getItem(index),
                (int) monthAdapter.getItem(wvMonth.getCurrentItem())));

        wvMonth.setOnItemSelectedListener(index -> updateDays((int) yearAdapter.getItem(wvYear.getCurrentItem()),
                (int) monthAdapter.getItem(index)));

        view.findViewById(R.id.tv_select).setOnClickListener(this);
        view.findViewById(R.id.tv_cancle).setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select:
                try {
                    int year = (int) yearAdapter.getItem(wvYear.getCurrentItem());
                    int month = (int) monthAdapter.getItem(wvMonth.getCurrentItem());
                    int day = (int) dayAdapter.getItem(wvDay.getCurrentItem());

                    String choiceDate = year + "-" + month + "-" + day;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    long choiceTime = format.parse(choiceDate).getTime();

                    Date date = new Date();
                    String currentDate = format.format(date);
                    long currentTime = format.parse(currentDate).getTime();

                    if (choiceTime > currentTime) {
                        ToastUtil.toast(getActivity(), "请选择正确的日期");
                    } else {
                        if (null != oneSelectDateListener) {
                            oneSelectDateListener.setSelectDate(choiceDate);
                            dismiss();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
            default:
                break;
        }
    }

    private void updateDays(int selectYear, int selectMonth) {
        calendar.set(Calendar.YEAR, selectYear);
        calendar.set(Calendar.MONTH, selectMonth - 1);
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayAdapter = new NumericWheelAdapter(1, maxDays);
        wvDay.setAdapter(dayAdapter);
        int currentItem = wvDay.getCurrentItem();
        int min = Math.min(maxDays, currentItem);
        wvDay.setCurrentItem(min);
    }

    public interface oneSelectDateListener {
        void setSelectDate(String date);
    }

    private oneSelectDateListener oneSelectDateListener;

    public void setOneSelectDateListener(oneSelectDateListener oneSelectDateListener) {
        this.oneSelectDateListener = oneSelectDateListener;
    }

}
