package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/logout")
    public String logout() {

        userService.logoutUser();

        return "index";
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }


    @PostMapping("/users/login")
    public String login(UserLoginDto userLoginDto) {

        boolean loginSuccessful = userService.loginUser(userLoginDto);

        return loginSuccessful ? "index" : "auth-login";
    }
}
