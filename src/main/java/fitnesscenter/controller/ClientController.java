package fitnesscenter.controller;


import fitnesscenter.interfaces.service.ITrainingSessionService;
import fitnesscenter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ITrainingSessionService trainingSessionService;

    @GetMapping("")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("account");
        model.addAttribute("trainingSessions", trainingSessionService.findClientBookedSessions(user.getId()));
        return "client/index";
    }

}
