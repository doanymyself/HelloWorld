package demo.helloworld.components.event;

import java.io.Serializable;

/**
 * ********************************************************
 * <p/>
 * ********************************************************
 * Created by wangdong on 16/8/10.
 */
public class EventsListBean implements Serializable {

    /**
     * _id : 10850101
     * day : 1
     * des : 在931年前的今天，1085年1月1日 (农历腊月初三)，司马光主持编撰的《资治通鉴》成书。
     * lunar :
     * month : 1
     * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/200911/30/0F02714956.jpg
     * title : 司马光主持编撰的《资治通鉴》成书
     * year : 1085
     */

    private String _id;
    private int day;
    private String des;
    private String lunar;
    private int month;
    private String pic;
    private String title;
    private int year;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
