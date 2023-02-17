package fitnesscenter.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fitnesscenter.interfaces.service.IBillingService;
import fitnesscenter.interfaces.service.IReviewService;
import fitnesscenter.interfaces.service.ITrainerService;
import fitnesscenter.models.Billing;
import fitnesscenter.models.Trainer;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private IBillingService billServ;
	
	@Autowired
	private ITrainerService traiServ;
	
	@Autowired
	private IReviewService revServ;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Trainer> trainerList = traiServ.findAllAccepted();
		HashMap<String, Float> ratingMap = new HashMap<String, Float>();
		for (Trainer t : trainerList) {
			ratingMap.put(t.getUser().getId(), revServ.findUserRating(t.getUser().getId()) );
		}
		
		List<Billing> billList = billServ.findAll();
		float total = 0;
		for (Billing b : billList) {
			total += b.getPrice();
		}
		
		model.addAttribute("total", total);
		model.addAttribute("ratings", ratingMap);
		model.addAttribute("trainers", trainerList);
		model.addAttribute("billing", billList);
		model.addAttribute("today", LocalDate.now());
		System.out.println(2);
		return "report/index";
	}
	
	@GetMapping("index/search")
	public String indexSearch(Model model, @RequestParam String searchDateStart, @RequestParam String searchDateEnd) {
		List<Trainer> trainerList = traiServ.findAllAccepted();
		HashMap<String, Float> ratingMap = new HashMap<String, Float>();
		for (Trainer t : trainerList) {
			ratingMap.put(t.getUser().getId(), revServ.findUserRating(t.getUser().getId()) );
		}
		
		List<Billing> billList = billServ.findForPeriod(searchDateStart, searchDateEnd);
		float total = 0;
		for (Billing b : billList) {
			total += b.getPrice();
		}
		model.addAttribute("total", total);
		model.addAttribute("ratings", ratingMap);
		model.addAttribute("trainers", trainerList);
		model.addAttribute("billing", billList);
		model.addAttribute("today", LocalDate.now());
		System.out.println(1);
		return "report/index"; 	
		
	}
	
}
