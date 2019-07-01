package com.example.demo;

/**
 * 图片类型
 *
 * @author songlekan songlekan@baidu.com
 * @date 2018/7/24.
 * Copyright  ©2016 百度
 */
public enum ImageType {

    /**
     * jpg图片类型
     */
    JPG("jpg"),

    /**
     * jpeg图片类型
     */
    JPEG("jpeg");

    private String name;

    /**
     * 构造方法
     *
     * @param name
     */
    ImageType(String name) {
        this.name = name;
    }

    /**
     * 获取图片类型名
     *
     * @return 返回图片类型字符串 例如：“jpg”
     */
    public String getName() {
        return name;
    }

}
