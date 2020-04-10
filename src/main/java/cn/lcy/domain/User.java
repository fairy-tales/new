package cn.lcy.domain;

import cn.lcy.utils.POI.ExcelTitle;

import java.io.Serializable;

public class User  implements Serializable {
    /*@ExcelTitle(title = "编号")*/
    private Integer id;
    @ExcelTitle(title = "姓名")
    private String name;
    @ExcelTitle(title = "学号")
    private String number;
    @ExcelTitle(title = "实验名称")
    private String course;
    @ExcelTitle(title = "预习")
    private double pre;
    @ExcelTitle(title = "操作")
    private double operate;
    @ExcelTitle(title = "结论")
    private double conclusion;
    @ExcelTitle(title = "核算结果")
    private double result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getPre() {
        return pre;
    }

    public void setPre(double pre) {
        this.pre = pre;
    }

    public double getOperate() {
        return operate;
    }

    public void setOperate(double operate) {
        this.operate = operate;
    }

    public double getConclusion() {
        return conclusion;
    }

    public void setConclusion(double conclusion) {
        this.conclusion = conclusion;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", course='" + course + '\'' +
                ", pre=" + pre +
                ", operate=" + operate +
                ", conclusion=" + conclusion +
                ", result=" + result +
                '}';
    }
}
