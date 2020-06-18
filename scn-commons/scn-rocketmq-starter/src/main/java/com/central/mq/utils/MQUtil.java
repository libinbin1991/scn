package com.central.mq.utils;


import com.central.mq.bean.MQSend;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class MQUtil {

    public static Message getMessage(MQSend send) {
        byte[] msg = SerializeUtils.SerialObjInBean(send.getMsgBody());
        if (StringUtils.isNotEmpty(send.getTags()) &&
                StringUtils.isEmpty(send.getKey())) {
            return new Message(send.getTopic(),
                    send.getTags(),
                    msg);
        } else if (StringUtils.isNotEmpty(send.getTags()) &&
                StringUtils.isNotEmpty(send.getKey())) {
            return new Message(send.getTopic(),
                    send.getTags(),
                    send.getKey(),
                    msg);
        } else {
            return new Message(send.getTopic(),
                    msg);
        }
    }


    public static List<Message> getMessageList(MQSend send) {
        List<Object> bodyList = send.getMsgBodyList();
        List<Message> msgList = new ArrayList<>();
        for (Object body : bodyList) {
            Message message = null;
            byte[] msg = SerializeUtils.SerialObjInBean(body);
            if (StringUtils.isNotEmpty(send.getTags()) && StringUtils.isEmpty(send.getKey())) {
                message = new Message(
                        send.getTopic(),
                        send.getTags(),
                        msg);
            } else if (StringUtils.isNotEmpty(send.getTags()) && StringUtils.isNotEmpty(send.getKey())) {
                message = new Message(
                        send.getTopic(),
                        send.getTags(),
                        send.getKey(),
                        msg);
            } else {
                message = new Message(send.getTopic(),
                        msg);
            }

            msgList.add(message);
        }
        return msgList;
    }

}
