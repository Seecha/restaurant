package cz.uhk.restaurace.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	private int id;
	private int sinceHour;
	private int toHour;
	private Date date;
	@ManyToOne
	private User customer;
	@ManyToOne
	private DinnerTable dinnerTable;
	
	public Booking() {}

	public Booking(int sinceHour, int toHour, Date date, User customer,
				   DinnerTable dinnerTable) {
		this.sinceHour = sinceHour;
		this.toHour = toHour;
		this.date = date;
		this.customer = customer;
		this.dinnerTable = dinnerTable;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public DinnerTable getDinnerTable() {
		return dinnerTable;
	}

	public void setDinnerTable(DinnerTable dinnerTable) {
		this.dinnerTable = dinnerTable;
	}
	
	
}
