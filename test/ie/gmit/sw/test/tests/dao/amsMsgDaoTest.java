package ie.gmit.sw.test.tests.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
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

import ie.gmit.sw.controller.UsersController;
import ie.gmit.sw.dao.AmsMessage;
import ie.gmit.sw.dao.AmsMsgDao;
import ie.gmit.sw.dao.User;
import ie.gmit.sw.dao.UserDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:ie/gmit/sw/config/dao-context.xml",
		"classpath:ie/gmit/sw/config/security-context.xml",
		"classpath:ie/gmit/sw/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class amsMsgDaoTest {

	@Autowired
	private AmsMsgDao msgdao;
	@Autowired
	private UserDAO userdao;
	@Autowired
	private DataSource datasource;
	
	private Calendar date = Calendar.getInstance();
	private Date dob;
	private static Logger logger = Logger.getLogger(amsMsgDaoTest.class);
	
	@Before
	public void init(){
		JdbcTemplate jdbc = new JdbcTemplate(datasource);

		jdbc.execute("delete from systemmsg");
		jdbc.execute("delete from userinfo");
		jdbc.execute("delete from users");
		
		
		// initialisation of some values
		date.set(Calendar.YEAR, 2010);
		date.set(Calendar.MONTH, Calendar.OCTOBER);
		date.set(Calendar.DATE, 3);
		
		dob = new Date(date.getTime().getTime());
	}
	
	@Test
	public void createMsgTest(){
		
		User user = new User("user", "user", true, "user@altech.ie", 100001, "User", "User", "1234567890", "hr", dob);
		logger.info("User ---> " + user.toString());
		
		if(userdao.createUser(user)){
			logger.info("User had being created successfuly.");
		}
		
		// Create message
		AmsMessage msg = new AmsMessage("message", user);
		assertTrue("Creation of message must return true", msgdao.createMsg(msg));
		
		List<AmsMessage> msgs = msgdao.getAllMsgs();
		assertEquals("Number of messages should be 1", 1, msgs.size());
	}
}
