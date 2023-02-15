package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.IWatchDataRepository;
import fitnesscenter.models.WatchData;

@Repository
public class WatchDataRepository implements IWatchDataRepository{

	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<WatchData>{

		@Override
		public WatchData mapRow(ResultSet rs, int rowNum) throws SQLException {

			int index = 1;
			String id = rs.getString(index++);
			String heartRate = rs.getString(index++);
			float calories = rs.getFloat(index++);
			
			WatchData watchData = new WatchData();
			watchData.setId(id);
			watchData.setHeartRate(heartRate);
			watchData.setCalories(calories);
			
			return watchData;
		}
		
	}

	@Override
	public List<WatchData> findAll() {
		String sql = "SELECT id, heart_rate, calories FROM WatchData;";
		return db.query(sql,  new RowMap());
	}

	@Override
	public WatchData findOneById(String id) {
		String sql = "SELECT id, heart_rate, calories FROM WatchData WHERE id=?;";
		return db.queryForObject(sql, new RowMap(), id);
	}

	@Override
	public void save(WatchData watchData) {
		String sql = "INSERT INTO WatchData (id, heart_rate, calories) VALUES (?,?,?);";
		db.update(sql, watchData.getId(), watchData.getHeartRate(), watchData.getCalories());
	}
	
}
