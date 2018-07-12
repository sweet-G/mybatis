package com.zt.entity;

/**
 * @author zhangtian
 * @date 2018/7/12
 */

public class Productor {
    private Integer id;
    private String proName;
    private Integer proNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getProNum() {
        return proNum;
    }

    public void setProNum(Integer proNum) {
        this.proNum = proNum;
    }

    @Override
    public String toString() {
        return "Productor{" +
                "id=" + id +
                ", proName='" + proName + '\'' +
                ", proNum='" + proNum + '\'' +
                '}';
    }
}
