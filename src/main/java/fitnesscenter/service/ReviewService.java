package fitnesscenter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.IReviewRepository;
import fitnesscenter.interfaces.service.IReviewService;
import fitnesscenter.interfaces.service.IUserService;
import fitnesscenter.models.Review;

@Service
public class ReviewService implements IReviewService{

	@Autowired
	private IReviewRepository reviRepo;
	
	@Autowired
	private IUserService userServ;

	@Override
	public List<Review> findAll() {
		return reviRepo.findAll();
	}

	@Override
	public List<Review> findAllActive() {
		return reviRepo.findAllActive();
	}

	@Override
	public List<Review> findAllForTarget(String id) {
		return reviRepo.findAllForTarget(id);
	}

	@Override
	public List<Review> findAllForSubmitted(String id) {
		return reviRepo.findAllForSubmitted(id);
	}

	@Override
	public float findUserRating(String userId) {
		float rating = 0;
		List<Review> reviewList = findAllForTarget(userId);
		for (int i=0;i<reviewList.size();i++) {
			rating += reviewList.get(i).getRating();
		}
		rating = rating / reviewList.size();
		return rating;
	}

	@Override
	public void save(Review review, String submittedId, String targetId) {
		review.setId(UUID.randomUUID().toString());
		review.setSubmitted(userServ.findOneById(submittedId));
		review.setTarget(userServ.findOneById(targetId));
		reviRepo.save(review);
		
	}

	@Override
	public void delete(String id) {
		reviRepo.delete(id);
	}
	
}
