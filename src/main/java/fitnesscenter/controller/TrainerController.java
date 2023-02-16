package fitnesscenter.controller;


import fitnesscenter.interfaces.service.ITrainerService;
import fitnesscenter.interfaces.service.ITrainingSessionService;
import fitnesscenter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private ITrainingSessionService trainingSessionService;

    @GetMapping("")
    public String index(Model model, HttpSession session) {
        User user = (User) session.getAttribute("account");
        System.out.println(user.getId());
        System.out.println(trainingSessionService.findTrainerReservedSessions(user.getId()));
        model.addAttribute("reservedSessions", trainingSessionService.findTrainerReservedSessions(user.getId()));
        model.addAttribute("availableSessions", trainingSessionService.findTrainerFreeSessions(user.getId()));
        return "trainer/index";
    }
}
