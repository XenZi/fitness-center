package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.Language;

public interface ILanguageService {
	
	public List<Language>findAll();
	
	public List<Language>findAllForUser(String id);
	
	public List<Language>findAllActive();
	
	public List<Language>findAllFromList(List<String> languageList);
	
	public Language findOneById(String id);
	
	public void save(Language language);
	
	public void update(Language language);
	
	public void delete(String id);

}
