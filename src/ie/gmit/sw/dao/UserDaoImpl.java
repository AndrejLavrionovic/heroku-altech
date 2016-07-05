package ie.gmit.sw.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDaoImpl")
public class UserDaoImpl implements UserDAO {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GET ALL USERS
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//	@Override
//	public List<User> getAllUsers() {
//		
//		String sql = "SELECT * FROM users;";
//		return jdbc.query(sql, new RowMapper<User>(){
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
//				User user = new User();
//				
//				user.setFirstname(rs.getString("users.firstname"));
//				user.setLastname(rs.getString("users.lastname"));
//				user.setEmpnum(rs.getInt("users.empnum"));
//				user.setTel(rs.getInt("users.tel"));
//				user.setEmail(rs.getString("users.email"));
//				user.setUsername(rs.getString("users.username"));
//				user.setPassword(rs.getString("users.password"));
//				user.setEnabled(rs.getBoolean("users.enabled"));
//				user.setAuthority(rs.getString("users.authority"));
//				
//				return user;
//			}
//		});
//	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GET ALL USERS VERSION 2
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public List<User> getAllUsers() {
		
		String sql = "SELECT * FROM users, userinfo where userinfo.username=users.username ORDER BY empnum ASC;";
		return jdbc.query(sql, BeanPropertyRowMapper.newInstance(User.class));
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GET USER BY EMPLOYEE NUMBER
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public User getUserByEmpnum(int empnum) {
		
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("empnum", empnum);
			
			String sql = " SELECT " +
					"u.username, u.enabled, u.empnum, u.authority, i.firstname, i.lastname, i.tel, i.email, i.dob " +
					"FROM users u INNER JOIN userinfo i ON i.username=u.username " +
					"WHERE u.empnum=:empnum " +
					"ORDER BY u.empnum ASC";
			return jdbc.queryForObject(sql, param, new UserRowMapper());
		}catch(DataAccessException ex){
			return null;
		}
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// GET USER BY USERNAME
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public User getUserByUsername(String username) {
		
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("username", username);
			
			String sql = " SELECT " +
					"u.username, u.enabled, u.empnum, u.authority, i.firstname, i.lastname, i.tel, i.email, i.dob " +
					"FROM users u INNER JOIN userinfo i ON i.username=u.username " +
					"WHERE u.username=:username " +
					"ORDER BY u.empnum ASC";
			return jdbc.queryForObject(sql, param, new UserRowMapper());
		}
		catch(DataAccessException ex){
			return null;
		}
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CREATE USER
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	@Transactional
	public boolean createUser(User user){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		String users = "insert into users (username, password, authority)" +
		" VALUES (:username, :password, :authority)";
		
		String userinfo = "insert into userinfo (username, email, firstname, lastname, tel, dob)" +
		" VALUES (:username, :email, :firstname, :lastname, :tel, :dob)";

		if(jdbc.update(users, params) == 1)
			return jdbc.update(userinfo, params) == 1;
		return false;
	}
	

	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// CREATE BUNCH OF USERS
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	@Transactional
	public int[] createUsers(List<User> users){

		String dbusers = "insert into users (username, password, authority)" +
				" VALUES (:username, :password, :authority)";
		
		String dbuserinfo = "insert into userinfo (username, firstname, lastname, email, tel, dob)" +
				" VALUES (:username, :firstname, :lastname, :email, :tel, :dob)";
		
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(users.toArray());
		
		jdbc.batchUpdate(dbusers, batchArgs);
		return jdbc.batchUpdate(dbuserinfo, batchArgs);
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// UPDATE USER
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public int updateUser(User user){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		String sql = "UPDATE users SET firstname=:firstname, lastname=:lastname," +
				" tel=:tel, email=:email WHERE username=:username";
				return jdbc.update(sql, params);
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// IS USER EXIST
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from users where username=:username;",
				new MapSqlParameterSource("username", username), Integer.class) > 0;
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// DELETE USER
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public boolean deleteUser(String username) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		
		String sql = "DELETE FROM users WHERE username=:username";
		return jdbc.update(sql, params) == 1;
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// SEARCH USER BY FIRSTNAME
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public List<User> getUsersByFirstname(String firstname) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("firstname", "%" + firstname + "%");
		
		String sql = "SELECT " +
				"u.username, u.enabled, u.empnum, u.authority, i.firstname, i.lastname, i.tel, i.email, i.dob " +
				"FROM users u INNER JOIN userinfo i ON i.username=u.username " +
				"WHERE i.firstname LIKE :firstname " +
				"ORDER BY empnum ASC;";
		return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(User.class));
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// SEARCH USER BY LASTNAME
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public List<User> getUsersByLastname(String lastname) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("lastname", "%" + lastname + "%");
		
		String sql = "SELECT " +
				"u.username, u.enabled, u.empnum, u.authority, i.firstname, i.lastname, i.tel, i.email, i.dob " +
				"FROM users u INNER JOIN userinfo i ON i.username=u.username " +
				"WHERE i.lastname LIKE :lastname " +
				"ORDER BY empnum ASC;";
		return jdbc.query(sql, params, BeanPropertyRowMapper.newInstance(User.class));
	}
	
	
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	// SEARCH USER BY EMAIL
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	@Override
	public User getUserByEmail(String email) {
		
		try{
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("email", email);
			
			String sql = " SELECT " +
					"u.username, u.enabled, u.empnum, u.authority, i.firstname, i.lastname, i.tel, i.email, i.dob " +
					"FROM users u INNER JOIN userinfo i ON i.username=u.username " +
					"WHERE i.email=:email " +
					"ORDER BY u.empnum ASC;";
			return jdbc.queryForObject(sql, param, new UserRowMapper());
		}
		catch(DataAccessException ex){
			return null;
		}
	}

	/*
	@Override
	public int populateUserinfo() {
		List<User> u;
		int count = 0;
		String sql = "SELECT * FROM users";
		u = jdbc.query(sql, BeanPropertyRowMapper.newInstance(User.class));
		
		for(User user : u){
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
			String insert = "insert into userinfo (firstname, lastname, tel, email, dob, username) " +
					"values (:firstname, :lastname, :tel, :email, DATE(NOW()), :username);";
			jdbc.update(insert, params);
			count++;
		}
		return count;
		
	}
	*/
}
