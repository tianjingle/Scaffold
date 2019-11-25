package com.inclination.scaffold.utils;


import lombok.Data;

/**
 * 公共数据返回类
 * @author tianjl
 */
@Data
public class ViewData {

    /**
     * 返回数据条数
     */
    private int count;
    /**
     * 匹配的总条数
     */
    private long total;

    /**
     * 响应业务状态
     */
    private Boolean success;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应中的数据
     */
    private Object content;

    public ViewData() {
    }

    public ViewData(Boolean success, String msg, Object content, int count, long total) {
        this.success = success;
        this.message = msg;
        this.count = count;
        this.total = total;
        this.content = content;
    }

    public static ViewData success(Object content, int count, long total) {
        return new ViewData(true, null, content, count, total);
    }

    public static ViewData success(Object content) {
        return new ViewData(true, null, content, 1, 1);
    }

    public static ViewData success(Object content, String msg) {
        return new ViewData(true, msg, content, 1, 1);
    }

    public static ViewData error(String msg) {
        return new ViewData(false, msg, null, 0, 0);
    }
    public static ViewData error(Object content){
        return new ViewData(false, "", content, 0, 0);
    }

}
