package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.TrainingSession;

public interface ITrainingSessionRepository {
	
	public List <TrainingSession>findAll();
	
	public TrainingSession findOneById(String id);
	
	
	public void save(TrainingSession trainingSession);
	
	public void update(TrainingSession trainingSession);
	
	public void delete(String id);

	void freeSession(TrainingSession trainingSession);

	List<TrainingSession> findAllByTrainerAndStatus(String userId, String status);

	List<TrainingSession> findAllByClientAndStatus(String userId, String status);

	List<TrainingSession> findAllFree();

}
