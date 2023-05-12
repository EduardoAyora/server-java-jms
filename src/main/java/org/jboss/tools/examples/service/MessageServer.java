package org.jboss.tools.examples.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class MessageServer implements MessageServerRemote {
    public void sendMessage(String message) {
        System.out.println("Mensaje recibido (implementacion): " + message);
        
		try {
			System.out.println("contexto");
			Context ctx = new InitialContext();
			
			System.out.println("cola");
		       // lookup the queue object
		       Queue queue = (Queue) ctx.lookup("queue/correo");
		       //Queue queue = (Queue) ctx.lookup("queue/ExpiryQueue");
		       
		       System.out.println("factory");
		       // lookup the queue connection factory
		       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
		          lookup("java:/ConnectionFactory");
		                
		       System.out.println("conexion");
		       // create a queue connection
		       QueueConnection queueConn = connFactory.createQueueConnection();
		                           
		       System.out.println("sesion");
		       // create a queue session
		       QueueSession queueSession = queueConn.createQueueSession(false,
		           Session.DUPS_OK_ACKNOWLEDGE);
		                       
		       System.out.println("sender");
		       // create a queue sender
		       QueueSender queueSender = queueSession.createSender(queue);
		       queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		                                                                          
		       // create a simple message to say "Hello"
		       TextMessage messageq = queueSession.createTextMessage(message);
		                                                                          
		       // send the message
		       queueSender.send(messageq);
		                                                                          
		       // print what we did
		       System.out.println("Sender: " + messageq.getText());
		                                                                          
		       // close the queue connection
		       queueConn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                            
        
    }
}
