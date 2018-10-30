package com.langwing.samocharge._fragment._dynamic.bean;

import java.io.Serializable;
import java.util.List;


/**
 * 单个评论
 */
public class CommentItem implements Serializable {

    private static final long serialVersionUID = 1561767573079749644L;
    private String id;
    private User user;
    private User toReplyUser;
    private String content;
    List<CommentItem> replyCommentItemList;

    public List<CommentItem> getReplyCommentItemList() {
        return replyCommentItemList;
    }

    public void setReplyCommentItemList(List<CommentItem> replyCommentItemList) {
        this.replyCommentItemList = replyCommentItemList;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getToReplyUser() {
        return toReplyUser;
    }

    public void setToReplyUser(User toReplyUser) {
        this.toReplyUser = toReplyUser;
    }

}
