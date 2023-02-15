package fitnesscenter.service;

import java.util.ArrayList;
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
		return repo.findAll();
	}

	@Override
	public List<Language> findAllForUser(String id) {
		return repo.findAllForUser(id);
	}

	@Override
	public List<Language> findAllActive() {
		return repo.findAllActive();
	}

	@Override
	public List<Language> findAllFromList(List<String> languageList) {
		List<Language> langList = new ArrayList<Language>();
		for (String langId : languageList) {
			langList.add(findOneById(langId));
		}
		return langList;
	}

	@Override
	public Language findOneById(String id) {
		return repo.findOneById(id);
	}

	@Override
	public void save(Language language) {
		language.setId(UUID.randomUUID().toString());
		repo.save(language);
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
