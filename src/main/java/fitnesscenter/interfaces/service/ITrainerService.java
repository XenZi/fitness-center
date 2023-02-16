package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.enums.ERole;
import fitnesscenter.models.Trainer;

public interface ITrainerService {
	
	public List<Trainer>findAll();
	
	public List<Trainer>findAllAccepted();
	
	public Trainer findOneById(String id);
	
	void save(Trainer trainer, String mainLangId, List<String> allLangIds, ERole role);
	
	void update(Trainer trainer, String mainLangId, List<String> allLangIds);

	public void approveTrainer(String id);


	

}
