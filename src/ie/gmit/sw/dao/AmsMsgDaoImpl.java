package ie.gmit.sw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("amsMsgDaoImpl")
public class AmsMsgDaoImpl implements AmsMsgDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource datasource){
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GET ALL MESSAGES
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public List<AmsMessage> getAllMsgs() {
		
		String sql = "select * from systemmsg, userinfo, users " +
				"where systemmsg.username = users.username and userinfo.username = users.username " +
				"order by systemmsg.date desc, systemmsg.msgid desc " +
				"limit 10;";
		return jdbc.query(sql, new AmsMsgRowMapper());
	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CREATE MESSAGE
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public boolean createMsg(AmsMessage msg){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(msg);
		
		String sql = "insert into systemmsg (date, msg, username) values (DATE(NOW()), :msg, :username);";
		
		return jdbc.update(sql, params) == 1;
	}
}
