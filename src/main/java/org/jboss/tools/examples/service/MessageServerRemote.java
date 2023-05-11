package org.jboss.tools.examples.service;

import javax.ejb.Remote;

@Remote
public interface MessageServerRemote {
    public void sendMessage(String message) throws Exception;
}

