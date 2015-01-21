package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.Role;

public interface RoleService {
	public void addRole(Role role);
	public void updateRole(Role role);
	public List<Role> listRole();
	public Role getRoleById(int id);
	public void removeRole(int id);

}
