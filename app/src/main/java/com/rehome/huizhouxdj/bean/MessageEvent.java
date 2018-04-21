package com.rehome.huizhouxdj.bean;

/**
 * Created by ruihong on 2018/4/18.
 */

public class MessageEvent {

    private String message;
    private String messageid;


    public MessageEvent(String message, String messageid) {
        this.message = message;
        this.messageid = messageid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }
}
