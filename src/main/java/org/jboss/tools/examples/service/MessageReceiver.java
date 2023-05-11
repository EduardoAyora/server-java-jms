package org.jboss.tools.examples.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/testQueue"),
//    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
//})
//public class MessageReceiver implements MessageListener {
//    public void onMessage(Message message) {
//        System.out.println("Mensaje recibido (jms): " + message);
//    }
//}
