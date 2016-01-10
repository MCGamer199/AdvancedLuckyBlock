package com.LuckyBlock.logic;


/**
 * <b>Time</b>
 * <p>
 * This library was created by @MCGamer199 for Lucky Block
 * <p>
 * You can use it and modify it under the following conditions:
 * <ul>
 * <li>Don't claim this class as your own
 * <li>Don't remove this disclaimer
 * </ul>
 * <p>
 * <i>Time Class</i>
 *
 * @author MCGamer199
 * @since 1.8
 */
public class Time {


    private byte second;
    private byte min;
    private byte hour;
    private byte day;
    private byte month;
    private int year;
    private int id;


    public Time() {
        //
    }

    public Time(int id) {
        this.id = id;
    }


    public byte getHour() {
        return hour;
    }

    public void setHour(byte hour) {
        this.hour = hour;
        reloadTime();
    }

    public void setHour(int hour) {
        this.hour = (byte) hour;
        reloadTime();
    }

    public byte getSecond() {
        return second;
    }

    public void setSecond(byte second) {
        this.second = second;
        reloadTime();
    }

    public void setSecond(int second) {
        this.second = (byte) second;
        reloadTime();
    }

    public byte getMin() {
        return min;
    }

    public void setMin(byte min) {
        this.min = min;
        reloadTime();
    }

    public void setMin(int min) {
        this.min = (byte) min;
        reloadTime();
    }

    public byte getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = (byte) day;
        reloadTime();
    }

    public void setDay(byte day) {
        this.day = day;
        reloadTime();
    }

    public byte getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = (byte) month;
        reloadTime();
    }

    public void setMonth(byte month) {
        this.month = month;
        reloadTime();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
        reloadTime();
    }

    public long getTotal() {
        int total = 0;
        if (second < 1) {
            if ((min + 1) * (hour + 1) * (day + 1) * (month + 1) * (year + 1) > 1) {
                total = (min + 1) * (hour + 1) * (day + 1) * (month + 1) * (year + 1);
            } else {
                total = 0;
            }
        } else {
            total = second * (min + 1) * (hour + 1) * (day + 1) * (month + 1) * (year + 1);
        }
        return total;
    }

    public void reset() {
        second = 0;
        min = 0;
        hour = 0;
        day = 0;
        month = 0;
        year = 0;
    }

    public void addTime(byte amount) {
        second += amount;
        reloadTime();
    }

    public void minTime(int amount) {
        second -= amount;
        reloadTime();
    }

    public void addTime(int amount) {
        second += amount;
        reloadTime();
    }

    public void addTime(short amount) {
        second += amount;
        reloadTime();
    }

    private void reloadTime() {
        for (int x = 0; x < 1; x++) {
            if (second > 59) {
                second -= 60;
                min++;
                x = 0;
            }
            if (second < 0) {
                if (min > 0) {
                    second += 60;
                    min--;
                }
            }
        }
        for (int x = 0; x < 1; x++) {
            if (min > 59) {
                min -= 60;
                hour++;
                x = 0;
            }
            if (min < 0) {
                if (hour > 0) {
                    min += 60;
                    hour--;
                }
            }
        }
        for (int x = 0; x < 1; x++) {
            if (hour > 23) {
                hour -= 24;
                day++;
                x = 0;
            }
        }
        for (int x = 0; x < 1; x++) {
            if (day > 29) {
                day -= 30;
                month++;
                x = 0;
            }
        }
        for (int x = 0; x < 1; x++) {
            if (month > 11) {
                month -= 12;
                year++;
                x = 0;
            }
        }
    }


    public int getId() {
        return id;
    }


}
