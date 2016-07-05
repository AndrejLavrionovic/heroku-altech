package ie.gmit.sw.dao;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AmsMessage {

	private int msgid;
	private Date date;
	private String msg;
	private User user;
	
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CONSTRUCRTORS
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	public AmsMessage(){
		this.user = new User();
	}
	
	public AmsMessage(String msg, User user){
		this.msg = msg;
		this.user = user;
	}
	
	public AmsMessage(Date date, String msg, User user){
		this.date = date;
		this.msg = msg;
		this.user = user;
	}
	
	public AmsMessage(int msgid, Date date, String msg, User user) {
		this.msgid = msgid;
		this.date = date;
		this.msg = msg;
		this.user = user;
	}
	

	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GETTERS/SETTERS
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getUsername(){
		return user.getUsername();
	}
	

	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// HASHCODE/EQUALS
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AmsMessage other = (AmsMessage) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	

	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// TOSTRING
	//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

	@Override
	public String toString() {
		return "AmsMessage [msgid=" + msgid + ", date=" + date + ", msg=" + msg + ", user=" + user + "]";
	}
}
