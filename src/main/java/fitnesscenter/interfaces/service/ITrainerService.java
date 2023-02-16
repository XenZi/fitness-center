package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.enums.ERole;
import fitnesscenter.models.Trainer;
import fitnesscenter.models.User;

public interface ITrainerService {
	
	public List<Trainer>findAll();
	
	public List<Trainer>findAllAccepted();
	
	public Trainer findOneById(String id);
	
	void save(User user, String mainLangId, List<String> allLangIds, ERole role, String certificate, String diploma, String vocation);
	
	void update(Trainer trainer, String mainLangId, List<String> allLangIds);

	public void approveTrainer(String id);


	

}
