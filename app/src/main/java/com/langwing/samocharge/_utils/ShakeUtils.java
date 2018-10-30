package com.langwing.samocharge._utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * To Change The World
 * 2017-11-27 10:34
 * Created by Mr.Wang
 */

public class ShakeUtils implements SensorEventListener {

    public ShakeUtils(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        shakeListener = onShakeListener;
    }

    public void onResume() {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            //这里可以调节摇一摇的灵敏度
            if ((Math.abs(values[0]) > SENSOR_VALUE || Math.abs(values[1]) > SENSOR_VALUE || Math.abs(values[2]) > SENSOR_VALUE)) {
                DD.dd("sensor value == ", "X: " + values[0] + "  Y: " + values[1] + "  Z:" + values[2]);
                if (null != shakeListener) {
                    shakeListener.onShake();
                }
            }
        }
    }

    public interface OnShakeListener {
        void onShake();
    }

    private SensorManager sensorManager = null;
    private OnShakeListener shakeListener = null;
    private static final int SENSOR_VALUE = 23;
}
