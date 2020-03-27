package com.example.wanandroid.bean;

import java.util.ArrayList;

public class TreeBean {
    private ArrayList<TreeChildrenBean> children;

    private String courseId;

    private String id;

    private String name;

    private String order;

    private String parentChapterId;

    private String userControlSetTop;

    private String visible;

    public void setChildren(ArrayList<TreeChildrenBean> children) {
        this.children = children;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setParentChapterId(String parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public void setUserControlSetTop(String userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public ArrayList<TreeChildrenBean> getChildren() {
        return children;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
    }

    public String getParentChapterId() {
        return parentChapterId;
    }

    public String getUserControlSetTop() {
        return userControlSetTop;
    }

    public String getVisible() {
        return visible;
    }
}
