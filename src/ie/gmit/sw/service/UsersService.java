package ie.gmit.sw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.gmit.sw.dao.User;
import ie.gmit.sw.dao.UserDAO;
import ie.gmit.sw.dao.UserDaoImpl;

@Service("usersService")
public class UsersService {

	private UserDAO userDao;
	
	@Autowired
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public  List<User> getCurrent(){
		return userDao.getAllUsers();
	}

	// create user
	public void create(User user) {
		userDao.createUser(user);
	}
	
	public User getUserByUsername(String username){
		return userDao.getUserByUsername(username);
	}

	public boolean exists(String username) {
		userDao.exists(username);
		return false;
	}

	public int update(User user) {
		return userDao.updateUser(user);
	}

	public boolean deleteUser(String username) {
		return userDao.deleteUser(username);
	}

	public User getUserByEmpnum(Integer empnum) {
		return userDao.getUserByEmpnum(empnum);
	}

	public List<User> getUsersByFirstname(String firstname) {
		return userDao.getUsersByFirstname(firstname);
	}

	public List<User> getUsersByLastname(String lastname) {
		return userDao.getUsersByLastname(lastname);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}
}
