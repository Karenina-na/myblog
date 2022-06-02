package com.augenstern.domain;

/**
 * 前端页码类型交互格式
 */
public class TypeAndPageBean {
    String type;
    int page;

    @Override
    public String toString() {
        return "TypeAndPageBean{" +
                "type='" + type + '\'' +
                ", page=" + page +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
