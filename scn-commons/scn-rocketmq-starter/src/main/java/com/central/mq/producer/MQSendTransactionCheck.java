package com.central.mq.producer;/*
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionCheckListener;
import org.apache.rocketmq.common.message.MessageExt;

public abstract class MQSendTransactionCheck implements TransactionCheckListener {

	public abstract MQEnums.TRANSACTION transactionCheck();
	
	@Override
	public LocalTransactionState checkLocalTransactionState(MessageExt paramMessageExt) {
		MQEnums.TRANSACTION tran = transactionCheck();
		switch (tran) {
		case UNKOWN:
			return LocalTransactionState.UNKNOW;
		case COMMIT:
			return LocalTransactionState.COMMIT_MESSAGE;
		default:
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
	}

}
*/
