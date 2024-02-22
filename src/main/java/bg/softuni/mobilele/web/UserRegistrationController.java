package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register (UserRegistrationDto userRegistrationDto){

        //todo Registration email with activation link

        userService.registerUser(userRegistrationDto);

        return "redirect:/home";
    }
}
