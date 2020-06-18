package com.central.mq;



import com.central.mq.bean.*;
import com.central.mq.service.ConsumerService;
import com.central.mq.service.ProducerService;
import com.central.mq.utils.MQEnums;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPullConsumerScheduleService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author libinbin
 * @version 1.0
 */
public class MQFactory {

    private String groupName;
    private String namesrvAddrs;
    private static ConcurrentHashMap<String, DefaultMQProducer> producerMap = new ConcurrentHashMap<String, DefaultMQProducer>();
    private static ConcurrentHashMap<String, DefaultMQPullConsumer> pullMap = new ConcurrentHashMap<String, DefaultMQPullConsumer>();
    private static ConcurrentHashMap<String, DefaultMQPushConsumer> pushMap = new ConcurrentHashMap<String, DefaultMQPushConsumer>();
    private static ConcurrentHashMap<String, MQPullConsumerScheduleService> scheduleMap = new ConcurrentHashMap<String, MQPullConsumerScheduleService>();

    public MQFactory(String groupName, String namesrvAddrs) {
        this.setGroupName(groupName);
        this.setNamesrvAddrs(namesrvAddrs);

    }
    private ProducerBean initProducer(MQSend send) throws MQClientException {
        if (null == send) {
            throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "MQSend is requeried");
        } else {
            if (StringUtils.isEmpty(send.getTopic())) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "Topic is requeried");
            } else if (null == send.getMsgBody()) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "MsgBody is requeried");
            } else if (send.getType() == MQEnums.SEND_TYPE.ASYNBATCH && null == send.getMsgBodyList()) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "MsgBodyList is requeried");
            } else if (send.getType() == MQEnums.SEND_TYPE.ASYN) {
                if (send.getCallback() == null) {
                    throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "Can't find 'Callback' in this Asyn Producer");
                }
            } else if (send.getType() == MQEnums.SEND_TYPE.FORCE_SEQUE) {
                if (StringUtils.isEmpty(send.getForceSequeueKey())) {
                    throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "Can't find 'ForceSequeueKey' in this ForceSeq Producer");
                }
            }
        }
        ProducerBean producerBean = new ProducerBean();
        producerBean.setSend(send);
        String gname = this.getGroupName() + "_producer";
        String key = producerKey(send);
        if (!producerMap.containsKey(key)) {
            synchronized (producerMap) {
                if (!producerMap.containsKey(key)) {
                    DefaultMQProducer tempProducer = new DefaultMQProducer();
                    tempProducer.setProducerGroup(gname);
                    tempProducer.setNamesrvAddr(namesrvAddrs);
                    if (StringUtils.isNotEmpty(send.getInstanceName())) {
                        tempProducer.setInstanceName(send.getInstanceName());
                    }
                    tempProducer.setDefaultTopicQueueNums(send.getQueueNum());
                    switch (send.getType()) {
                        case DEFAULT:
                            producerMap.put(key, tempProducer);
                            break;
                        case ASYN:
                            producerMap.put(key, tempProducer);
                            break;
                        case ASYNBATCH:
                            producerMap.put(key, tempProducer);
                            break;
                    }
                    producerMap.get(key).start();
                }
            }
            producerBean.setProducer(producerMap.get(key));
            return producerBean;
        } else {
            producerBean.setProducer(producerMap.get(key));
            return producerBean;
        }
    }


    private ConsumerBean initConsumer(MQReceive receive) throws MQClientException {
        if (null == receive) {
            throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "MQReceive is requeried");
        } else {
            if (StringUtils.isEmpty(receive.getTopic())) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "Topic is requeried");
            } else if (receive.getType() == MQEnums.RECEIVE_TYPE.DEFAULT && null == receive.getCallback()) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "CallBack is requeried");
            } else if (receive.getType() == MQEnums.RECEIVE_TYPE.ORDERLY && null == receive.getOrderlyCallBack()) {
                throw new MQClientException(MQCode.MQ_PARAMETER_ERR, "OrderlyCallBack is requeried in 'ORDERLY' receive");
            }
        }
        String gname = this.getGroupName() + "_consumer";
        String key = consumerKey(receive);
        ConsumerBean consumerBean = new ConsumerBean();
        consumerBean.setReceive(receive);
        boolean exists = receiveExists(receive);
        if (!exists) {
            synchronized (this) {
                if (!receiveExists(receive)) {
                    switch (receive.getType()) {
                        case SCHEDULE:
                            MQPullConsumerScheduleService scheduleConsumer = new MQPullConsumerScheduleService(gname);
                            scheduleMap.put(key, scheduleConsumer);
                            break;
                        case PULL:
                            DefaultMQPullConsumer pullConsumer = new DefaultMQPullConsumer(gname);
                            pullConsumer.setNamesrvAddr(namesrvAddrs);
                            pullMap.put(key, pullConsumer);
                        case BROADCAST:
                            DefaultMQPushConsumer brodConsumer = new DefaultMQPushConsumer(gname);
                            brodConsumer.setNamesrvAddr(namesrvAddrs);
                            brodConsumer.setMessageModel(MessageModel.BROADCASTING);
                            pushMap.put(key, brodConsumer);
                            break;
                        default:
                            DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(gname);
                            pushConsumer.setNamesrvAddr(namesrvAddrs);
                            pushMap.put(key, pushConsumer);
                            break;
                    }
                }
            }
        }
        switch (receive.getType()) {
            case SCHEDULE:
                consumerBean.setScheduleConsumer(scheduleMap.get(key));
                break;
            case PULL:
                consumerBean.setPullConsumer(pullMap.get(key));
                break;
            default:
                consumerBean.setPushConsumer(pushMap.get(key));
                break;
        }
        return consumerBean;
    }


    private boolean receiveExists(MQReceive receive) {
        boolean exists = false;
        switch (receive.getType()) {
            case SCHEDULE:
                exists = scheduleMap.containsKey(consumerKey(receive));
                break;
            case PULL:
                exists = pullMap.containsKey(consumerKey(receive));
                break;
            default:
                exists = pushMap.containsKey(consumerKey(receive));
                break;
        }
        return exists;
    }

    /**
     * 启动生产者开始发布消息
     *
     * @param send
     * @return
     */
    public Result sendMsg(MQSend send) {

        ProducerService service = new ProducerService();

        try {
            ProducerBean proBean = initProducer(send);
            switch (send.getType()) {
                case ASYN:
                    return service.sendAsyn(proBean);
                case ASYNBATCH:
                    return service.sendAsynBatch(proBean);
                case FORCE_SEQUE:
                    return service.sendOrderlyMessage(proBean);
                default:
                    service.defaultSend(proBean);
//				proBean.getProducer().shutdown();
//				producerMap.remove(send.getGroupName());
                    return new Result(MQEnums.CODE.SUCCESS);
            }

        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Result(MQEnums.CODE.FAIL);
        }
    }

    /**
     * 启动消费者开始订阅消息
     *
     * @param receive
     * @return
     */
    public Result receiveMsg(MQReceive receive) {
        ConsumerService service = new ConsumerService();
        try {
            ConsumerBean consumerBean = initConsumer(receive);
            switch (receive.getType()) {
                case SCHEDULE:
                    return new Result(MQEnums.CODE.FAIL, "暂不支持定时");
                case PULL:
                    return new Result(MQEnums.CODE.FAIL, "暂不支持拉取");
                case ORDERLY:
                    return service.orderlyReveive(consumerBean);
                default:
                    return service.defaultReveive(consumerBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(MQEnums.CODE.FAIL);
        }
    }

    /**
     * 停止生产
     *
     * @param send
     */
    public void producerShutdown(MQSend send) {
        if (producerMap.containsKey(send.getTopic())) {
            synchronized (producerMap) {
                if (producerMap.containsKey(send.getTopic())) {
                    producerMap.get(send.getTopic()).shutdown();
                    producerMap.remove(send.getTopic());
                }
            }
        }
    }


    private String producerKey(MQSend send) {
        StringBuffer sb = new StringBuffer();
        sb.append(send.getTopic());
        sb.append(send.getType().toString());
        if (StringUtils.isNotEmpty(send.getTags())) {
            for (String tag : send.getTags().split(",")) {
                sb.append(tag);
            }
        }
        return sb.toString();
    }

    private String consumerKey(MQReceive receive) {
        StringBuffer sb = new StringBuffer();
        sb.append(receive.getTopic());
        sb.append(receive.getType().toString());
        if (StringUtils.isNotEmpty(receive.getTags())) {
            for (String tag : receive.getTags().split(",")) {
                sb.append(tag);
            }
        }
        return sb.toString();
    }

//	public static void main(String[] args) {
//		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
//		map.put("test", "1");
//		new Thread() {
//			@Override
//			public void run() {
//				map.remove("test");
//				super.run();
//			}
//		}.start();
//
//		new Thread() {
//			@Override
//			public void run() {
//				map.remove("test");
//				super.run();
//			}
//		}.start();
//		System.out.println(map.get("test").length());
//	}

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNamesrvAddrs() {
        return namesrvAddrs;
    }

    public void setNamesrvAddrs(String namesrvAddrs) {
        this.namesrvAddrs = namesrvAddrs;
    }


}
