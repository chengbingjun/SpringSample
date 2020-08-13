package com.cbj.example.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("response")
public class Response {

    private ResponseBody body;

    private ResponseHead head;

    public ResponseBody getBody() {
        return body;
    }

    public void setBody(ResponseBody body) {
        this.body = body;
    }

    public ResponseHead getHead() {
        return head;
    }

    public void setHead(ResponseHead head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "Response{" +
                "body=" + body +
                ", head=" + head +
                '}';
    }
}
