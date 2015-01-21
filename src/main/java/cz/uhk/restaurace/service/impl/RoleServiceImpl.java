package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.RoleDao;
import cz.uhk.restaurace.model.Role;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public void addRole(Role role) {
		roleDao.addRole(role);

	}

	@Override
	@Transactional
	public void updateRole(Role role) {
		roleDao.updateRole(role);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> listRole() {
		return roleDao.listRole();
	}

	@Override
	@Transactional(readOnly = true)
	public Role getRoleById(int id) {
		return roleDao.getRoleById(id);
	}

	@Override
	@Transactional
	public void removeRole(int id) {
		roleDao.removeRole(id);

	}

}
