package bg.softuni.mobilele.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserLoginController {


    @GetMapping("/users/login")
    public String login(Model model) {

        return "auth-login";
    }

}
