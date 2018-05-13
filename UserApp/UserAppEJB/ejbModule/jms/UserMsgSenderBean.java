package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueReceiver;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.ejb.Stateless;

@Stateless
public class UserMsgSenderBean implements UserMsgSender {

	@Override
	public void sendMsg(String msgContent) {
		// TODO Auto-generated method stub
		try {
			Context context = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			final Queue queue = (Queue) context.lookup("jms/queue/chatAppQueue");
			context.close();
			Connection connection = cf.createConnection();
			final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			connection.start();

			// MessageConsumer consumer = session.createConsumer(queue);
			// consumer.setMessageListener(this);

			TextMessage msg = session.createTextMessage(msgContent);
			// The sent timestamp acts as the message's ID
			long sent = System.currentTimeMillis();
			msg.setLongProperty("sent", sent);

			MessageProducer producer = session.createProducer(queue);
			producer.send(msg);
			// Thread.sleep(1000);
			producer.close();
			// consumer.close();
			connection.stop();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}