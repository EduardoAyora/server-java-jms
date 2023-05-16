package org.jboss.tools.examples.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.tools.examples.model.MessageData;

@Stateless
public class MessageServer implements MessageServerRemote {
	
    public void sendMessage(String message, String destinationUser) {
		try {
			String messageWU = destinationUser + message;
			Context ctx = new InitialContext();
			
	       Queue queue = (Queue) ctx.lookup("queue/correo");
	       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
	          lookup("java:/ConnectionFactory");
	                
	       QueueConnection queueConn = connFactory.createQueueConnection();
	       QueueSession queueSession = queueConn.createQueueSession(false,
	           Session.DUPS_OK_ACKNOWLEDGE);
	                       
	       QueueSender queueSender = queueSession.createSender(queue);
	       queueSender.setDeliveryMode(DeliveryMode.PERSISTENT);
	                                                     
	       TextMessage messageq = queueSession.createTextMessage(messageWU);
	                                      
	       queueSender.send(messageq);
	                                          
	       queueConn.close();
	       queueSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                            
        
    }

	@Override
	public String listMessages(String myUser) throws Exception {
		// TODO Auto-generated method stub
        
        	// The producer and consumer need to get a connection factory and use it to set up
            // a connection and a session
		Context ctx = new InitialContext();
		
	       Queue queue = (Queue) ctx.lookup("queue/correo");
	       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
	          lookup("java:/ConnectionFactory");
	                
            QueueConnection conn = connFactory.createQueueConnection();
            // This session is not transacted, and it uses automatic message acknowledgement
            QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queuer = (Queue) ctx.lookup("queue/correo");
            
            
            // Receiver
            QueueReceiver receiver = session.createReceiver(queuer);
            conn.start();
            Message m = receiver.receive();
            String response = "";
            if(m instanceof TextMessage) {
               TextMessage txt = (TextMessage) m;
               System.out.println("Message Received: "+txt.getText());
               response = txt.getText();
            }
            session.close();
            conn.close();
            return response;
        
		
	}
}
