package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.Company;

public interface ICompanyService {
	
	public List<Company>findAll();
	
	public void update(Company company);

}
