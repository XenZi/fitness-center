package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.models.WatchData;

@Repository
public class WatchDataRepository {

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
	
}
