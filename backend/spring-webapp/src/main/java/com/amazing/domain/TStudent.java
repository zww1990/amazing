package com.amazing.domain;

import java.io.Serializable;

/**
 * @author zhangweiwei
 * @description 学生表
 * @date 2020-04-18 23:18:57
 */
@SuppressWarnings("serial")
public class TStudent implements Serializable {
    /** 编号 */
    private Integer id;

    /** 姓名 */
    private String name;

    /**
     * @return 编号
     * @date 2020-04-18 23:18:57
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 编号
     * @date 2020-04-18 23:18:57
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 姓名
     * @date 2020-04-18 23:18:57
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 姓名
     * @date 2020-04-18 23:18:57
     */
    public void setName(String name) {
        this.name = name;
    }
}