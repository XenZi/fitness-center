package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.Billing;

public interface IBillingRepository {
	
	public List<Billing>findAll();
	
	public Billing findOne(String id);
	
	public List<Billing>findForPeriod(String startDate, String endDate);
	
	public void save(Billing billing);

}
