package cz.uhk.restaurace.service.impl;

import java.util.List;

import cz.uhk.restaurace.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.BookingDao;
import cz.uhk.restaurace.model.Booking;
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Override
	@Transactional
	public void addBooking(Booking booking) {
		bookingDao.addBooking(booking);

	}

	@Override
	@Transactional
	public void updateBooking(Booking booking) {
		bookingDao.updateBooking(booking);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> listBooking() {
		return bookingDao.listBooking();
	}

	@Override
	@Transactional(readOnly = true)
	public Booking getBookingById(int id) {
		return bookingDao.getBookingById(id);
	}

	@Override
	@Transactional
	public void removeBooking(int id) {
		bookingDao.removeBooking(id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> getBookingsByUsername(String username) {
		return bookingDao.getBookingsByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> listActualBooking() {
		return bookingDao.listActualBooking();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Booking> listOldBooking() {
		return bookingDao.listOldBooking();
	}
}
