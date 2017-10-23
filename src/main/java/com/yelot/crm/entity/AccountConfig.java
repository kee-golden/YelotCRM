package com.yelot.crm.entity;

/**
 * 会员系统的配置参数,系统配置
 * Created by kee on 17/9/20.
 */
public class AccountConfig {
    private int id;
    private int yuan_per_point;
    private int first_level_points;
    private int second_level_points;
    private int third_level_points;
    /**
     * 百分比
     */
    private int first_level_discount;
    private int second_level_discount;
    private int third_level_discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYuan_per_point() {
        return yuan_per_point;
    }

    public void setYuan_per_point(int yuan_per_point) {
        this.yuan_per_point = yuan_per_point;
    }

    public int getFirst_level_points() {
        return first_level_points;
    }

    public void setFirst_level_points(int first_level_points) {
        this.first_level_points = first_level_points;
    }

    public int getSecond_level_points() {
        return second_level_points;
    }

    public void setSecond_level_points(int second_level_points) {
        this.second_level_points = second_level_points;
    }

    public int getThird_level_points() {
        return third_level_points;
    }

    public void setThird_level_points(int third_level_points) {
        this.third_level_points = third_level_points;
    }

    public int getFirst_level_discount() {
        return first_level_discount;
    }

    public void setFirst_level_discount(int first_level_discount) {
        this.first_level_discount = first_level_discount;
    }

    public int getSecond_level_discount() {
        return second_level_discount;
    }

    public void setSecond_level_discount(int second_level_discount) {
        this.second_level_discount = second_level_discount;
    }

    public int getThird_level_discount() {
        return third_level_discount;
    }

    public void setThird_level_discount(int third_level_discount) {
        this.third_level_discount = third_level_discount;
    }
}
