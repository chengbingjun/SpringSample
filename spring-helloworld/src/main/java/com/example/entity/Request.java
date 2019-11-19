package com.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("inparams")
public class Request {

    @XStreamAlias("head")
    private RequestHead head;

    private RequestBody body;

    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
