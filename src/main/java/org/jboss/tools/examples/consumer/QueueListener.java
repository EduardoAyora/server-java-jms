package org.jboss.tools.examples.consumer;

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
 
//@MessageDriven(activationConfig = {
//        @ActivationConfigProperty(
//                propertyName = "destinationType", 
//                propertyValue = "javax.jms.Queue"),
//        @ActivationConfigProperty(
//                propertyName = "destination", 
//                propertyValue = "queue/MyQueue") })
//public class QueueListener implements MessageListener {
// 
//    public void onMessage(Message message) {
//        try {
//            if (message instanceof TextMessage) {
//                System.out.println("Queue: I received a TextMessage at "
//                        + new Date());
//                TextMessage msg = (TextMessage) message;
//                System.out.println("Message is : " + msg.getText());
//            } else {
//                System.out.println("Not valid message for this Queue MDB");
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//}

/**
@MessageDriven(name = "MessageMDBSample", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "correo"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class QueueListener implements MessageListener {
    
    public void onMessage(Message message) {
                System.out.println("MDB!!!!: " + message);
        
    }
}**/
