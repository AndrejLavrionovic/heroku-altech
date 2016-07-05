package ie.gmit.sw.test.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ie.gmit.sw.dao.User;
import ie.gmit.sw.dao.UserDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:ie/gmit/sw/config/dao-context.xml",
		"classpath:ie/gmit/sw/config/security-context.xml",
		"classpath:ie/gmit/sw/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	private Date dob;
	private Calendar date = Calendar.getInstance();
	private static Logger logger = Logger.getLogger(UserDaoTests.class);
	User user = null;
	List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserDAO userdao;
	
	@Autowired
	private DataSource datasource;

	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(datasource);

		jdbc.execute("delete from systemmsg");
		jdbc.execute("delete from userinfo");
		jdbc.execute("delete from users");
		
		date.set(Calendar.YEAR, 2010);
		date.set(Calendar.MONTH, Calendar.OCTOBER);
		date.set(Calendar.DATE, 3);
		
		dob = new Date(date.getTime().getTime());
		
		user = new User("user", "user", true, "user@user.com", 100001, "Andrej", "Andrej", "0861234567", "admin", dob);
		
		users.add(user);
		users.add(new User("user1", "user1", true, "user1@user.com", null, "Andrej1", "Andrej1", "0861234568", "admin", dob));
		users.add(new User("user3", "user3", true, "user3@user.com", null, "Andrej3", "Andrej3", "0861234569", "admin", dob));
		users.add(new User("user4", "user4", true, "user4@user.com", 100004, "Andrej4", "Andrej4", "0861234560", "admin", dob));
		users.add(new User("user5", "user5", true, "user5@user.com", 100005, "Andrej1", "Andrej1", "0861234568", "admin", dob));
		users.add(new User("user6", "user6", true, "user6@user.com", null, "Alexej", "Alexejs", "0861234575", "admin", dob));
	}
	
	@Test
	public void createUserTest(){
		
		logger.info("::::::::::CREATE USER TEST::::::::::::");
		assertTrue("Creation user should return true", userdao.createUser(user));
	}
	
	@Test
	public void createBunchOfUsersTest(){
		logger.info("::::::::::CREATE BUNCH OF USERS TEST::::::::::::");
		
		int[] rowsNumbers = userdao.createUsers(users);
		
		assertTrue("The number of created rows must be greater then 0", (rowsNumbers.length > 0));
		assertEquals("Number of rows should be 6", 6, rowsNumbers.length);
	}
	
	
	@Test
	public void getAllUsersTest(){
		logger.info("::::::::::GET ALL USERS TEST::::::::::::");
		
		userdao.createUsers(users);
		List<User> createdUsers = userdao.getAllUsers();
		
		assertTrue("The number of users must be greater then 0", createdUsers.size() > 0);
		assertEquals("Number of users should be 6", 6, createdUsers.size());
		assertFalse("These two objects must not be equal", createdUsers.get(1).equals(createdUsers.get(3)));
	}
	
	@Test
	public void userExistTest(){
		logger.info("::::::::::IS USER EXIST TEST::::::::::::");
		
		userdao.createUser(user);
		assertTrue("User should exist", userdao.exists(user.getUsername()));
	}
	
	@Test
	public void usersEquality(){
		logger.info("::::::::::IS CREATED USER IDENTICAL TO INITIALISED USER TEST::::::::::::");
		
		userdao.createUser(user);
		User u = userdao.getAllUsers().get(0);
		u.setEmpnum(100001);
		logger.info("--->" + u);
		logger.info("--->"+ user);
		assertTrue("Created user should be adentical to retrieved user", user.equals(u));
	}
	
	@Test
	public void getUserByEmpnumTest(){
		logger.info("::::::::::GET USER BY EMPLOYEE NUMBER TEST::::::::::::");
		
		userdao.createUsers(users);
		User u = userdao.getUserByEmpnum(userdao.getAllUsers().get(3).getEmpnum());
		assertTrue("User should exist after he was assigned usiung empnum", userdao.exists(u.getUsername()));
	}
	
	@Test
	public void getUserByUsernameTest(){
		logger.info("::::::::::GET USER BY USERNAME TEST::::::::::::");
		
		userdao.createUsers(users);
		User u = userdao.getUserByUsername(userdao.getAllUsers().get(2).getUsername());
		assertTrue("User should exist after he was assigned usiung username", userdao.exists(u.getUsername()));
	}
	
	@Test
	public void getUserByFirstnameTest(){
		logger.info("::::::::::GET USER BY FIRSTNAME TEST::::::::::::");
		
		userdao.createUsers(users);
		
		logger.info("---> Get all users buy given pattern of firstname.");
		List<User> u = userdao.getUsersByFirstname("And");
		assertEquals("List should consist of 5 users", 5, u.size());
		
		logger.info("---> Make sure that users are complies.");
		u.get(4).setEmpnum(100005);
		u.get(4).setPassword("user5");
		assertTrue("Users must be eqals", u.get(4).equals(users.get(4)));
	}
	
	@Test
	public void getUserByLastnameTest(){
		logger.info("::::::::::GET USER BY LASTNAME TEST::::::::::::");
		
		userdao.createUsers(users);
		
		logger.info("---> Get all users buy given pattern of lastname.");
		List<User> u = userdao.getUsersByLastname("rej");
		assertEquals("List should consist of 5 users", 5, u.size());
		
		logger.info("---> Make sure that users are complies.");
		u.get(3).setEmpnum(100004);
		u.get(3).setPassword("user4");
		assertTrue("Users must be eqals", u.get(3).equals(users.get(3)));
	}
	
	@Test
	public void getUserByEmailTest(){
		logger.info("::::::::::GET USER BY EMAIL TEST::::::::::::");
		
		userdao.createUsers(users);
		User u = userdao.getUserByEmail(userdao.getAllUsers().get(5).getEmail());
		assertTrue("User should exist after he was assigned usiung username", userdao.exists(u.getUsername()));
	}
}
