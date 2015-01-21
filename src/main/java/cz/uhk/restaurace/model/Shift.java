package cz.uhk.restaurace.model;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Shift {
	
	@Id
	@GeneratedValue
	private int id;
	private int sinceHour;
	private int toHour;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_shift",
	joinColumns=@JoinColumn(name="shifts_id"),
	inverseJoinColumns = @JoinColumn(name = "employees_username"))
	private Collection<User> employees;
	@Column(name = "work_day")
	@Enumerated(EnumType.STRING)
	private Day workDay;

	public enum Day{
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY;
	}
	
	public Shift() {}

	public Shift(int id, int sinceHour, int toHour) {
		super();
		this.id = id;
		this.sinceHour = sinceHour;
		this.toHour = toHour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSinceHour() {
		return sinceHour;
	}

	public void setSinceHour(int sinceHour) {
		this.sinceHour = sinceHour;
	}

	public int getToHour() {
		return toHour;
	}

	public void setToHour(int toHour) {
		this.toHour = toHour;
	}

	public Day getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Day workDay) {
		this.workDay = workDay;
	}

	public Collection<User> getEmployees() {
		return employees;
	}
	
	
}
