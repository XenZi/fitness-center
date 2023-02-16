package fitnesscenter.controller;


import fitnesscenter.interfaces.service.ITrainingSessionService;
import fitnesscenter.models.TrainingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

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
}
