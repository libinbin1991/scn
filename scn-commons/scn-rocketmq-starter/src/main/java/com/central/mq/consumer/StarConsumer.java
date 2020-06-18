package com.central.mq.consumer;


import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.MQPullConsumerScheduleService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Set;

public class StarConsumer extends MQPullConsumerScheduleService implements MQConsumer {

    public StarConsumer(String consumerGroup) {
        super(consumerGroup);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createTopic(String paramString1, String paramString2, int paramInt) throws MQClientException {
        // TODO Auto-generated method stub

    }

    @Override
    public void createTopic(String paramString1, String paramString2, int paramInt1, int paramInt2)
            throws MQClientException {
        // TODO Auto-generated method stub

    }

    @Override
    public long searchOffset(MessageQueue paramMessageQueue, long paramLong) throws MQClientException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long maxOffset(MessageQueue paramMessageQueue) throws MQClientException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long minOffset(MessageQueue paramMessageQueue) throws MQClientException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long earliestMsgStoreTime(MessageQueue paramMessageQueue) throws MQClientException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MessageExt viewMessage(String paramString)
            throws RemotingException, MQBrokerException, InterruptedException, MQClientException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public QueryResult queryMessage(String paramString1, String paramString2, int paramInt, long paramLong1,
                                    long paramLong2) throws MQClientException, InterruptedException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MessageExt viewMessage(String paramString1, String paramString2)
            throws RemotingException, MQBrokerException, InterruptedException, MQClientException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendMessageBack(MessageExt paramMessageExt, int paramInt)
            throws RemotingException, MQBrokerException, InterruptedException, MQClientException {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessageBack(MessageExt paramMessageExt, int paramInt, String paramString)
            throws RemotingException, MQBrokerException, InterruptedException, MQClientException {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<MessageQueue> fetchSubscribeMessageQueues(String paramString) throws MQClientException {
        // TODO Auto-generated method stub
        return null;
    }

}
