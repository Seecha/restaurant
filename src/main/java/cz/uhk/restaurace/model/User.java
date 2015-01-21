package cz.uhk.restaurace.model;

import cz.uhk.restaurace.service.validation.constraints.FieldMatch;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

@FieldMatch(first = "passwordVerif", second = "password")
@Entity
public class User{
	@Id
	@Size(min = 5, max = 20)
	private String username;
	@Size(min = 8)
	private String password;
	private transient String passwordVerif;
	@NotEmpty(message = "{NotEmpty.user.surname}")
	private String surname;
	@NotEmpty(message = "{NotEmpty.user.firstname}")
	private String firstname;
	@NotEmpty(message = "{NotEmpty.user.email}")
	@Email
	private String email;
	private String telephone;
	private boolean enabled;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	@OneToMany(mappedBy="customer")
	private Collection<CustomerOrder> customerOrders;
	@ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "employees")
	private Collection<Shift> shifts;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
				joinColumns=@JoinColumn(name="user"),
				inverseJoinColumns = @JoinColumn(name = "role"))
	private Set<Role> roles;
	@OneToMany(mappedBy="customer")
	private Collection<Booking> bookings;
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private Employee employee;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Comment> comments;

	public User() {
	}

	public User(String username, String password, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public User(String username, String password, Set<Role> roles,
				String surname, String firstname, String email) {
		this(username, password, roles);
		this.surname = surname;
		this.firstname = firstname;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<CustomerOrder> getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(Collection<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Collection<Shift> getShifts() {
		return shifts;
	}

	public void setShifts(Collection<Shift> shifts) {
		this.shifts = shifts;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Collection<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}

	public String getPasswordVerif() {
		return passwordVerif;
	}

	public void setPasswordVerif(String passwordVerif) {
		this.passwordVerif = passwordVerif;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (username != null ? !username.equals(user.username) : user.username != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (surname != null ? surname.hashCode() : 0);
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
		result = 31 * result + (enabled ? 1 : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

}
