package cz.uhk.restaurace.web;


import cz.uhk.restaurace.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

/**
 * Created by dann on 15.11.2014.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private ModelMap modelMap;

    @Mock
    private BindingResult bindingResult;

    private User user  = new User();

    @Test
    public void doRegisterTestUserNull(){
        String returnString = "redirect:/";
        Assert.assertTrue(returnString.equals(userController.doRegister(null, bindingResult)));
    }

    @Test
    public void doRegisterTestUsernameNull(){
        String returnString = "redirect:/";
        Assert.assertTrue(returnString.equals(userController.doRegister(user, bindingResult)));
    }
}
