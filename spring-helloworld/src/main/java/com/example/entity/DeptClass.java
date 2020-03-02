package com.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("deptclass")
public class DeptClass {

    private String visitdate;

    private String deptcode;

    private String deptname;

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "DeptClass{" +
                "visitdate='" + visitdate + '\'' +
                ", deptcode='" + deptcode + '\'' +
                ", deptname='" + deptname + '\'' +
                '}';
    }
}
