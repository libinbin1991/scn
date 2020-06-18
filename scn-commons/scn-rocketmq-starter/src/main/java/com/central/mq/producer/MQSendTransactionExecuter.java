package com.central.mq.producer;/*


import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

public abstract class MQSendTransactionExecuter implements LocalTransactionExecuter {

	public abstract void executorTran(Object callBackParam);

	@Override
	public LocalTransactionState executeLocalTransactionBranch(Message paramMessage, Object paramObject) {
		try {
			executorTran(paramObject);
			return LocalTransactionState.COMMIT_MESSAGE;
		} catch (Exception e) {
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
	}

}
*/
