package ie.gmit.sw.dao;

import java.util.List;

public interface AmsMsgDao {

	List<AmsMessage> getAllMsgs();

	boolean createMsg(AmsMessage msg);

	//public boolean createMsg(AmsMessage amsMessage);
}
