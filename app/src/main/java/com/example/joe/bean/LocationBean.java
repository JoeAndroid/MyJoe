package com.example.joe.bean;

/**
 * 定位bean
 * Created by shenxiaolei on 16/12/26.
 */

public class LocationBean {

    private String locationCity;//定位城市

    private String addr;//定位详细地址

    private String latitude;//纬度

    private String longitude;//经度

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
