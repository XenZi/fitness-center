package fitnesscenter.controller;


import fitnesscenter.interfaces.service.ITrainingSessionService;
import fitnesscenter.models.ClientApplicationData;
import fitnesscenter.models.TrainingSession;
import fitnesscenter.models.User;
import fitnesscenter.models.WatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/training-session")
public class TrainingSessionController {
    @Autowired
    private ITrainingSessionService trainingSessionService;


    @PostMapping("/create")
    public void postCreate(@ModelAttribute TrainingSession trainingSession, @RequestParam String trainerID, @RequestParam String startingDateTime) {
        trainingSession.setStartTime(LocalDateTime.parse(startingDateTime));
        trainingSessionService.save(trainingSession, trainerID);
    }

    @PostMapping("/delete")
    public void postDelete(@RequestParam String sessionID) {
        trainingSessionService.deleteTrainingSession(sessionID);
    }

    @PostMapping("/book")
    public void postBookTrainingSession(@ModelAttribute ClientApplicationData cad, HttpSession session, String id) {
        User user = (User) session.getAttribute("account");
        trainingSessionService.bookTrainingSession(id, user.getId(), cad);
    }

    @PostMapping("/undo")
    public void postUndo(@RequestParam String id) {
        trainingSessionService.freeTrainingSession(id);
    }

    @GetMapping("/view")
    public String viewGet(@RequestParam String id, Model model) {
        model.addAttribute("activeSession", trainingSessionService.findOneById(id));
        return "test/index";
    }

    @PostMapping("/end")
    public void postEnd(@RequestParam String calories, @RequestParam String pulse, @RequestParam String sessionID) {
        System.out.println(calories);
        System.out.println(pulse);
        System.out.println(sessionID);
        WatchData watchData = new WatchData();
        watchData.setId(UUID.randomUUID().toString());
        watchData.setCalories(Float.parseFloat(calories));
        watchData.setHeartRate(pulse);
        trainingSessionService.endTrainingSession(sessionID, watchData);
    }
}
