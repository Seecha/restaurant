package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.User;

public interface UserService {

	public void addUser(User user);

	public void updateUser(User user);

	/**
	 * Get User proxy
	 * @param username
	 * @return
	 */
	public User loadUser(String username);

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
	 * @param roles
	 * @return
	 */
	public List<User> getEmployeesCurrentlyWorking(int hour, Shift.Day day, List<Role.RoleType> roles);

	/**
	 * Get additional localized information about given list of employees
	 * @param employees
	 * @param language
	 * @return
	 */
	public List<User> getEmployeesLocalized(List<User> employees, String language);
	
	/**
	 * Get list of employees - according to user roles
	 * @return
	 */
	public List<User> getEmploeyees();
}
