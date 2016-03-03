package com.mrc.acbbs.entity;

import java.util.List;

/**
 * Created by CYF on 16/1/24.
 */
public class TitleFirst {

    /**
     * id : 4
     * sort : 1
     * name : 综合
     * status : n
     * forums : [{"id":"4","fgroup":"4","sort":"1","name":"综合版1","showName":"","msg":"msg","interval":"15","createdAt":"2011-10-21 15:49:28","updateAt":"2015-06-23 17:26:28","status":"n"},{"id":"20","fgroup":"4","sort":"2","name":"欢乐恶搞","showName":"","msg":"msg","interval":"15","createdAt":"2011-10-21 15:48:43","updateAt":"2014-11-05 00:27:52","status":"n"},{"id":"11","fgroup":"4","sort":"3","name":"推理","showName":"","msg":"\u2022微小说、图片推理、解谜。<br/>\u2022本版发文间隔为15秒。","interval":"15","createdAt":"2011-10-29 16:38:23","updateAt":"2014-08-02 04:54:09","status":"n"}]
     */

    private String id;
    private String sort;
    private String name;
    private String status;

    private List<TitleSecond> forums;

    public void setId(String id) {
        this.id = id;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setForums(List<TitleSecond> forums) {
        this.forums = forums;
    }

    public String getId() {
        return id;
    }

    public String getSort() {
        return sort;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<TitleSecond> getForums() {
        return forums;
    }


}
