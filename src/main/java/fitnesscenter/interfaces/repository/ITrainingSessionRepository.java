package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.TrainingSession;

public interface ITrainingSessionRepository {
	
	public List <TrainingSession>findAll();
	
	public TrainingSession findOneById(String id);
	
	public List<TrainingSession>findAllByUserAndStatus(String user,String userId, String status);
	
	public void save(TrainingSession trainingSession);
	
	public void update(TrainingSession trainingSession);
	
	public void delete(String id);

	void freeSession(TrainingSession trainingSession);

}
