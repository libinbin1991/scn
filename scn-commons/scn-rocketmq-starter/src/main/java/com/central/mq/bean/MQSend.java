package com.central.mq.bean;


import com.central.mq.producer.MQSendCallback;
import com.central.mq.utils.MQEnums;

import java.util.List;

public class MQSend extends BaseBean {

    private MQSendCallback callback;

    private Object tranCallBackParam;
    private MQEnums.SEND_TYPE type;
    private int queueNum = 10;
    private int msgTimeOut = 30000;
    private String forceSequeueKey;
    private Object msgBody;
    private List<Object> msgBodyList;

    public MQSendCallback getCallback() {
        return callback;
    }

    /**
     * 设置ASYN下的回调
     *
     * @param callback
     */
    public void setCallback(MQSendCallback callback) {
        this.callback = callback;
    }


    public MQEnums.SEND_TYPE getType() {
        return type;
    }

    public void setType(MQEnums.SEND_TYPE type) {
        this.type = type;
    }

    public List<Object> getMsgBodyList() {
        return msgBodyList;
    }

    public void setMsgBodyList(List<Object> msgBodyList) {
        this.msgBodyList = msgBodyList;
    }

    public Object getMsgBody() {
        return msgBody;
    }

    /**
     * 设置消息体
     *
     * @param msgBody
     */
    public void setMsgBody(Object msgBody) {
        this.msgBody = msgBody;
    }

    public Object getTranCallBackParam() {
        return tranCallBackParam;
    }

    /**
     * 设置ASYN下的回调参数
     *
     * @param tranCallBackParam
     */
    public void setTranCallBackParam(Object tranCallBackParam) {
        this.tranCallBackParam = tranCallBackParam;
    }

    public String getForceSequeueKey() {
        return forceSequeueKey;
    }

    /**
     * 设置强制顺序消费的key
     *
     * @param forceSequeueKey
     */
    public void setForceSequeueKey(String forceSequeueKey) {
        this.forceSequeueKey = forceSequeueKey;
    }

    public int getMsgTimeOut() {
        return msgTimeOut;
    }

    /**
     * 消息超时时间(发送广播消息最好设置该值)
     *
     * @param msgTimeOut
     */
    public void setMsgTimeOut(int msgTimeOut) {
        this.msgTimeOut = msgTimeOut;
    }

    public int getQueueNum() {
        return queueNum;
    }

    /**
     * 生产队列大小(默认为10),不宜过大,视消息量与消费者数量衡量
     *
     * @param queueNum
     */
    public void setQueueNum(int queueNum) {
        this.queueNum = queueNum;
    }


}
