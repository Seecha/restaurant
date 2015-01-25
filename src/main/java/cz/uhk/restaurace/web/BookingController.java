package cz.uhk.restaurace.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.uhk.restaurace.model.Booking;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.BookingService;
import cz.uhk.restaurace.service.DinnerTableService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Handles requests for booking a table
 */
@Controller
public class BookingController {

	@Autowired
	private DinnerTableService dinnerTableService;

	@Autowired
	private BookingService bookingService;

	private static final Logger logger = LoggerFactory
			.getLogger(BookingController.class);

	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public String booking(Model model) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		model.addAttribute("date", dateFormat.format(date));
		model.addAttribute("tables", dinnerTableService.listTable());
		model.addAttribute("booking", new Booking());
		return "booking";
	}

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public String book(@Valid Booking booking, BindingResult bindingResult,
			@RequestParam String date, @RequestParam int tableId,
			Authentication authentication, Model model) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			booking.setDate(new java.sql.Date(dateFormat.parse(date).getTime()));
		} catch (ParseException e) {
			Date dateNow = new Date();
			model.addAttribute("date", dateFormat.format(dateNow));
			model.addAttribute("tables", dinnerTableService.listTable());
			model.addAttribute("wrongDateFormat", true);
			model.addAttribute("booking", booking);
			return "booking";
		}
		booking.setDinnerTable(dinnerTableService.getTableById(tableId));
		if(bookingService.isBooked(booking.getDinnerTable(), booking.getDate(),
				booking.getSinceHour(), booking.getToHour())){
			Date dateNow = new Date();
			model.addAttribute("date", dateFormat.format(dateNow));
			model.addAttribute("tables", dinnerTableService.listTable());
			model.addAttribute("booked", true);
			model.addAttribute("booking", booking);
			return "booking";
			
		}

		booking.setCustomer((User) authentication.getPrincipal());
		
		bookingService.addBooking(booking);
		return "booksuccessful";

	}

}
