package fitnesscenter.interfaces.repository;

import java.util.List;

import fitnesscenter.models.Review;



public interface IReviewRepository {
	
	public List<Review>findAll();	
	
	public List<Review>findAllActive();
					
	public List<Review>findAllForTarget(String id);
	
	public List<Review>findAllForSubmitted(String id);
	
	public void save(Review review);
			
	public void delete(String id);
	

}
