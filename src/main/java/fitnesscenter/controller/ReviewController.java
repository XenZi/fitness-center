package fitnesscenter.controller;


import fitnesscenter.interfaces.repository.IReviewRepository;
import fitnesscenter.interfaces.service.IReviewService;
import fitnesscenter.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private IReviewService service;


    @PostMapping("/create")
    public void postCreate(@RequestParam String trainerID, @RequestParam String clientID, @ModelAttribute Review review) {
        service.save(review, clientID, trainerID);
    }
}
