package com.central.mq.web;


import com.central.mq.MQFactory;
import com.central.mq.bean.MQReceive;
import com.central.mq.bean.Response;
import com.central.mq.consumer.MQReceiveCallback;
import com.central.mq.consumer.MQReceiveOrderly;
import com.central.mq.utils.MQEnums;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author libinbin
 * @version 1.0
 */
@RestController
public class Consumer {

    @RequestMapping("/pushSMS")
    public void pushSMS() {

        MQFactory factory = new MQFactory("top2", "192.168.192.120:9876");
        MQReceive recive = new MQReceive();
        recive.setTopic("TestTopic1");
        recive.setType(MQEnums.RECEIVE_TYPE.DEFAULT);
        recive.setCallback(new MQReceiveCallback() {
            @Override
            public void callback(List<Response> responses) {
                for (Response response : responses) {

                    System.out.println("push消息" + response.getMsgId() + "  " + response.getObj());
                }
            }

        });
        factory.receiveMsg(recive);

        System.out.println("Consumer Started.");
    }

    @RequestMapping("/pushSMSBatch")
    public void pushSMSBatch() {

        MQFactory factory = new MQFactory("top2", "192.168.192.120:9876");
        MQReceive recive = new MQReceive();
        recive.setTopic("dealEndorseTaskBatch");
        recive.setType(MQEnums.RECEIVE_TYPE.DEFAULT);


        recive.setCallback(new MQReceiveCallback() {
            @Override
            public void callback(List<Response> responses) {
                for (Response response : responses) {


                    System.out.println("push消息" + response.getMsgId() + "  " + response.getObj());
                }
            }

        });
        factory.receiveMsg(recive);

        System.out.println("Consumer Started.");
    }


    @RequestMapping("/pushSMSOdery")
    public void pushSMSOdery() {

        MQFactory factory = new MQFactory("top4", "192.168.192.120:9876");
        MQReceive recive = new MQReceive();
        recive.setTopic("dealEndorseTask");
        recive.setType(MQEnums.RECEIVE_TYPE.ORDERLY);
        recive.setOrderlyCallBack(new MQReceiveOrderly() {

            @Override
            public void callback(List<Response> responses) {
                for (Response response : responses) {


                    System.out.println("pushOrdery消息" + response.getMsgId());

                }
            }
        });
        factory.receiveMsg(recive);

        System.out.println("Consumer Started.");
    }


}