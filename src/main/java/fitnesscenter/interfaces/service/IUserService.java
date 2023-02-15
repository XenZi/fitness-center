package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.User;

public interface IUserService {
	
	
	public List<User>findAll();
	
	public List<User>findAllActive();
	
	public List<User>findAllByRole(String role);
	
	public User findOneByEmailAndPassword(String email,String password);
	
	public User findOneById(String id);
	
	void save(User user, String mainLangId, List<String> allLangIds);
	
	void update(User user, String mainLangId, List<String> allLangIds);
	
	public void delete(String id);



}
