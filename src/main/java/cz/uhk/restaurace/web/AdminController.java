package cz.uhk.restaurace.web;

import cz.uhk.restaurace.forms.NewDishForm;
import cz.uhk.restaurace.forms.NewIngredientForm;
import cz.uhk.restaurace.model.Booking;
import cz.uhk.restaurace.model.CustomerOrder;
import cz.uhk.restaurace.model.DishGeneral;
import cz.uhk.restaurace.model.DishLoc;
import cz.uhk.restaurace.model.IngredientGeneral;
import cz.uhk.restaurace.model.IngredientLoc;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.Shift.Day;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.BookingService;
import cz.uhk.restaurace.service.CommentService;
import cz.uhk.restaurace.service.CustomerOrderService;
import cz.uhk.restaurace.service.DishGeneralService;
import cz.uhk.restaurace.service.DishLocService;
import cz.uhk.restaurace.service.IngredientGeneralService;
import cz.uhk.restaurace.service.IngredientLocService;
import cz.uhk.restaurace.service.ShiftService;
import cz.uhk.restaurace.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by dann on 21.12.2014.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerOrderService customerOrderService;
    
    @Autowired
    private IngredientGeneralService ingredientGeneralService;
    
    @Autowired
    private IngredientLocService ingredientLocService;
    
    @Autowired
    private DishGeneralService dishGeneralService;
    
    @Autowired
    private DishLocService dishLocService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private ShiftService shiftService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentService;
    
    private String language = "cs";
    
    public void setLanguage(String language) {
		this.language = language;
	}

    @RequestMapping(value = "/adminHome")
    public String adminHome(){
        return "admin/adminHome";
    }

    @RequestMapping(value= "/newOrders")
    public String showNewOrders(Model model){
        List<CustomerOrder> regOrders = customerOrderService.getUnprocessedRegisteredCustomerOrders(language);
        model.addAttribute("newRegOrders", regOrders);
        List<CustomerOrder> notregOrders = customerOrderService.getUnprocessedNotregisteredCustomerOrders(language);
        model.addAttribute("newNotregOrders", notregOrders);
        return "admin/adminNewOrders";
    }

    @RequestMapping(value = "/showNewOrders", produces = "application/json")
    @ResponseBody
    public Integer getNewOrders(){
        List<CustomerOrder> regOrders = customerOrderService.getUnprocessedRegisteredCustomerOrders(language);
        List<CustomerOrder> notregOrders = customerOrderService.getUnprocessedNotregisteredCustomerOrders(language);
        return regOrders.size() + notregOrders.size();
    }
    
    @RequestMapping(value= "/processOrder", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public String processOrder(HttpSession session,  @RequestParam int id){
    	customerOrderService.processOrder(id);
        return "redirect:/admin/newOrders";
    }
    
  
    @RequestMapping(value= "/oldOrders")
    public String showOldOrders(Model model){
        List<CustomerOrder> regOrders = customerOrderService.getProcessedRegisteredCustomerOrders(language);
        model.addAttribute("regOrders", regOrders);
        List<CustomerOrder> notregOrders = customerOrderService.getProcessedNotregisteredCustomerOrders(language);
        model.addAttribute("notRegOrders", notregOrders);
        return "admin/adminOldOrders";
    }
    
    @RequestMapping(value= "/orderDetails",  method = RequestMethod.GET)
    public String showOrderDetails(@RequestParam int id, Model model){
    	CustomerOrder order = customerOrderService.getOrderById(id, language);
    	model.addAttribute("order", order);
        return "admin/adminOrderDetails";
    }
    
    @RequestMapping(value= "/deleteOrder",  method = RequestMethod.POST)
    public String deleteOrder(@RequestParam int id){
    	customerOrderService.removeOrder(id);
        return "redirect:/admin/oldOrders";
    }
    
    @RequestMapping(value= "/actualBookings")
    public String showActualBookings(Model model){
        List<Booking> actualBookings = bookingService.listActualBooking();
        model.addAttribute("bookings", actualBookings);
        return "admin/adminActualBookings";
    }
    
    @RequestMapping(value= "/oldBookings")
    public String showOldBookings(Model model){
        List<Booking> oldBookings = bookingService.listOldBooking();
        model.addAttribute("bookings", oldBookings);
        return "admin/adminOldBookings";
    }
    
    @RequestMapping(value= "/addIngredient", method = RequestMethod.GET)
    public String showAddIngredient(Model model){
    	model.addAttribute("newIngredientForm", new NewIngredientForm());
    	model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
    	model.addAttribute("lang", language);
        return "admin/addIngredient";
    }
    
    @RequestMapping(value= "/addIngredient", method = RequestMethod.POST)
    public String addIngredient(Model model,
    		@Valid NewIngredientForm newIngredientForm, BindingResult bindingResult){
    	if(bindingResult.hasErrors()){
    		model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
    		model.addAttribute("lang", language);
    		return "admin/addIngredient";
    	}
        IngredientGeneral ingredient = new IngredientGeneral(newIngredientForm.getKcal(), 
        		newIngredientForm.getFatGrams(), newIngredientForm.getSaccharideGrams(),
        		newIngredientForm.getProteinGrams(), newIngredientForm.getType(),
        		newIngredientForm.getPricePerHundredGrams());
        ingredientGeneralService.addIngredient(ingredient);
        IngredientLoc csIngredient = new IngredientLoc(ingredient.getId(), "cs", newIngredientForm.getCsName());
        IngredientLoc enIngredient = new IngredientLoc(ingredient.getId(), "en", newIngredientForm.getEnName());
        ingredientLocService.addIngredientLoc(csIngredient);
        ingredientLocService.addIngredientLoc(enIngredient);
    	return "redirect:/admin/addIngredient";
    }
    
    @RequestMapping(value= "/deleteIngredient", method = RequestMethod.POST)
    public String deleteIngredient(Model model, @RequestParam int id){
    	String ingredientType = ingredientGeneralService.getIngredientById(id).getType().toString().toLowerCase();       
    	ingredientLocService.removeIngredientsLoc(id);  	
    	ingredientGeneralService.removeIngredient(id);
    	return "redirect:/teppanyaki/"+ingredientType;
    }
    
    @RequestMapping(value= "/updateIngredient", method = RequestMethod.GET)
    public String showUpdateIngredient(Model model, @RequestParam int id){
    	IngredientGeneral ingredient = ingredientGeneralService.getIngredientById(id);
    	NewIngredientForm ingredientForm = new NewIngredientForm();
    	ingredientForm.setType(ingredient.getType());
    	ingredientForm.setKcal(ingredient.getKcalPerHundredGrams());
    	ingredientForm.setFatGrams(ingredient.getFatGrams());
    	ingredientForm.setPricePerHundredGrams(ingredient.getPricePerHundredGrams());
    	ingredientForm.setProteinGrams(ingredient.getProteinGrams());
    	ingredientForm.setSaccharideGrams(ingredient.getSaccharideGrams());
    	IngredientLoc ingredientCs = ingredientLocService.getIngredientLocById(id, "cs");
    	IngredientLoc ingredientEn = ingredientLocService.getIngredientLocById(id, "en");   	
    	if (ingredientCs != null) {
			ingredientForm.setCsName(ingredientCs.getName());
		}
    	if (ingredientCs != null) {
			ingredientForm.setEnName(ingredientEn.getName());
		}
    	model.addAttribute("newIngredientForm", ingredientForm);
    	model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
    	model.addAttribute("lang", language);
    	model.addAttribute("id", id);
        return "admin/updateIngredient";
    }
    
    @RequestMapping(value= "/updateIngredient", method = RequestMethod.POST)
    public String updateIngredient(Model model,
    		@Valid NewIngredientForm ingredientForm, BindingResult bindingResult, @RequestParam int id){
    	if(bindingResult.hasErrors()){
        	model.addAttribute("ingredientTypes", ingredientGeneralService.getIngredientTypes());
        	model.addAttribute("lang", language);
        	model.addAttribute("id", id);
            return "admin/updateIngredient";
    	}
    	IngredientGeneral ingredient = ingredientGeneralService.getIngredientById(id);
    	String ingredientType = ingredient.getType().toString().toLowerCase();
    	if (ingredient != null) {
    		ingredient.setKcal(ingredientForm.getKcal());
    		ingredient.setFatGrams(ingredientForm.getFatGrams());
    		ingredient.setSaccharideGrams(ingredientForm.getSaccharideGrams());
    		ingredient.setProteinGrams(ingredientForm.getProteinGrams());
    		ingredient.setType(ingredientForm.getType());
    		ingredient.setPricePerHundredGrams(ingredientForm.getPricePerHundredGrams());
    		ingredientGeneralService.updateIngredient(ingredient);
		}
    	IngredientLoc ingredientCs = ingredientLocService.getIngredientLocById(id, "cs");
    	if (ingredientCs != null) {
			ingredientCs.setName(ingredientForm.getCsName());
			ingredientLocService.updateIngredientLoc(ingredientCs);
		}
    	IngredientLoc ingredientEn = ingredientLocService.getIngredientLocById(id, "en");
    	if (ingredientEn != null) {
    		ingredientEn.setName(ingredientForm.getEnName());
			ingredientLocService.updateIngredientLoc(ingredientEn);
		}
    	 
    	return "redirect:/teppanyaki/"+ingredientType;
    }
    
    @RequestMapping(value= "/addDish", method = RequestMethod.GET)
    public String showAddDish(Model model){
    	model.addAttribute("newDishForm", new NewDishForm());   	
    	model.addAttribute("dishCategories", dishGeneralService.getDishCategories());
    	model.addAttribute("lang", language);
        return "admin/addDish";
    }
    
    @RequestMapping(value= "/addDish", method = RequestMethod.POST)
    public String addDish(Model model,
    		@Valid NewDishForm newDishForm, BindingResult bindingResult){
    	if(bindingResult.hasErrors()){
    		model.addAttribute("dishCategories", dishGeneralService.getDishCategories());
        	model.addAttribute("lang", language);
    		return "admin/addDish";
    	}
    	DishGeneral dish = new DishGeneral(newDishForm.getPrice(), newDishForm.getFatGrams(),
    			newDishForm.getKcal(), newDishForm.getSaccharideGrams(), newDishForm.getProteinGrams(),
    			newDishForm.getDishCategory());
    	dishGeneralService.addDish(dish);
    	DishLoc csDish = new DishLoc(dish.getId(), "cs", newDishForm.getCsName(), newDishForm.getCsDescription());
    	DishLoc enDish = new DishLoc(dish.getId(), "en", newDishForm.getEnName(), newDishForm.getEnDescription());
    	dishLocService.addDishLoc(csDish);
    	dishLocService.addDishLoc(enDish);
    	return "redirect:/admin/addDish";
    }
    
    @RequestMapping(value= "/deleteDish", method = RequestMethod.POST)
    public String deleteDish(Model model, @RequestParam int id){
        dishLocService.removeDishesLoc(id);
        dishGeneralService.removeDish(id);  	
        return "redirect:/menu/";
    }
    
    @RequestMapping(value= "/updateDish", method = RequestMethod.GET)
    public String showUpdateDish(Model model, @RequestParam int id){
    	DishGeneral dish = dishGeneralService.getDishById(id);
    	NewDishForm dishForm = new NewDishForm();
    	dishForm.setPrice(dish.getPrice());
    	dishForm.setKcal(dish.getKcal());
    	dishForm.setFatGrams(dish.getFatGrams());
    	dishForm.setProteinGrams(dish.getProteinGrams());
    	dishForm.setSaccharideGrams(dish.getSaccharideGrams());
    	dishForm.setDishCategory(dish.getDishCategory());
    	DishLoc dishCs = dishLocService.getDishLocById(id, "cs");
    	DishLoc dishEn = dishLocService.getDishLocById(id, "en");   	
    	if (dishCs != null) {
    		dishForm.setCsName(dishCs.getName());
    		dishForm.setCsDescription(dishCs.getDescription());
		}
    	if (dishCs != null) {
    		dishForm.setEnName(dishEn.getName());
    		dishForm.setEnDescription(dishEn.getDescription());
		}
    	model.addAttribute("newDishForm", dishForm);
		model.addAttribute("dishCategories", dishGeneralService.getDishCategories());
    	model.addAttribute("lang", language);
    	model.addAttribute("id", id);
        return "admin/updateDish";
    }
    
    @RequestMapping(value= "/updateDish", method = RequestMethod.POST)
    public String updateDish(Model model,
    		@Valid NewDishForm dishForm, BindingResult bindingResult, @RequestParam int id){
    	if(bindingResult.hasErrors()){
    		model.addAttribute("dishCategories", dishGeneralService.getDishCategories());
        	model.addAttribute("lang", language);
        	model.addAttribute("id", id);
            return "admin/updateDish";
    	}
    	DishGeneral dish =dishGeneralService.getDishById(id);
    	if (dish != null) {
    		dish.setKcal(dishForm.getKcal());
    		dish.setFatGrams(dishForm.getFatGrams());
    		dish.setSaccharideGrams(dishForm.getSaccharideGrams());
    		dish.setProteinGrams(dishForm.getProteinGrams());
    		dish.setDishCategory(dishForm.getDishCategory());
    		dish.setPrice(dishForm.getPrice());
    		dishGeneralService.updateDish(dish);
		}
    	DishLoc dishCs = dishLocService.getDishLocById(id, "cs");
    	if (dishCs != null) {
    		dishCs.setName(dishForm.getCsName());
    		dishCs.setDescription(dishForm.getCsDescription());
			dishLocService.updateDishLoc(dishCs);
		}
    	DishLoc dishEn = dishLocService.getDishLocById(id, "en");
    	if (dishEn != null) {
    		dishEn.setName(dishForm.getEnName());
    		dishEn.setDescription(dishForm.getEnDescription());
    		dishLocService.updateDishLoc(dishEn);
		}
    	 
    	return "redirect:/menu/";
    }
    
    @RequestMapping(value= "/addShift", method = RequestMethod.GET)
    public String showAddShift(Model model){
    	Day[] days = Shift.Day.values();
    	model.addAttribute("days", days);
        return "admin/addShift";
    }
    
    @RequestMapping(value= "/addShift", method = RequestMethod.POST)
    public String showAddShift(@RequestParam int since, @RequestParam int to, @RequestParam Day day){
    	Shift shift = new Shift();
    	shift.setWorkDay(day);
    	shift.setSinceHour(since);
    	shift.setToHour(to);
    	shiftService.addShift(shift);
        return "redirect:/adminHome";
    }
    
    @RequestMapping(value= "/shifts", method = RequestMethod.GET)
    public String showShifts(Model model){
    	List<Shift> shifts = shiftService.listShift();
        model.addAttribute("shifts", shifts);
    	return "admin/adminShifts";
    }
    
	@RequestMapping(value= "/shift", method = RequestMethod.GET)
    public String showShift(Model model, @RequestParam int id){
    	Day[] days = Shift.Day.values();
    	model.addAttribute("days", days);
    	Shift shift = shiftService.getShiftById(id);
        model.addAttribute("shift", shift);
        List<User> users =  userService.getEmploeyees();
        model.addAttribute("users", users);
    	return "admin/adminShift";
    }
    
    @RequestMapping(value= "/editShiftTime", method = RequestMethod.POST)
    public String editShiftTime(@RequestParam int since, @RequestParam int to,
    		@RequestParam Day day, @ModelAttribute("shift") Shift shift){
    	shift.setSinceHour(since);
    	shift.setToHour(to);
    	shift.setWorkDay(day);
    	shiftService.updateShift(shift);
    	return "redirect:/admin/shifts";
    }
    
    @RequestMapping(value= "/removeUserFromShift", method = RequestMethod.POST)
    public String removeUserFromShift(@RequestParam String userId, @RequestParam int shiftId){
    	User user = userService.getUserById(userId);
    	Shift shift = shiftService.getShiftById(shiftId);
    	shift.getEmployees().remove(user);
    	shiftService.updateShift(shift);
    	return "redirect:/admin/shifts";
    }
    
    @RequestMapping(value= "/addUserToShift", method = RequestMethod.POST)
    public String addUserToShift(@RequestParam int shiftId, @RequestParam String username){
    	Shift shift = shiftService.getShiftById(shiftId);
    	User user = userService.getUserById(username);
    	if(!shift.getEmployees().contains(user)){
    		shift.getEmployees().add(userService.getUserById(username));  	
    		shiftService.updateShift(shift);
    	}
    	return "redirect:/admin/shifts";
    }
    
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public String deleteComent(@RequestParam int id){
		commentService.removeComment(id);
		return "redirect:/menu";
	}


}

