package com.central.mq.bean;


import com.central.mq.utils.MQEnums;

public class Result {

    private MQEnums.CODE code;
    private String msgId;
    private String desc;

    public Result(MQEnums.CODE code) {
        this.code = code;
    }

    public Result(MQEnums.CODE code, String msgId) {
        this.code = code;
        this.msgId = msgId;
    }

    public Result(MQEnums.CODE code, String msgId, String desc) {
        this.code = code;
        this.msgId = msgId;
        this.desc = desc;
    }

    public MQEnums.CODE getCode() {
        return code;
    }

    public void setCode(MQEnums.CODE code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }


}
