package fitnesscenter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import fitnesscenter.enums.EStatus;
import fitnesscenter.interfaces.repository.ITrainingSessionRepository;
import fitnesscenter.interfaces.service.IClientApplicationDataService;
import fitnesscenter.interfaces.service.ITrainingSessionService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.interfaces.service.IWatchDataService;
import fitnesscenter.models.ClientApplicationData;
import fitnesscenter.models.TrainingSession;
import fitnesscenter.models.User;
import fitnesscenter.models.WatchData;
import org.springframework.stereotype.Service;

@Service
public class TrainingSessionService implements ITrainingSessionService{
	
	@Autowired
	private ITrainingSessionRepository repo;
	
	@Autowired
	private IUserService userServ;
	
	@Autowired
	private IClientApplicationDataService cadServ;
	
	@Autowired
	private IWatchDataService waddService;

	@Override
	public List<TrainingSession> findAll() {
		return repo.findAll();
	}

	@Override
	public TrainingSession findOneById(String id) {
		return repo.findOneById(id);
	}

	@Override
	public List<TrainingSession> findTrainerFreeSessions(String trainerId) {
		return repo.findAllByTrainerAndStatus(trainerId, "FREE");
	}

	@Override
	public List<TrainingSession> findTrainerReservedSessions(String trainerId) {
		return repo.findAllByTrainerAndStatus(trainerId, "RESERVED");
	}

	@Override
	public List<TrainingSession> findClientBookedSessions(String clientId) {
		return repo.findAllByClientAndStatus(clientId, "RESERVED");
	}

	@Override
	public void save(TrainingSession traSes, String trainerId) {
		User trainer = userServ.findOneById(trainerId);
		traSes.setTrainer(trainer);
		traSes.setStatus(EStatus.FREE);
		traSes.setId(UUID.randomUUID().toString());
		repo.save(traSes);
	}

	@Override
	public void bookTrainingSession(String id, String clientId, ClientApplicationData dataId) {
		TrainingSession traSes = repo.findOneById(id);
		traSes.setClient(userServ.findOneById(clientId));
		traSes.setApplication(dataId);
		traSes.setStatus(EStatus.RESERVED);
		repo.update(traSes);
	}

	@Override
	public void freeTrainingSession(String id) {
		TrainingSession traSes = repo.findOneById(id);
		repo.freeSession(traSes);
	}

	@Override
	public void endTrainingSession(String id, WatchData watchData) {
		TrainingSession traSes = repo.findOneById(id);
		traSes.setWatch(watchData);
		traSes.setStatus(EStatus.PAYMENT_PROCEED);
		repo.freeSession(traSes);
	}

	@Override
	public void deleteTrainingSession(String id) {
		repo.delete(id);
	}

	@Override
	public void update(TrainingSession traSes) {
		repo.update(traSes);		
	}

	@Override
	public List<TrainingSession> findAllFree() {
		return repo.findAllFree();
	}
	
	

}
