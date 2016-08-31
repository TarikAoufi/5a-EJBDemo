package fr.aoufi.ejbsample.listener;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;


@MessageDriven(activationConfig= {
		@ActivationConfigProperty(propertyName="destinationType",
				propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination",
				propertyValue = "java:/jms/EcommerceQueue")
})
public class PersonneMessageListener  implements MessageListener{
	
	private Logger logger = Logger.getLogger(PersonneMessageListener.class);

	@Override
	public void onMessage(Message message) {
		ObjectMessage msg = (ObjectMessage) message;
		try{
			logger.info(msg.getObject());
		} catch (JMSException e) {
			e.printStackTrace();			
		}
	}

}
