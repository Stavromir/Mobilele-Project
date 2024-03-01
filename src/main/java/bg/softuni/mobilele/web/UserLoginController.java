package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDto;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(Model model) {

        if (!model.containsAttribute("userLoginDto")) {
            model.addAttribute("userLoginDto", UserLoginDto.empty());
        }

        return "auth-login";
    }


    @PostMapping("/users/login")
    public String login(@Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginDto", userLoginDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDto",
                            bindingResult);

            return "redirect:login";
        }

        boolean loginSuccessful = userService.loginUser(userLoginDto);

        return loginSuccessful ? "index" : "auth-login";
    }
}
