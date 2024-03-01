package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegistrationDto")) {
            model.addAttribute("userRegistrationDto", UserRegistrationDto.empty());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String register (@Valid UserRegistrationDto userRegistrationDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes){

        //todo Registration email with activation link

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto");

            return "redirect:register";
        }

        userService.registerUser(userRegistrationDto);

        return "redirect:/home";
    }
}
