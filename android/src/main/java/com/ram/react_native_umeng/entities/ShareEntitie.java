package com.ram.react_native_umeng.entities;

/**
 * <p>com.ram.react_native_umeng.entities
 * <p>Created by GaoXuanJi on 2017/11/28
 * <p>Des          :
 * <p>Modify Author:
 * <p>Modify Date  :
 */
public class ShareEntitie {

    /**
     * type : text
     * text : 文字内容
     * imageUrl : 图片地址
     * title : 标题
     * description : 描述
     * webpageUrl : 链接地址
     */

    private String type;
    private String text;
    private String imageUrl;
    private String title;
    private String description;
    private String webpageUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }
}
