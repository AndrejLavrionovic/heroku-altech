package ie.gmit.sw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AmsMsgRowMapper implements RowMapper<AmsMessage>{

	@Override
	public AmsMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		user.setEmpnum(rs.getInt("empnum"));
		user.setTel(rs.getString("tel"));
		user.setAuthority(rs.getString("authority"));
		user.setDob(rs.getDate("dob"));
		
		AmsMessage msg = new AmsMessage();
		msg.setMsgid(rs.getInt("systemmsg.msgid"));
		msg.setDate(rs.getDate("systemmsg.date"));
		msg.setMsg(rs.getString("systemmsg.msg"));
		msg.setUser(user);
		
		return msg;
	}
}
