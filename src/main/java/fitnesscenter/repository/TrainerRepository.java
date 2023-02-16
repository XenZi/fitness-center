package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.ITrainerRepository;
import fitnesscenter.interfaces.repository.IUserRepository;

import fitnesscenter.models.Trainer;
import fitnesscenter.models.User;


@Repository
public class TrainerRepository implements ITrainerRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private IUserRepository userRepo;
	
	private class RowMap implements RowMapper<Trainer>{

		@Override
		public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			User user =userRepo.findOneById(rs.getString(index++));
			boolean diploma = rs.getBoolean(index++);
			String certificate=rs.getString(index++);
			String vocation=rs.getString(index++);
			boolean accepted=rs.getBoolean(index++);
				
			
			Trainer trainer = new Trainer();
			
			trainer.setUser(user);
			trainer.setDiploma(diploma);
			trainer.setCertificate(certificate);
			trainer.setVocation(vocation);
			trainer.setAccepted(accepted);
			
			return trainer;
		}
		
	}
	
	

	@Override
	public List<Trainer> findAll() {

		String sql = "SELECT id,diploma,certificate,vocation,accepted FROM trainers;"; 
		return db.query(sql, new RowMap());
	
	}

	@Override
	public List<Trainer> findAllAccepted() {
		String sql = "SELECT id,diploma,certificate,vocation,accepted FROM trainers WHERE accepted=1;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public Trainer findOneById(String id) {

		String sql = "SELECT id,diploma,certificate,vocation,accepted FROM trainers WHERE id=?;"; 
		return db.queryForObject(sql, new RowMap(), id);
	
	}

	@Override
	public void save(Trainer trainer) {
		System.out.println(trainer.isDiploma());
		String sql = "INSERT INTO Trainers (id,diploma,certificate,vocation,accepted) VALUES (?,?,?,?,?);";
		userRepo.save(trainer.getUser());
		db.update(sql, trainer.getUser().getId(),trainer.isDiploma(),trainer.getCertificate(),trainer.getVocation(), false);

	}

	@Override
	public void update(Trainer trainer) {

		String sql = "UPDATE trainers SET diploma=?, certificate=?, vocation=? WHERE id=?;";
		db.update(sql, trainer.isDiploma(),trainer.getCertificate(),trainer.getVocation(), trainer.getUser().getId());
		userRepo.update(trainer.getUser());
	
	}
	

	@Override
	public void approveTrainer(String id) {
		
		String sql = "UPDATE Languages SET accepted=1 WHERE id=?";
		db.update(sql, id);
		
	}

}
