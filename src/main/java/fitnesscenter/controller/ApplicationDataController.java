package fitnesscenter.controller;


import fitnesscenter.interfaces.service.IClientApplicationDataService;
import fitnesscenter.models.ClientApplicationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/application-data")
@Controller
public class ApplicationDataController {

    @Autowired
    private IClientApplicationDataService applicationDataService;

    @PostMapping("/create")
    public void postCreate(@ModelAttribute ClientApplicationData data, HttpSession session) {

    }
}
