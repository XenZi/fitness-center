package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.User;

public interface IUserRepository {
	
	public List<User>findAll();
	
	public List<User>findAllActive();
	
	public List<User>findAllByRole(String role);
	
	public User findOneByEmailAndPassword(String email,String password);
	
	public User findOneById(String id);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(String id);
	
	

}
