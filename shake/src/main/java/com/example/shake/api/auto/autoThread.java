package com.example.shake.api.auto;
import java.util.Calendar;
import java.util.Date;

public class autoThread extends Thread {
    private int mYear, mMonth, mDay, mHour, mMinute;


    public autoThread(int year, int month, int day, int hour, int minute) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mHour = hour;
        mMinute = minute;
    }
    @Override
    public void run() {
        try {
            sleep(timeUntil(mYear, mMonth, mDay, mHour, mMinute));
            // 작업을 수행한다.
            work();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void work() {
        System.out.println("i'm working!");
    }
    public long timeUntil(int year, int month, int day, int hour, int minute) {
        Date now = new Date();


        Calendar calUntil = Calendar.getInstance();

        calUntil.set(Calendar.YEAR, year);

        calUntil.set(Calendar.MONTH, month - 1);

        calUntil.set(Calendar.DAY_OF_WEEK, day);

        calUntil.set(Calendar.HOUR_OF_DAY, hour);

        calUntil.set(Calendar.MINUTE, minute);

        calUntil.set(Calendar.SECOND, 0);


        Date until = calUntil.getTime();


        long sleep = until.getTime() - now.getTime();


        return sleep;

    }

}
