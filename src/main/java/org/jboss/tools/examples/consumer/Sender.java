package org.jboss.tools.examples.consumer;

import javax.naming.InitialContext;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.QueueSender;
import javax.jms.DeliveryMode;
import javax.jms.QueueSession;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
                                                                           
public class Sender
{
    public static void main(String[] args) throws Exception
    {
       // get the initial context
    	final Hashtable<String, Comparable> jndiProperties =  
                new Hashtable<String, Comparable>();  
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                "org.jboss.naming.remote.client.InitialContextFactory");  
        //jndiProperties.put("jboss.naming.client.ejb.context", true);  
          
        //jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "eduardo");  
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "eduardo");
          
        System.out.println("contexto");
        final Context ctx = new InitialContext();
                            
        System.out.println("cola");
       // lookup the queue object
       Queue queue = (Queue) ctx.lookup("correo");
       //Queue queue = (Queue) ctx.lookup("queue/ExpiryQueue");
       
       System.out.println("factory");
       // lookup the queue connection factory
       QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
          lookup("jms/correo");
                
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
       TextMessage message = queueSession.createTextMessage("Hello");
                                                                          
       // send the message
       queueSender.send(message);
                                                                          
       // print what we did
       System.out.println("sent: " + message.getText());
                                                                          
       // close the queue connection
       queueConn.close();
    }
}
