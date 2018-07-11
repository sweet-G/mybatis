package com.zt.entity;

/**
 * @author zhangtian
 * @date 2018/7/10
 */

public class Movie {
    private Integer id;
    private String title;
    private String directory;
    private String area;
    private String year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", directory='" + directory + '\'' +
                ", area='" + area + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
