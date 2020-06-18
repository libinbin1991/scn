package com.central.mq.producer;


import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * 生产异步回调
 *
 * @author libinbin
 */
public abstract class MQSendCallback implements SendCallback {

    public abstract void hasSuccess(SendResult result);

    public abstract void hasError(Throwable error);


    @Override
    public void onSuccess(SendResult paramSendResult) {
        try {
            hasSuccess(paramSendResult);
        } catch (Exception e) {

        }

    }

    @Override
    public void onException(Throwable paramThrowable) {
        try {
            hasError(paramThrowable);
        } catch (Exception e) {

        }

    }

}
