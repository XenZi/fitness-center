package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.Language;

public interface ILanguageRepository {
	
	public List<Language>findAll();
	
	public List<Language>findAllForUser(String id);
	
	public List<Language>findAllActive();
	
	public Language findOneById(String id);
	
	public void save(Language language);
	
	public void update(Language language);
	
	public void delete(String id);
	

}
