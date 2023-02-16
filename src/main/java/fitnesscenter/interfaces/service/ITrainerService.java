package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.Trainer;

public interface ITrainerService {
	

	public List<Trainer>findAll();
		
	public List<Trainer>findAllAccepted();
	
	public Trainer findOneById(String id);
	
	public void save(Trainer trainer);
	
	public void update(Trainer trainer);	

	public void approveTrainer(String id);

}
