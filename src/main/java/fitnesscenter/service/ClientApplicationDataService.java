package fitnesscenter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.enums.EEquipment;
import fitnesscenter.enums.EGoals;
import fitnesscenter.enums.EHealthConditions;
import fitnesscenter.interfaces.repository.IClientApplicationDataRepository;
import fitnesscenter.interfaces.service.IClientApplicationDataService;
import fitnesscenter.models.ClientApplicationData;

@Service
public class ClientApplicationDataService implements IClientApplicationDataService{

	@Autowired
	private IClientApplicationDataRepository repo;
	
	@Override
	public List<ClientApplicationData> findAll() {
		return repo.findAll();
	}

	@Override
	public ClientApplicationData findOneById(String id) {
		return repo.findOneById(id);
	}

	@Override
	public void save(ClientApplicationData clientApplicationData, List<String> goals, List<String> conditions,
			List<String> equipment) {
		
		List<EGoals> goalList = new ArrayList<EGoals>();
		for (int i=0;i<goals.size();i++) {
			goalList.add(EGoals.valueOf(goals.get(i)));
		}
		clientApplicationData.setGoals(goalList);
		
		List<EEquipment> eqList = new ArrayList<EEquipment>();
		for (int i=0;i<equipment.size();i++) {
			eqList.add(EEquipment.valueOf(equipment.get(i)));
		}
		clientApplicationData.setEquipment(eqList);
		
		List<EHealthConditions> condList = new ArrayList<EHealthConditions>();
		for (int i=0;i<conditions.size();i++) {
			condList.add(EHealthConditions.valueOf(conditions.get(i)));
		}
		clientApplicationData.setHealthConditions(condList);
		
		repo.save(clientApplicationData);
	}

	@Override
	public List<ClientApplicationData> findAllForUser(String userId) {
		return repo.findAllForUser(userId);
	}

	@Override
	public void delete(String appId) {
		repo.delete(appId);
	}

}
