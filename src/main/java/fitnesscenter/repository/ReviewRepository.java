package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.IReviewRepository;
import fitnesscenter.interfaces.repository.IUserRepository;
import fitnesscenter.models.Review;
import fitnesscenter.models.User;


@Repository
public class ReviewRepository implements IReviewRepository {
	
	@Autowired
	private JdbcTemplate db;
	@Autowired
	private IUserRepository userRepo;
	
	private class RowMap implements RowMapper<Review>{

		@Override
		public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			User submitted =userRepo.findOneById(rs.getString(index++)) ;
			User target=userRepo.findOneById(rs.getString(index++));
			int rating=rs.getInt(index++);
			String txt=rs.getString(index++);
			
			Review review = new Review();
			
			review.setId(id);
			review.setSubmitted(submitted);
			review.setTarget(target);
			review.setRating(rating);
			review.setTxt(txt);
			
			return review;
		}
		
	}
	
	

	@Override
	public List<Review> findAll() {
		String sql = "SELECT id,submitted_id,target_id,rating,txt FROM reviews;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public List<Review> findAllActive() {
		String sql = "SELECT id,submitted_id,target_id,rating,txt FROM reviews WHERE active=1;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public List<Review> findAllForTarget(String id) {
		String sql = "SELECT id,submitted_id,target_id,rating,txt FROM reviews WHERE active=1 AND target_id=?;"; 
		return db.query(sql, new RowMap(),id);
	}

	@Override
	public List<Review> findAllForSubmitted(String id) {
		String sql = "SELECT id,submitted_id,target_id,rating,txt FROM reviews WHERE active=1 AND submitted_id=?;"; 
		return db.query(sql, new RowMap(),id);
	}

	@Override
	public void save(Review review) {
		String sql = "INSERT INTO reviews(id,submitted_id,target_id,rating,txt,active) VALUES (?,?,?,?,?,?);";
		db.update(sql, review.getId(),review.getSubmitted().getId(),review.getTarget().getId(),review.getRating(),review.getTxt() , true);
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE Reviews SET active=0 WHERE id=?";
		db.update(sql, id);
		
	}

}
