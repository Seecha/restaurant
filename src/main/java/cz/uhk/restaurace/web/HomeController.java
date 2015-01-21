package cz.uhk.restaurace.web;

import java.security.Principal;


import cz.uhk.restaurace.service.CustomerOrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private CustomerOrderService customerOrderService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, Principal principal) {
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homee(HttpSession session, Principal principal) {
		return "home";
	}

		
}
