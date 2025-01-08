package bean;

import java.io.Serializable;
import java.sql.Time;

public class Stores implements Serializable {
    private String storeCode;
    private String name;
    private Groups groups;
    private String phoneNum;
    /**
     * 緯度
     */
    private double latitude;
    /**
     * 経度
     */
    private double longitude;
    private Time openingTime;
    private Time closingTime;
    private int avg_amount_low;
    private int avg_amount_high;
    private String figure1;
    private String figure2;
    private String figure3;
    private boolean isActive;

    public String getStoreCode() {
        return storeCode;
    }
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Groups getGroups() {
        return groups;
    }
    public void setGroups(Groups groups) {
        this.groups = groups;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Time getOpeningTime() {
        return openingTime;
    }
    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }
    public Time getClosingTime() {
        return closingTime;
    }
    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }
    public int getAvg_amount_low() {
        return avg_amount_low;
    }
    public void setAvg_amount_low(int avg_amount_low) {
        this.avg_amount_low = avg_amount_low;
    }
    public int getAvg_amount_high() {
        return avg_amount_high;
    }
    public void setAvg_amount_high(int avg_amount_high) {
        this.avg_amount_high = avg_amount_high;
    }
    public String getFigure1() {
        return figure1;
    }
    public void setFigure1(String figure1) {
        this.figure1 = figure1;
    }
    public String getFigure2() {
        return figure2;
    }
    public void setFigure2(String figure2) {
        this.figure2 = figure2;
    }
    public String getFigure3() {
        return figure3;
    }
    public void setFigure3(String figure3) {
        this.figure3 = figure3;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
