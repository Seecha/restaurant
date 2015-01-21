package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.*;
import cz.uhk.restaurace.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by dann on 11.11.2014.
 */

@Controller
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private DishGeneralService dishGeneralService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private TempCustomerInfoService tempCustomerInfoService;

    @Autowired
    private IngredientGeneralService ingredientGeneralService;

    private String language = "cs";

    private static String ANONYMOUS_USER ="anonymousUser";

    public void setLanguage(String language, HttpSession session) {
        this.language = language;
    }

    @RequestMapping(value="/cartOrdered")
    public String showCartCheckout(){
        return "cartOrdered";
    }

    /**
     * Gets from request a way of delivery and, if the user is not logged in, given street, city and phonenumber
     * Than sets to the cart customer name, if logged in, sets other important properties
     * If the user isn't logged in, persists given request parameters in db in TempCustomerInfo table
     * Removes cart from session at the end and redirects to cartOrdered.html
     * @param session
     * @param deliveryName
     * @param street
     * @param city
     * @param phonenumber
     * @return
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
    public String checkout(HttpSession session,
                           @RequestParam(value = "delivery", required = false) String deliveryName,
                           @RequestParam(required = false) String street,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String phonenumber){
        String attr = "cart";
        User user = null;
        CustomerOrder cart = (CustomerOrder)session.getAttribute(attr);
        if(!cart.getOrderedDishes().isEmpty() || !cart.getOrderedTeppanyakiDishes().isEmpty()) {

            cart = prepareCartToCheckout(cart, user, deliveryName);

            customerOrderService.addOrder(cart);
            if(street != null && city != null && phonenumber != null){
                tempCustomerInfoService.addTempCustomerInfo(new TempCustomerInfo(cart.getId(), street, city, phonenumber));
            }
        }
        session.removeAttribute(attr);
        return "redirect:/cartOrdered";
    }

    /**
     * Show second page of cart ordering, first is a cart.html, show deliveries and ordered dishes
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/orderCart")
    public String orderCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        Map<String, DishGeneral> teppanyakiDishes = dishGeneralService.getLocalizedTeppanyakiDishes(
                cart.getOrderedTeppanyakiDishes(), this.language);
        model.addAttribute("teppanyakiDishes", teppanyakiDishes);
        model.addAttribute("orderedDishes", dishes);
        model.addAttribute("deliveries", deliveryService.listDelivery());
        return "cartOrder";
    }

    /**
     * Empty a cart
     * @param session
     * @return
     */
    @RequestMapping(value = "/emptyCart")
    public String emptyCart(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        cart.getOrderedDishes().clear();
        cart.getOrderedTeppanyakiDishes().clear();
        return "cart";
    }

    /**
     * Add users custom dish to a cart, generate automatically name of this dish
     * @param session
     * @return
     */
    @RequestMapping(value = "/addTeppanyakiToCart", method = RequestMethod.GET)
    public String addTeppanyakiToCart(HttpSession session){
        DishGeneral dish = (DishGeneral) session.getAttribute("teppanyakiDish");
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        if (cart == null){
            cart = new CustomerOrder();
            session.setAttribute("cart", cart);
        }

        int numOfTeppanyakis = cart.getNumOfTeppanyakis();
        numOfTeppanyakis++;
        cart.setNumOfTeppanyakis(numOfTeppanyakis);
        String dishName = "Teppanyaki " + numOfTeppanyakis;
        dish.setName(dishName);
        dish.setPrice(dish.getCustomDishPrice());
        cart.getOrderedTeppanyakiDishes().put(dishName, dish);
        session.removeAttribute("teppanyakiDish");
        return "redirect:/showCart";
    }

    /**
     * Add regular dish to cart, not usable for custom dishes
     * @param session
     * @param id
     * @param amount
     * @return
     */
    @RequestMapping(value="/addToCart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody DishGeneral addToCart(HttpSession session, @RequestParam Integer id,
                                        @RequestParam String amount){
        CustomerOrder cart = createCartIfNull(session);
        DishGeneral dish = dishGeneralService.getDishById(id);
        dish.setAmount(Integer.parseInt(amount));
        //Prevent saving dishes with same id again in a map, instead increment amount of dishes already stored
        DishGeneral duplicity = cart.getOrderedDishes().get(dish.getId());
        if(duplicity == null) {
            cart.getOrderedDishes().put(dish.getId(), dish);
        }else{
            duplicity.setAmount(duplicity.getAmount() + dish.getAmount());
        }
        return dish;
    }

    /**
     * Show users cart with completely localized info about each item
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/showCart")
    public String showCart(HttpSession session, Model model){
        CustomerOrder cart = createCartIfNull(session);
        Map<Integer, DishGeneral> dishes = dishGeneralService.getLocalizedDishesInCart(cart.getOrderedDishes(), this.language);
        model.addAttribute("orderedDishes", dishes);
        Map<String, DishGeneral> teppanyakiDishes = cart.getOrderedTeppanyakiDishes();
        model.addAttribute("teppanyakiDishes",
                dishGeneralService.getLocalizedTeppanyakiDishes(teppanyakiDishes, this.language));
        return "cart";
    }


    /**
     * Remove item from cart using ajax
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeItem")
    @ResponseBody
    public DishGeneral removeItem(HttpSession session, @RequestParam("id") Integer id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        DishGeneral dish = cart.getOrderedDishes().get(id);
        cart.getOrderedDishes().remove(id);
        return dish;
    }

    /**
     * Remove custom item from cart using ajax
     * @param session
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeCustomItem")
    @ResponseBody
    public DishGeneral removeCustomItem(HttpSession session, @RequestParam("name") String id){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        DishGeneral dish = cart.getOrderedTeppanyakiDishes().get(id);
        cart.getOrderedTeppanyakiDishes().remove(id);
        return dish;
    }

    private CustomerOrder createCartIfNull(HttpSession session){
        CustomerOrder cart = (CustomerOrder)session.getAttribute("cart");
        if (cart == null) {
            cart = customerOrderService.createCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    public CustomerOrder prepareCartToCheckout(CustomerOrder cart, User user, String deliveryName){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!ANONYMOUS_USER.equals(auth.getPrincipal())) {
            user = (User) auth.getPrincipal();
            if(user != null) {
                cart.setCustomer(userService.loadUser(user.getUsername()));
            }
        }
        Date date = new Date();
        cart.setDate(new java.sql.Date(date.getTime()));
        Delivery delivery = deliveryService.getDeliveryById(deliveryName);
        cart.setDelivery(delivery);
        cart.setTotalPrice(cart.getTotalPrice().add(delivery.getPrice()));

        for(Map.Entry<Integer, DishGeneral> dishh : cart.getOrderedDishes().entrySet()){
            DishGeneral dish = dishh.getValue();
            OrderDish orderDish = new OrderDish(cart, dish, dish.getAmount());
            cart.getOrderedDishesToPersist().add(orderDish);
        }

        cart.setOrderedDishes(dishGeneralService.loadOrderedDishes(cart.getOrderedDishes()));

        for(Map.Entry<String, DishGeneral> dishh : cart.getOrderedTeppanyakiDishes().entrySet()){
            DishGeneral dish = dishh.getValue();
            int totalKcal = 0;
            int totalPrice = 0;
            int totalProt = 0;
            int totalSach = 0;
            int totalFat = 0;
            for(Map.Entry<Integer, IngredientGeneral> ingr : dish.getIngredients().entrySet()) {
                IngredientGeneral ingredient = ingr.getValue();
            	DishIngredient dishIngredient = new DishIngredient(dish, ingredient, ingredient.getGrams());
                dish.getDishIngredients().add(dishIngredient);
                totalKcal += ingredient.getKcalPerHundredGrams() * ingredient.getGrams();
                totalPrice += ingredient.getPricePerHundredGrams().intValue() * ingredient.getGrams();
                totalProt += ingredient.getProteinGrams() * ingredient.getGrams();
                totalSach += ingredient.getSaccharideGrams() * ingredient.getGrams();
                totalFat += ingredient.getFatGrams() * ingredient.getGrams();
            }
            dish.setKcal(totalKcal);
            dish.setPrice(new BigDecimal(totalPrice));
            dish.setProteinGrams(totalProt);
            dish.setSaccharideGrams(totalSach);
            dish.setFatGrams(totalFat);
            
            dishGeneralService.addDish(dish);
            OrderDish orderDish = new OrderDish(cart, dish, dish.getAmount());
            cart.getOrderedDishesToPersist().add(orderDish);
        }
        return cart;
    }

    
}
