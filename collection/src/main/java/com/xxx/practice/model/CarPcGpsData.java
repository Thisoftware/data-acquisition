package com.xxx.practice.model;

import java.util.Date;

/**
 * Created by Wyl 2017/6/28 0013.
 */
public class CarPcGpsData {

    /**
     * 车机序列号
     */
    private String serialNum;

    /**
     * gps维度
     */
    private double latitude;

    /**
     * gps经度
     */
    private double longitude;

    /**
     * gps海拔高度
     */
    private double altitude;

    /**
     * 地址名字
     */
    private String siteName;

    /**
     * 精度
     */
    private String accuracy;

    /**
     * 提供者
     */
    private String provider;

    /**
     * 行驶速度
     */
    private double speed;
    /**
     * 采集时间
     */
    private Date collectionTime;
    /**
     * 行驶类型，0-行程开始，1-行程中，2-行程结束
     */
    private int type;
    /**
     * 方位
     */
    private String bearing;

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    @Override
    public String toString() {
        return "CarPcGpsData{" +
                "serialNum='" + serialNum + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", altitude=" + altitude +
                ", siteName='" + siteName + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", provider='" + provider + '\'' +
                ", speed=" + speed +
                ", collectionTime=" + collectionTime +
                ", type=" + type +
                ", bearing='" + bearing + '\'' +
                '}';
    }
}
