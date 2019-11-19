package com.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("body")
public class ResponseBody {

    private List<DeptClass>  deptclasslist;

    public List<DeptClass> getDeptclasslist() {
        return deptclasslist;
    }

    public void setDeptclasslist(List<DeptClass> deptclasslist) {
        this.deptclasslist = deptclasslist;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "deptclasslist=" + deptclasslist +
                '}';
    }
}
