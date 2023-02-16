package fitnesscenter.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.IBillingRepository;
import fitnesscenter.interfaces.service.IBillingService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.Billing;

@Service
public class BillingService implements IBillingService{
	
	@Autowired
	private IBillingRepository repo;
	
	@Autowired
	private IUserService userServ;

	@Override
	public List<Billing> findAll() {
		return repo.findAll();
	}

	@Override
	public Billing findOne(String id) {
		return repo.findOne(id);
	}

	@Override
	public List<Billing> findForPeriod(String startDate, String endDate) {
		
		if (endDate=="") {
			endDate = LocalDate.now().toString();			
		}
		
		return repo.findForPeriod(startDate, endDate);
	}

	@Override
	public void save(Billing billing, String clientId, String trainerId) {
		billing.setClient(userServ.findOneById(clientId));
		billing.setTrainer(userServ.findOneById(trainerId));
		repo.save(billing);
	}
	
	

}
