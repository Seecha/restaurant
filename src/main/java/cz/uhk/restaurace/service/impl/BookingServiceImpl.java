package cz.uhk.restaurace.service.impl;

import java.sql.Date;
import java.util.List;

import cz.uhk.restaurace.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.restaurace.dao.BookingDao;
import cz.uhk.restaurace.model.Booking;
import cz.uhk.restaurace.model.DinnerTable;
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

	@Override
	@Transactional(readOnly = true)
	public boolean isBooked(DinnerTable table, Date date, int since, int to) {
		if(!bookingDao.getTableBookingOnTime(date, since, to, table).isEmpty()){
			return true;
		}
		return false;
	}
}
