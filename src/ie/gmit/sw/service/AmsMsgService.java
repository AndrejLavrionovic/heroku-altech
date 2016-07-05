package ie.gmit.sw.service;

import java.util.List;

import ie.gmit.sw.dao.AmsMessage;

public interface AmsMsgService {

	void createMsg(AmsMessage msg);

	List<AmsMessage> getCurrent();

	
}
