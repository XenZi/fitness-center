package fitnesscenter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.IUserRepository;
import fitnesscenter.interfaces.service.ILanguageService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private ILanguageService langServ;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public List<User> findAllActive() {
		return userRepo.findAllActive();
	}

	@Override
	public List<User> findAllByRole(String role) {
		return userRepo.findAllByRole(role);
	}

	@Override
	public User findOneByEmailAndPassword(String email, String password) {
		return userRepo.findOneByEmailAndPassword(email, password);
	}

	@Override
	public User findOneById(String id) {
		return userRepo.findOneById(id);
	}

	@Override
	public void save(User user, String mainLangId, List<String> allLangIds) {
		user.setId(UUID.randomUUID().toString());
		user.setMainLanguage(langServ.findOneById(mainLangId));
		user.setAllLanguages(langServ.findAllFromList(allLangIds));
		userRepo.save(user);
	}

	@Override
	public void update(User user, String mainLangId, List<String> allLangIds){ 
		user.setMainLanguage(langServ.findOneById(mainLangId));
		user.setAllLanguages(langServ.findAllFromList(allLangIds));
		userRepo.update(user);		
	}

	@Override
	public void delete(String id) {
		userRepo.delete(id);
	}

}
