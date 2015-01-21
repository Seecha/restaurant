package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Booking;
import cz.uhk.restaurace.model.CustomerOrder;
import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.AddressService;
import cz.uhk.restaurace.service.BookingService;
import cz.uhk.restaurace.service.CustomerOrderService;
import cz.uhk.restaurace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.*;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerOrderService orderService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/user/orders")
    public String getOrders(Model model, Authentication authentication){
    	User user = (User) authentication.getPrincipal();
    	List<CustomerOrder> orders = orderService.getUserOrdersByUsername(user.getUsername());
        System.out.println(orders.size());
        model.addAttribute("customerOrders", orders);
        return "user/orders";
    }
    
    @RequestMapping(value = "/user/bookings")
    public String getBookings(Model model, Authentication authentication){
    	User user = (User) authentication.getPrincipal();
        List<Booking> bookings = bookingService.getBookingsByUsername(user.getUsername());
        model.addAttribute("bookings", bookings);
        return "user/bookings";
    }


    /**
     * move to registration page
     * @param user
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(User user){
        return "registration";
    }

    /**
     * do register a new user and redirect him to a new page
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String doRegister(@Valid User user, BindingResult bindingResult){
        if (user == null || user.getUsername() == null){
            return "redirect:/";
        }
        if (bindingResult.hasErrors()){
            return "registration";
        }
        Set<Role> role = new HashSet<Role>();
        role.add(new Role(Role.RoleType.ROLE_USER));
        user.setRoles(role);
        userService.addUser(user);
        return "redirect:/regsuccessful";
    }

    @RequestMapping(value = "/user/userdetails")
    public String getUserDetails(Model model, Authentication authentication){
    	User user = (User) authentication.getPrincipal();
    	userService.getUserById(user.getUsername());
    	model.addAttribute("currentUser", user);
        return "user/userdetails";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        session.removeAttribute("user");
        return "redirect:/";
    }

}
