package cz.uhk.restaurace.web;

import cz.uhk.restaurace.model.Role;
import cz.uhk.restaurace.model.Shift;
import cz.uhk.restaurace.model.User;
import cz.uhk.restaurace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dann on 14.12.2014.
 */

@Controller
public class ShiftController {

    @Autowired
    private UserService userService;

    private String language = "cs";

    private Role.RoleType cookRole = Role.RoleType.ROLE_COOK;
    private Role.RoleType chefRole = Role.RoleType.ROLE_CHEF;

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Display all cooks currently cooking and all cooks employed
     * @param model
     * @return
     */
    @RequestMapping(value = "/personal")
    public String whosInDaHouse(Model model){
        getCurrentWorkers(model);
        getAllPersonal(model);
        return "personal";
    }

    @RequestMapping(value = "currentlyWorking")
    public String getCurrentWorkers(Model model){

        //Process current time
        LocalDateTime currentTime = LocalDateTime.now();
        Integer hour = currentTime.getHour();
        String day = currentTime.getDayOfWeek().toString();

        //retrieve cooks based, on current time and role
        List<User> currentCooks = userService.getEmployeesCurrentlyWorking(
                hour, Shift.Day.valueOf(day), Arrays.asList(cookRole, chefRole));
        //get localized info about them
        List<User> currentCooksLoc = userService.getEmployeesLocalized(currentCooks, language);
        model.addAttribute("currentCooks", currentCooksLoc);
        return "personal";
    }

    @RequestMapping(value = "allPersonal")
    public String getAllPersonal(Model model){
        // get all cooks and chef
        //get localized info about them
        List<User> chefs = userService.getEmployeesLocalized(userService.getUsersByRole(chefRole), language);
        List<User> cooks = userService.getEmployeesLocalized(userService.getUsersByRole(cookRole), language);

        model.addAttribute("chefs", chefs);
        model.addAttribute("cooks", cooks);
        return "personal";
    }
}
