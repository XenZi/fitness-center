package fitnesscenter.interfaces.repository;

import java.util.List;
import fitnesscenter.models.Trainer;


public interface ITrainerRepository {
	
	public List<Trainer>findAll();
		
	public List<Trainer>findAllAccepted();

	public List<Trainer> findAllNotAccepted();
	public Trainer findOneById(String id);
	
	public void save(Trainer trainer);
	
	public void update(Trainer trainer);	

	public void approveTrainer(String id);
	

}
