package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.User;

public interface UserDao {

	public void addUser(User user);

	public void updateUser(User user);

	/**
	 * Returns all users
	 * @return
	 */
	public List<User> listUser();

	/**
	 * Get user by his unique username
	 * @param username
	 * @return
	 */
	public User getUserById(String username);

	public void removeUser(int id);

	/**
	 * Gets user based on his role
	 * @param role
	 * @return
	 */
	public List<User> getUsersByRole(Role.RoleType role);

	/**
	 * Returns list of Cooks currently cooking
	 * @param hour
	 * @param day
	 * @param role
	 * @return
	 */
	public List<User> getEmployeesCurrentlyWorking(int hour, Shift.Day day, Role.RoleType role);

	/**
	 * Get User proxy
	 * @param username
	 * @return
	 */
	public User loadUser(String username);
}
