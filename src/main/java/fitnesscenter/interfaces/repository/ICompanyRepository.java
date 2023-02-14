package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.Company;

public interface ICompanyRepository {
	
	public List<Company>findAll();
				
	public void update(Company company);
	
	

}
