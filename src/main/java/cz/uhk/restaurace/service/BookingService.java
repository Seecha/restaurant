package cz.uhk.restaurace.service;

import java.util.List;

import cz.uhk.restaurace.model.Booking;

public interface BookingService {
	public void addBooking(Booking booking);
	public void updateBooking(Booking booking);
	public List<Booking> listBooking();
	public List<Booking> listActualBooking();
	public List<Booking> listOldBooking();
	public Booking getBookingById(int id);
	public void removeBooking(int id);
	public List<Booking> getBookingsByUsername(String username);
}
