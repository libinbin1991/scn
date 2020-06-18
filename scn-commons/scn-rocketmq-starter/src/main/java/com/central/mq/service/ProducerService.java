package com.central.mq.service;

import com.central.mq.bean.ProducerBean;
import com.central.mq.bean.Result;
import com.central.mq.utils.MQEnums;
import com.central.mq.utils.MQException;
import com.central.mq.utils.MQUtil;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author libinbin
 * @version 1.0
 */
public class ProducerService {

    /**
     * 默认生产方法
     *
     * @param producerBean
     */
    public Result defaultSend(ProducerBean producerBean) {
        Message message = MQUtil.getMessage(producerBean.getSend());
        SendResult sendResult;
        try {
            sendResult = producerBean.getProducer().send(message);
            return getSendResult(sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getMessage());
        }
    }

    /**
     * 异步生产,没有MSGID返回
     *
     * @param producerBean
     * @return
     */
    public Result sendAsyn(ProducerBean producerBean) {
        Message message = MQUtil.getMessage(producerBean.getSend());
        try {
            producerBean.getProducer().send(message, producerBean.getSend().getCallback());
            return new Result(MQEnums.CODE.SUCCESS);
        } catch (MQClientException | RemotingException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getMessage());
        }
    }

    /**
     * 批量发送异步生产,没有MSGID返回
     *
     * @param producerBean
     * @return
     */
    public Result sendAsynBatch(ProducerBean producerBean) {
        List<Message> messageList = MQUtil.getMessageList(producerBean.getSend());
        try {
            producerBean.getProducer().send(messageList);
            return new Result(MQEnums.CODE.SUCCESS);
        } catch (MQClientException | RemotingException | InterruptedException | MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getMessage());
        }
    }


    /**
     * 发送强制顺序一致方法
     *
     * @param producerBean
     * @return
     */
    public Result sendOrderlyMessage(ProducerBean producerBean) {
        Message message = MQUtil.getMessage(producerBean.getSend());
        try {
            SendResult sendResult = producerBean.getProducer().send(message, new MessageQueueSelector() {

                @Override
                public MessageQueue select(List<MessageQueue> arg0, Message arg1, Object arg2) {
                    // TODO Auto-generated method stub
                    Long forceKey = strToNumber((String) arg2);
                    Long index = forceKey % arg0.size();
                    return arg0.get(Integer.valueOf(index.toString()));
                }
            }, producerBean.getSend().getForceSequeueKey());
            return getSendResult(sendResult);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getMessage());
        }
    }

    private static Result getSendResult(SendResult sendResult) {
        switch (sendResult.getSendStatus()) {
            case FLUSH_DISK_TIMEOUT:
                throw new MQException(MQEnums.CODE.FAIL, "FLISH_DISK_TIMEOUT", sendResult.getMsgId());
            case FLUSH_SLAVE_TIMEOUT:
                throw new MQException(MQEnums.CODE.FAIL, "FLUSH_SLAVE_TIMEOUT", sendResult.getMsgId());
            case SLAVE_NOT_AVAILABLE:
                throw new MQException(MQEnums.CODE.FAIL, "SLAVE_NOT_AVAILABLE", sendResult.getMsgId());
            default:
                return new Result(MQEnums.CODE.SUCCESS, sendResult.getMsgId());
        }
    }

    public static Long strToNumber(String str) {
        Long result = 0l;
        StringBuffer sb = new StringBuffer();
        String regex = "[^0-9]";
        Pattern parttern = Pattern.compile(regex);
        Matcher matcher = parttern.matcher(str);
        while (matcher.find()) {
            char[] chars = matcher.group(0).toCharArray();
            for (char c : chars) {
                result += (int) c;
            }
        }
        sb.append(result).append(str.replaceAll(regex, ""));
        return Long.valueOf(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(ProducerService.strToNumber("PD9291038282718"));
    }
}
