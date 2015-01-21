package cz.uhk.restaurace.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 42L;

	@Id
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	@ManyToMany(mappedBy = "roles", cascade={CascadeType.ALL})
	private Set<User> users;
	public enum RoleType{
		ROLE_ADMIN,
		ROLE_COOK,
		ROLE_WAITER,
		ROLE_CHEF,
		ROLE_MANAGER,
		ROLE_USER
	}

	public Role(){}

	public Role(RoleType roleType){
		this.roleType = roleType;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return roleType.name();
	}
}
