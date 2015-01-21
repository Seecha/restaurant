package cz.uhk.restaurace.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import cz.uhk.restaurace.dao.EmployeeLocDao;
import cz.uhk.restaurace.dao.ShiftDao;
import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.UserService;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EmployeeLocDao employeeLocDao;

	@Autowired
	private ShiftDao shiftDao;

	@Override
	@Transactional
	public void addUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setPasswordVerif(user.getPassword());
		userDao.addUser(user);

	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	@Transactional(readOnly = true)
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(String username) {
		return userDao.getUserById(username);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		userDao.removeUser(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getUsersByRole(Role.RoleType role) {
		return userDao.getUsersByRole(role);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<User> getEmployeesCurrentlyWorking(int hour, Shift.Day day, List<Role.RoleType> roles) {
		List<User> employees = new ArrayList<User>();
		for(Role.RoleType role : roles) {
			employees = ListUtils.union(userDao.getEmployeesCurrentlyWorking(hour, day, role), employees);
		}
		for(User empl : employees){
			empl.setShifts(Arrays.asList(shiftDao.getCurrentEmployeesShift(hour, day, empl)));
		}
		return employees;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getEmployeesLocalized(List<User> employees, String language) {
		for(User user : employees){
			Employee employee = user.getEmployee();
			if(employee != null) {
				user.getEmployee().setEmployeeLoc(employeeLocDao.getEmployeeLocById(language, user.getUsername()));
			}else {
				log.info(UserServiceImpl.class.getSimpleName() + " Ke kuchari " + user.getFirstname()
						+ " " + user.getSurname() + " neexistuje lokalizace.");
			}
		}
		return employees;
	}

	@Override
	@Transactional(readOnly = true)
	public User loadUser(String username) {
		return userDao.loadUser(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getEmploeyees() {
		HashSet<User> hs = new HashSet<User>();
		hs.addAll(getUsersByRole(Role.RoleType.ROLE_CHEF));
		hs.addAll(getUsersByRole(Role.RoleType.ROLE_COOK));
		hs.addAll(getUsersByRole(Role.RoleType.ROLE_MANAGER));
		hs.addAll(getUsersByRole(Role.RoleType.ROLE_WAITER));
		List<User> list = new ArrayList<User>(hs);
		return list;
	}
}
