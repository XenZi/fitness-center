package fitnesscenter.controller;


import fitnesscenter.enums.ERole;
import fitnesscenter.interfaces.service.ILanguageService;
import fitnesscenter.interfaces.service.ITrainerService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.Trainer;
import fitnesscenter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    private ILanguageService languageService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITrainerService trainerService;


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "register";
    }

    @PostMapping("/register")
    public void postRegister(@ModelAttribute User user, @RequestParam String mainLanguageID, @RequestParam(required = false) List<String> multiLanguageIDs) {
        userService.save(user, mainLanguageID, multiLanguageIDs, ERole.CLIENT);

    }

    @PostMapping("/register-trainer")
    public void postRegisterTrainer(@ModelAttribute User user, @RequestParam String mainLanguageID, @RequestParam(required = false) List<String> multiLanguageIDs, @RequestParam String certificate, @RequestParam(required = false) String diploma, @RequestParam String vocation) {
        trainerService.save(user, mainLanguageID, multiLanguageIDs, ERole.TRAINER, certificate, diploma, vocation);
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public void postLogin(@RequestParam String email, @RequestParam String password, HttpSession session, HttpServletResponse response) throws IOException {
        User user = userService.findOneByEmailAndPassword(email, password);
        if (user == null) return;
        session.setAttribute("account", user);
        response.sendRedirect("/");
    }
}
