package fitnesscenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.service.ICompanyService;
import fitnesscenter.models.Company;
import fitnesscenter.repository.CompanyRepository;

@Service
public class CompanyService implements ICompanyService{

	@Autowired
	private CompanyRepository repo;
	
	@Override
	public List<Company> findAll() {
		return repo.findAll();
	}

	@Override
	public void update(Company company) {
		repo.update(company);
	}

}
