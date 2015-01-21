package cz.uhk.restaurace.dao;

import java.util.List;

import cz.uhk.restaurace.model.Role;

public interface RoleDao {
	public void addRole(Role role);
	public void updateRole(Role role);
	public List<Role> listRole();
	public Role getRoleById(int id);
	public void removeRole(int id);

}
