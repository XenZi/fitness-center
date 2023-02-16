package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.ClientApplicationData;
import fitnesscenter.models.TrainingSession;
import fitnesscenter.models.WatchData;

public interface ITrainingSessionService {
	
	public List<TrainingSession> findAll();
	
	public TrainingSession findOneById(String id);
	
	public List<TrainingSession> findTrainerFreeSessions(String trainerId);
	


	public void save(TrainingSession traSes, String trainerId);
	
	public void bookTrainingSession(String id, String clientId, ClientApplicationData dataId);
	
	public void freeTrainingSession(String id);
	
	public void endTrainingSession(String id, WatchData watchData);
	
	public void deleteTrainingSession(String id);
	
	public void update(TrainingSession traSes);

	List<TrainingSession> findTrainerReservedSessions(String trainerId);

	List<TrainingSession> findClientBookedSessions(String clientId);

	
}
