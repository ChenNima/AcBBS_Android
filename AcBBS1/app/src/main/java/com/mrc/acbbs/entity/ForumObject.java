package com.mrc.acbbs.entity;

/**
 * Created by CYF on 16/1/19.
 */
public class ForumObject {
    /**
     * id : 787826
     * img :
     * ext :
     * now : 2013-05-06(一)11:03:58
     * userid : Wq2wRi8p
     * name : 无名氏
     * email :
     * title : 无标题
     * content : 询问版没人...<br/>买部平板看漫画和PDF实际不？不玩游戏，国产平板可靠么？多少寸合适？其实我主要是要续航能力
     * admin : 0
     * replyCount : 5
     * replys : [{"id":"788581","img":"2015-08-12/55cab9bb5f12a","ext":".jpg","now":"2013-05-06(一)14:39:33","userid":"FbYx5Cbe","name":"无名氏","email":"","title":"无标题","content":"<font color=\"#789922\">&gt;787826<\/font><br/>国产的续航都不行，，你肥皂掉了","admin":"0"}]
     */

    private String id;
    private String img;
    private String ext;
    private String now;
    private String userid;
    private String name;
    private String email;
    private String title;
    private String content;
    private boolean admin;
    private String replyCount;

    public void setId(String id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getExt() {
        return ext;
    }

    public String getNow() {
        return now;
    }

    public String getUserid() {
        return userid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean getAdmin() {
        return admin;
    }

    public String getReplyCount() {
        return replyCount;
    }
}
