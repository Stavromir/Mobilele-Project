package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.ReCaptchaResponseDTO;
import bg.softuni.mobilele.model.dto.UserRegistrationDto;
import bg.softuni.mobilele.service.ReCaptchaService;
import bg.softuni.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;
    private final ReCaptchaService reCaptchaService;

    @Autowired
    public UserRegistrationController(UserService userService, ReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.reCaptchaService = reCaptchaService;
    }


    @GetMapping("/register")
    public String register(Model model) {

        if (!model.containsAttribute("userRegistrationDto")){
            model.addAttribute("userRegistrationDto", UserRegistrationDto.empty());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String registerConfirm (@Valid UserRegistrationDto userRegistrationDto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   @RequestParam("g-recaptcha-response") String reCaptchaResponse){

        //todo Registration email with activation link

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationDto", userRegistrationDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto",
                            bindingResult);

            return "redirect:/users/register";
        }


        boolean isBot = !reCaptchaService
                .verify(reCaptchaResponse)
                        .map(ReCaptchaResponseDTO::isSuccess)
                                .orElse(false);

        if (isBot) {
            return "redirect:/home";
        }



        userService.registerUser(userRegistrationDto);

        return "redirect:/home";
    }
}
