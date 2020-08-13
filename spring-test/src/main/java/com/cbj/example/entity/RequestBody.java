package com.cbj.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("body")
public class RequestBody {

    private String hospcode;

    private String visitdate;

    private String clinictype;

    public String getHospcode() {
        return hospcode;
    }

    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
    }

    public String getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(String visitdate) {
        this.visitdate = visitdate;
    }

    public String getClinictype() {
        return clinictype;
    }

    public void setClinictype(String clinictype) {
        this.clinictype = clinictype;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "hospcode='" + hospcode + '\'' +
                ", visitdate='" + visitdate + '\'' +
                ", clinictype='" + clinictype + '\'' +
                '}';
    }
}
