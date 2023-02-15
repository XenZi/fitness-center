package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.WatchData;

public interface IWatchDataRepository {
	
	public List<WatchData> findAll();
	
	public WatchData findOneById(String id);
	
	public void save(WatchData watchData);	
	

}
