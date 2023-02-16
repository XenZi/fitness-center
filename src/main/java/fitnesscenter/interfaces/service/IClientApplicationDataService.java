package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.ClientApplicationData;

public interface IClientApplicationDataService {
	
	public List<ClientApplicationData>findAll();
	
	public ClientApplicationData findOneById(String id);
	
	public void save(ClientApplicationData clientApplicationData, List<String> goals, List<String> conditions, List<String> equipment);
	
	public List <ClientApplicationData> findAllForUser(String userId);

	void delete(String appId);

}
