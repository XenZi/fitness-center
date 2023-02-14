package fitnesscenter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.ILanguageRepository;
import fitnesscenter.interfaces.service.ILanguageService;
import fitnesscenter.models.Language;

@Service
public class LanguageService implements ILanguageService{

	
	@Autowired
	private ILanguageRepository repo;

	@Override
	public List<Language> findAll() {
		repo.findAll();
		return null;
	}

	@Override
	public List<Language> findAllForUser(String id) {
		repo.findAllForUser(id);
		return null;
	}

	@Override
	public List<Language> findAllActive() {
		repo.findAllActive();
		return null;
	}

	@Override
	public Language findOneById(String id) {
		repo.findOneById(id);
		return null;
	}

	@Override
	public void save(Language language) {
		language.setId(UUID.randomUUID().toString());
	}

	@Override
	public void update(Language language) {
		repo.update(language);
	}

	@Override
	public void delete(String id) {
		repo.delete(id);
	}
	
	
	

}
