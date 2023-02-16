package fitnesscenter.controller;


import fitnesscenter.enums.EEquipment;
import fitnesscenter.enums.EGoals;
import fitnesscenter.enums.EHealthConditions;
import fitnesscenter.interfaces.service.ITrainerService;
import fitnesscenter.interfaces.service.ITrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class IndexController {

    @Autowired
    private ITrainerService trainerService;
    @Autowired
    private ITrainingSessionService trainingSessionService;
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("trainers", trainerService.findAllAccepted());
        model.addAttribute("availableSessions", trainingSessionService.findAllFree());
        model.addAttribute("conditions", EHealthConditions.values());
        model.addAttribute("goals", EGoals.values());
        model.addAttribute("equipment", EEquipment.values());
        return "index";
    }

}
