package smartchip.computers.controlleur;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import smartchip.computers.entities.User;
import smartchip.computers.service.SecurityService;
import smartchip.computers.service.UserService;
import smartchip.computers.validator.UserValidator;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm")@Valid User userForm, BindingResult bindingResult, String error) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
			userService.createUser(userForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("error", "Nom d'utilisateur ou Mot de passe invalides.");
            return "login";
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        return "redirect:/";
        }
        return "login";
    }


    
}