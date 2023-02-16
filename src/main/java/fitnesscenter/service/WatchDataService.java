package fitnesscenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.IWatchDataRepository;
import fitnesscenter.interfaces.service.IWatchDataService;
import fitnesscenter.models.WatchData;

@Service
public class WatchDataService  implements IWatchDataService{

	@Autowired
	private IWatchDataRepository watchRepo;
	
	@Override
	public List<WatchData> findAll() {
		return watchRepo.findAll();
	}

	@Override
	public List<WatchData> findAllForUser(String userId) {
		return watchRepo.findAllForUser(userId);
	}

	@Override
	public WatchData findOneById(String id) {
		return watchRepo.findOneById(id);
	}

	@Override
	public void save(WatchData watchData) {
		watchRepo.save(watchData);
	}
	
	

}
