package com.langwing.samocharge._fragment._dynamic.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 每一个朋友圈的 Item
 */
public class CircleItem implements Serializable {

    private static final long serialVersionUID = 5276519692001627332L;

    public final static String TYPE_URL = "1";
    public final static String TYPE_IMG = "2";

    private String id;
    private String content;
    private String createTime;
    private String type;//1:链接  2:图片 3:视频
    private String linkImg;
    private String linkTitle;
    private List<PhotoInfo> photos;  // 照片
    private List<FavortItem> favorters; // 点赞
    private List<CommentItem> comments; // 评论
    private User user;

    private boolean isExpand;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FavortItem> getFavorters() {
        return favorters;
    }

    public void setFavorters(List<FavortItem> favorters) {
        this.favorters = favorters;
    }

    public List<CommentItem> getComments() {
        return comments;
    }

    public void setComments(List<CommentItem> comments) {
        this.comments = comments;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public List<PhotoInfo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInfo> photos) {
        this.photos = photos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public boolean isExpand() {
        return this.isExpand;
    }

    public boolean hasFavort() {
        if (favorters != null && favorters.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean hasComment() {
        if (comments != null && comments.size() > 0) {
            return true;
        }
        return false;
    }

    public String getCurUserFavortId(String curUserId) {
        String favortid = "";
        if (!TextUtils.isEmpty(curUserId) && hasFavort()) {
            for (FavortItem item : favorters) {
                if (curUserId.equals(item.getUser().getId())) {
                    favortid = item.getId();
                    return favortid;
                }
            }
        }
        return favortid;
    }
}
