package cz.uhk.restaurace.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DinnerTable {

	@Id
	@GeneratedValue
	private int id;
	private int seatsNumber;
	@OneToMany(mappedBy="dinnerTable")
	private Collection<Booking> bookings;
	
	public DinnerTable() {}

	public DinnerTable(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(int seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public Collection<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}

	
	
}
