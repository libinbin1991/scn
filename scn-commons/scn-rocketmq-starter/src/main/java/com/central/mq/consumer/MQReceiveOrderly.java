package com.central.mq.consumer;


import com.central.mq.bean.Response;
import com.central.mq.utils.SerializeUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.ArrayList;
import java.util.List;

public abstract class MQReceiveOrderly implements MessageListenerOrderly {

    public abstract void callback(List<Response> responses);

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> paramList, ConsumeOrderlyContext arg1) {
        try {
            List<Response> respones = new ArrayList<Response>();
            for (MessageExt ext : paramList) {
                Response response = new Response();
                response.setMsgId(ext.getMsgId());
                response.setObj(SerializeUtils.unSerialObjInBean(ext.getBody(), Object.class));
                respones.add(response);
            }
            callback(respones);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

}
