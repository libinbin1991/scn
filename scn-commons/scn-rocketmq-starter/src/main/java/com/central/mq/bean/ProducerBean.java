package com.central.mq.bean;


import org.apache.rocketmq.client.producer.MQProducer;

public class ProducerBean {

    private MQProducer producer;
    private MQSend send;

    public MQProducer getProducer() {
        return producer;
    }

    public void setProducer(MQProducer producer) {
        this.producer = producer;
    }

    public MQSend getSend() {
        return send;
    }

    public void setSend(MQSend send) {
        this.send = send;
    }
}
