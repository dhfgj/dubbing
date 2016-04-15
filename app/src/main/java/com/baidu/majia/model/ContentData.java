package com.baidu.majia.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xuwt on 2016/4/14.
 */
public class ContentData extends BaseData{


    /**
     * comment_count : 0
     * date_time : 1460521310000
     * duriation : 00:03:35
     * played_count : 0
     * id : 32
     * title : null
     * thumbnail_url : http://123.56.195.4/media/videoImage/2016-04-13-12-21-19-58b42e05604d41d0a77ebed8aae41939.jpg
     * supported_count : 0
     * url : http://123.56.195.4/media/video/2016-04-13-12-21-19-58b42e05604d41d0a77ebed8aae41939.mp4
     */

    @SerializedName("comment_count")
    private int commentCount;
    @SerializedName("date_time")
    private long dateTime;
    @SerializedName("duriation")
    private String duriation;
    @SerializedName("played_count")
    private int playedCount;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnail_url")
    private String thumbnailUrl;
    @SerializedName("supported_count")
    private int supportedCount;
    @SerializedName("url")
    private String url;

    public int getCommentCount() {
        return commentCount;
    }

    public long getDateTime() {
        return dateTime;
    }

    public String getDuriation() {
        return duriation;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public int getSupportedCount() {
        return supportedCount;
    }

    public String getUrl() {
        return url;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public void setDuriation(String duriation) {
        this.duriation = duriation;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setSupportedCount(int supportedCount) {
        this.supportedCount = supportedCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
