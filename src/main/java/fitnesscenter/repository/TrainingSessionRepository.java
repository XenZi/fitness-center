package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.enums.EStatus;
import fitnesscenter.interfaces.repository.IClientApplicationDataRepository;
import fitnesscenter.interfaces.repository.ITrainingSessionRepository;
import fitnesscenter.interfaces.repository.IUserRepository;
import fitnesscenter.interfaces.repository.IWatchDataRepository;
import fitnesscenter.models.ClientApplicationData;
import fitnesscenter.models.TrainingSession;
import fitnesscenter.models.User;
import fitnesscenter.models.WatchData;

@Repository
public class TrainingSessionRepository implements ITrainingSessionRepository {
	
	
	@Autowired
	private JdbcTemplate db;
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private IClientApplicationDataRepository cadRepo;
	@Autowired
	private IWatchDataRepository wdRepo;
	
	private class RowMap implements RowMapper<TrainingSession>{

		@Override
		public TrainingSession mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			LocalDateTime startTime =rs.getTimestamp(index++).toLocalDateTime();
			int duration=rs.getInt(index++);
			EStatus status=EStatus.valueOf(rs.getString(index++));
			User trainer=userRepo.findOneById(rs.getString(index++));
			User client=userRepo.findOneById(rs.getString(index++));
			ClientApplicationData application=cadRepo.findOneById(rs.getString(index++));
			WatchData watch=wdRepo.findOneById(rs.getString(index++));
			float price=rs.getFloat(index++);
			
			
			
			TrainingSession trainingSession = new TrainingSession();
			
			trainingSession.setId(id);
			trainingSession.setStartTime(startTime);
			trainingSession.setDuration(duration);
			trainingSession.setStatus(status);
			trainingSession.setTrainer(trainer);
			trainingSession.setClient(client);
			trainingSession.setApplication(application);
			trainingSession.setWatch(watch);
			trainingSession.setPrice(price);
			
			return trainingSession;
		}
		
	}

	@Override
	public List<TrainingSession> findAll() {
		String sql = "SELECT id,start_time,duration,status,trainer_id,client_id,application_id,watch_id,price FROM trainingsession;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public TrainingSession findOneById(String id) {
		String sql = "SELECT id,start_time,duration,status,trainer_id,client_id,application_id,watch_id,price FROM trainingsession WHERE id=?;"; 
		return db.queryForObject(sql, new RowMap(), id);
	}

	@Override
	public List<TrainingSession> findAllByClientAndStatus(String userId, String status) {
		String sql = "SELECT id,start_time,duration,status,trainer_id,client_id,application_id,watch_id,price FROM trainingsession WHERE client_id=? AND status=? ;"; 
		return db.query(sql, new RowMap(),userId,status);
	}
	
	@Override
	public List<TrainingSession> findAllByTrainerAndStatus(String userId, String status) {
		String sql = "SELECT id,start_time,duration,status,trainer_id,client_id,application_id,watch_id,price FROM trainingsession WHERE trainer_id=? AND status=? ;"; 
		return db.query(sql, new RowMap(),userId,status);
	}


	@Override
	public void save(TrainingSession trainingSession) {
		String sql = "INSERT INTO TrainingSession(id,start_time,duration,status,trainer_id,client_id,application_id,watch_id,price,active) VALUES (?,?,?,?,?,?,?,?,?,?);";
		db.update(sql, trainingSession.getId(), trainingSession.getStartTime(),trainingSession.getDuration(),trainingSession.getStatus().toString(),trainingSession.getTrainer().getId(),trainingSession.getClient().getId(),trainingSession.getApplication().getId(),trainingSession.getWatch().getId(),trainingSession.getPrice(), true);
	}

	@Override
	public void update(TrainingSession trainingSession) {

		String sql = "UPDATE trainingsession SET start_time=?, duration=?, status=?, trainer_id=?, client_id=?, application_id=?, watch_id=?, price=? WHERE id=? ;";
		db.update(sql, trainingSession.getStartTime(),trainingSession.getDuration(),trainingSession.getStatus().toString(),trainingSession.getTrainer().getId(),trainingSession.getClient().getId(),trainingSession.getApplication().getId(),trainingSession.getWatch().getId(),trainingSession.getPrice(), trainingSession.getId());
	
	}

	@Override
	public void freeSession(TrainingSession trainingSession) {
		String sql = "UPDATE TrainingSession SET client_id=?, application_id=?, status=? WHERE id=?;";
		db.update(sql,null,null,EStatus.FREE.toString(),trainingSession.getId());
	}

	@Override
	public void delete(String id) {
		
		String sql = "UPDATE trainingsession SET active=0 WHERE id=?";
		db.update(sql, id);
		
	}

}
