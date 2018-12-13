package com.example.cherish.salehouse_kotlin.bean;

/**
 * 轮播图
 * Created by cherish
 */

public class BannerBean {

    /**
     * targetUrl : https://act.centanet.com/actfile/tj/app/20181130/index.html
     * imageUrl : https://act.centanet.com/www/images/f42c5b1df1ffd02bcfb65b751c6c1647.jpg
     * bannerId : 72
     * width : 640
     * height : 200
     * zoneId : 404
     * updatetime : 1543892998
     */

    private String targetUrl;
    private String imageUrl;
    private String bannerId;
    private String width;
    private String height;
    private String zoneId;
    private int updatetime;

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }
}
