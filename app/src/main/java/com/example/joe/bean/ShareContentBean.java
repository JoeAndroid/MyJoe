package com.example.joe.bean;

/**
 * 分享文案 Created by qiaobing on 2017/1/19.
 */

public class ShareContentBean {


    /**
     * title : 金财圈 content : 中国银行理财2 img : http://182.92.128.84/wangcai/resources/imgs/share-logo.png
     * shareUrl : http://182.92.128.84/wangcai/product/25.html sinaShareUrl : http://t.cn/RMdihVW
     */

    private String title;
    private String content;
    private String img;
    private String shareUrl;
    private String sinaShareUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getSinaShareUrl() {
        return sinaShareUrl;
    }

    public void setSinaShareUrl(String sinaShareUrl) {
        this.sinaShareUrl = sinaShareUrl;
    }
}
