package com.example.entity;

public class SelOrderRequest {

    private String funcode;

    private String safecontrol;

    private String orderno;

    private String key;

    private String sign;

    private String enkey;

    public String getFuncode() {
        return funcode;
    }

    public void setFuncode(String funcode) {
        this.funcode = funcode;
    }

    public String getSafecontrol() {
        return safecontrol;
    }

    public void setSafecontrol(String safecontrol) {
        this.safecontrol = safecontrol;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEnkey() {
        return enkey;
    }

    public void setEnkey(String enkey) {
        this.enkey = enkey;
    }
}
