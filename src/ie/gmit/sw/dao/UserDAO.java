package ie.gmit.sw.dao;

import java.util.List;

public interface UserDAO {

	public List<User> getAllUsers();
	public boolean createUser(User user);
	public int[] createUsers(List<User> users);
	public boolean exists(String username);
	public User getUserByEmpnum(int empnum);
	public User getUserByUsername(String username);
	public int updateUser(User user);
	public boolean deleteUser(String username);
	public List<User> getUsersByFirstname(String firstname);
	public List<User> getUsersByLastname(String lastname);
	public User getUserByEmail(String email);
}
