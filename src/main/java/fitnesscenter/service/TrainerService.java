package fitnesscenter.service;

import java.util.List;
import java.util.UUID;

import fitnesscenter.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.enums.ERole;
import fitnesscenter.interfaces.repository.ITrainerRepository;
import fitnesscenter.interfaces.service.ILanguageService;
import fitnesscenter.interfaces.service.ITrainerService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.Trainer;

@Service
public class TrainerService implements ITrainerService{

	@Autowired
	private ITrainerRepository repo;
	
	@Autowired
	private IUserService userServ;
	
	@Autowired
	private ILanguageService langServ;

	@Override
	public List<Trainer> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Trainer> findAllAccepted() {
		return repo.findAllAccepted();
	}

	@Override
	public Trainer findOneById(String id) {
		return repo.findOneById(id);
	}

	@Override
	public void save(User user, String mainLangId, List<String> allLangIds, ERole role, String certificate, String diploma, String vocation) {
		Trainer trainer = new Trainer();
		user.setId(UUID.randomUUID().toString());
		trainer.setUser(user);
		user.setMainLanguage(langServ.findOneById(mainLangId));
		user.setAllLanguages(langServ.findAllFromList(allLangIds));
		trainer.setCertificate(certificate);
		trainer.setDiploma(diploma.equals("on") == true);
		trainer.setUser(user);
		trainer.setVocation(vocation);
		trainer.getUser().setRole(role);
		trainer.getUser().setMainLanguage(langServ.findOneById(mainLangId));
		trainer.getUser().setAllLanguages(langServ.findAllFromList(allLangIds));
		repo.save(trainer);
	}

	@Override
	public void update(Trainer trainer, String mainLangId, List<String> allLangIds) {
		trainer.getUser().setMainLanguage(langServ.findOneById(mainLangId));
		trainer.getUser().setAllLanguages(langServ.findAllFromList(allLangIds));
		repo.update(trainer);		
	}

	@Override
	public void approveTrainer(String id) {
		repo.approveTrainer(id);
	}

	@Override
	public List<Trainer> findAllNotAccepted() {
		return repo.findAllNotAccepted();
	}
	
	
}
