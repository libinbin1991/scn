package com.central.mq.web;


import com.central.mq.MQFactory;
import com.central.mq.bean.MQSend;
import com.central.mq.utils.MQEnums;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author libinbin
 * @version 1.0
 */
@RestController
public class Producer {


    @GetMapping("/sendSMS")
    public void sendSMS() {
        MQFactory factory = new MQFactory("top1", "192.168.192.120:9876");
        MQSend send = new MQSend();
        send.setTopic("TestTopic1");
        send.setMsgBody("This is a message~balalala");
        send.setType(MQEnums.SEND_TYPE.DEFAULT);
        factory.sendMsg(send);
    }
}