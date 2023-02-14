package fitnesscenter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AuthController {

    @GetMapping("/register")
    public String getRegister() {
        return "index";
    }

    @PostMapping("/register")
    public void postRegister() {

    }

    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @PostMapping("/login")
    public void postLogin() {

    }
}
