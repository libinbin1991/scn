package com.central.mq.service;


import com.central.mq.bean.ConsumerBean;
import com.central.mq.bean.MQReceive;
import com.central.mq.bean.Result;
import com.central.mq.utils.MQEnums;
import com.central.mq.utils.MQException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;

/**
 * @author libinbin
 * @version 1.0
 */
public class ConsumerService {

    public Result defaultReveive(ConsumerBean consumerBean) {
        MQReceive receive = consumerBean.getReceive();
        try {
            ///consumerBean.getPushConsumer().setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumerBean.getPushConsumer().subscribe(receive.getTopic(),
                    receive.getTags());
            if (StringUtils.isNotEmpty(receive.getInstanceName())) {
                consumerBean.getPushConsumer().setInstanceName(receive.getInstanceName());
            }
            consumerBean.getPushConsumer().registerMessageListener(receive.getCallback());
            consumerBean.getPushConsumer().start();
            return new Result(MQEnums.CODE.SUCCESS);
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getErrorMessage());
        }
    }

    public Result orderlyReveive(ConsumerBean consumerBean) {
        MQReceive receive = consumerBean.getReceive();
        try {
            consumerBean.getPushConsumer().setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumerBean.getPushConsumer().subscribe(receive.getTopic(),
                    receive.getTags());
            if (StringUtils.isNotEmpty(receive.getInstanceName())) {
                consumerBean.getPushConsumer().setInstanceName(receive.getInstanceName());
            }
            consumerBean.getPushConsumer().registerMessageListener(receive.getOrderlyCallBack());
            consumerBean.getPushConsumer().start();
            return new Result(MQEnums.CODE.SUCCESS);
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new MQException(MQEnums.CODE.FAIL, e.getErrorMessage());
        }
    }

    public Result pullReceive(ConsumerBean consumerBean) {

        return null;
    }

    public Result scheduleReceive(ConsumerBean consumerBean) {

        return null;
    }

}
