package fitnesscenter.controller;


import fitnesscenter.interfaces.service.ILanguageService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.Trainer;
import fitnesscenter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    private ILanguageService languageService;
    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "register";
    }

    @PostMapping("/register")
    public void postRegister(@ModelAttribute User user, @RequestParam String mainLanguageID, @RequestParam(required = false) List<String> multiLanguageIDs) {
        userService.save(user, mainLanguageID, multiLanguageIDs);
    }

    @PostMapping("/register-trainer")
    public void postRegisterTrainer(@ModelAttribute User user) {

    }

    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @PostMapping("/login")
    public void postLogin() {

    }
}
