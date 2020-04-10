package cn.lcy.domain;

import java.io.Serializable;

public class Percent implements Serializable {
    private double prep;
    private double operatep;
    private  double conclusionp;

    @Override
    public String toString() {
        return "percent{" +
                "prep=" + prep +
                ", operatep=" + operatep +
                ", conclusionp=" + conclusionp +
                '}';
    }

    public double getPrep() {
        return prep;
    }

    public void setPrep(double prep) {
        this.prep = prep;
    }

    public double getOperatep() {
        return operatep;
    }

    public void setOperatep(double operatep) {
        this.operatep = operatep;
    }

    public double getConclusionp() {
        return conclusionp;
    }

    public void setConclusionp(double conclusionp) {
        this.conclusionp = conclusionp;
    }
}
