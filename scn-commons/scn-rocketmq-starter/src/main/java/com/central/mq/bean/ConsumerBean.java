package com.central.mq.bean;


import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPullConsumerScheduleService;

public class ConsumerBean {

    private DefaultMQPushConsumer pushConsumer;
    private DefaultMQPullConsumer pullConsumer;
    private MQPullConsumerScheduleService scheduleConsumer;
    private MQReceive receive;

    public MQPullConsumerScheduleService getScheduleConsumer() {
        return scheduleConsumer;
    }

    public void setScheduleConsumer(MQPullConsumerScheduleService scheduleConsumer) {
        this.scheduleConsumer = scheduleConsumer;
    }

    public MQReceive getReceive() {
        return receive;
    }

    public void setReceive(MQReceive receive) {
        this.receive = receive;
    }

    public DefaultMQPushConsumer getPushConsumer() {
        return pushConsumer;
    }

    public void setPushConsumer(DefaultMQPushConsumer pushConsumer) {
        this.pushConsumer = pushConsumer;
    }

    public DefaultMQPullConsumer getPullConsumer() {
        return pullConsumer;
    }

    public void setPullConsumer(DefaultMQPullConsumer pullConsumer) {
        this.pullConsumer = pullConsumer;
    }

}
