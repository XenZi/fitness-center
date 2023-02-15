package fitnesscenter.interfaces.service;

import java.util.List;

import fitnesscenter.models.Review;

public interface IReviewService {

	public List<Review>findAll();	
	
	public List<Review>findAllActive();
					
	public List<Review>findAllForTarget(String id);
	
	public List<Review>findAllForSubmitted(String id);
	
	public float findUserRating(String userId);
	
	void save(Review review, String submittedId, String targetId);
			
	public void delete(String id);

	
	
}
