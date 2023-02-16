package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.ClientApplicationData;

public interface IClientApplicationDataRepository {
	
	public List<ClientApplicationData>findAll();
	
	public ClientApplicationData findOneById(String id);
	
	public void save(ClientApplicationData clientApplicationData);
	
	public List <ClientApplicationData> findAllForUser(String userId);

	void delete(String appId);

}
