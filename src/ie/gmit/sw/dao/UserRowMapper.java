package ie.gmit.sw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		
		user.setFirstname(rs.getString("firstname"));
		user.setLastname(rs.getString("lastname"));
		user.setEmpnum(rs.getInt("empnum"));
		user.setUsername(rs.getString("username"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setTel(rs.getString("tel"));
		user.setEmail(rs.getString("email"));
		user.setAuthority(rs.getString("authority"));
		user.setDob(rs.getDate("dob"));
		
		return user;
	}
}
