package fitnesscenter.repository;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.enums.EEquipment;
import fitnesscenter.enums.EGoals;
import fitnesscenter.enums.EHealthConditions;
import fitnesscenter.interfaces.repository.IClientApplicationDataRepository;
import fitnesscenter.models.ClientApplicationData;

@Repository
public class ClientApplicationDataRepository implements IClientApplicationDataRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<ClientApplicationData>{

		@Override
		public ClientApplicationData mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			float height=rs.getFloat(index++);
			float weight=rs.getFloat(index++);
			List <EGoals>eGoals=findAllApplicationGoals(id);
			List<EEquipment>eEquipment=findAllApplicationEquipment(id);
			List<EHealthConditions>eHealthConditions=findAllApplicationHealthConditions(id);
			
			ClientApplicationData clientApplicationData = new ClientApplicationData();
			
			clientApplicationData.setId(id);
			clientApplicationData.setHeight(height);
			clientApplicationData.setWeight(weight);
			clientApplicationData.setHealthConditions(eHealthConditions);
			clientApplicationData.setEquipment(eEquipment);
			clientApplicationData.setGoals(eGoals);
			
			
			return clientApplicationData;
		}
		
	}
	
	private class EnumRowMap implements RowMapper<String>{

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index=1;
			String value=rs.getString(index++);
			return value;
		}
		
	}

	@Override
	public List<ClientApplicationData> findAll() {

		String sql = "SELECT id, height, weight FROM ClientApplicationData;"; 
		return db.query(sql, new RowMap());
	
	}

	@Override
	public ClientApplicationData findOneById(String id) {
		
		try {
			
			String sql = "SELECT id, height, weight FROM ClientApplicationData WHERE id=?;"; 
			return db.queryForObject(sql, new RowMap(), id);
		} catch (Exception e) {
			return null;
		}
	
	}

	@Override
	public void save(ClientApplicationData clientApplicationData) {

		String sql = "INSERT INTO ClientApplicationData(id,height,weight) VALUES (?,?,?);";
		db.update(sql, clientApplicationData.getId(),clientApplicationData.getHeight(),clientApplicationData.getWeight());
		connectApplicationGoals(clientApplicationData);
		connectApplicationEquipment(clientApplicationData);
		connectApplicationHealthConditions(clientApplicationData);
	}
	
	@Override
	public void delete(String appId) {
		String sql = "DELETE FROM ClientApplicationData WHERE id=?;";
		db.update(sql, appId);
		deleteGoals(appId);
		deleteConditions(appId);
		deleteEquipment(appId);
	}
	
	private void deleteGoals(String appId) {
		String sql = "DELETE FROM Goals WHERE application_id=?;";
		db.update(sql, appId);
	}
	
	private void deleteConditions(String appId) {
		String sql = "DELETE FROM HealthConditions WHERE application_id=?;";
		db.update(sql, appId);
	}
	
	private void deleteEquipment(String appId) {
		String sql = "DELETE FROM Equipment WHERE application_id=?;";
		db.update(sql, appId);
	}
	
	
	

	
	public List<EGoals>findAllApplicationGoals(String appId){
		String sql="SELECT goal FROM goals WHERE application_id=? ;";
		List<String>list=db.query(sql,new EnumRowMap(),appId);
		List<EGoals>eGoals=new ArrayList<EGoals>();
		
		for(int i=0;i<list.size();i++) {
			eGoals.add(EGoals.valueOf(list.get(i)));
			
			
		}
		return eGoals;
		
	}
	
	public List<EHealthConditions>findAllApplicationHealthConditions(String appId){
		String sql="SELECT h_condition FROM HealthConditions WHERE application_id=?;";
		List<String>list=db.query(sql, new EnumRowMap(),appId);
		List<EHealthConditions>eHealthConditions=new ArrayList<EHealthConditions>();
		
		for (int i=0;i<list.size();i++) {
			eHealthConditions.add(EHealthConditions.valueOf(list.get(i)));
		}
		return eHealthConditions;
	}
	
	
	public List<EEquipment>findAllApplicationEquipment(String appId){
		String sql="SELECT equipment FROM equipment WHERE application_id=? ;";
		List<String>list=db.query(sql,new EnumRowMap(),appId);
		List<EEquipment>eEquipment=new ArrayList<EEquipment>();
		
		for (int i=0;i<list.size();i++) {
			eEquipment.add(EEquipment.valueOf(list.get(i)));
			
		}
		return eEquipment;
	}
	
	
	public void connectApplicationGoals(ClientApplicationData appData){
		String sql="INSERT INTO goals(application_id,goal) VALUES (?,?);";
		List<EGoals>list=appData.getGoals();
		
		for (int i=0;i<list.size();i++) {
			db.update(sql,appData.getId(),list.get(i).toString());
			
		}
				
	}
	
	public void connectApplicationHealthConditions(ClientApplicationData appData){
		String sql="INSERT INTO healthconditions(application_id,h_conditions) VALUES (?,?);";
		List<EHealthConditions>list=appData.getHealthConditions();
		
		for (int i=0;i<list.size();i++) {
			db.update(sql,appData.getId(),list.get(i).toString());
			
		}
				
	}
	
	public void connectApplicationEquipment(ClientApplicationData appData){
		String sql="INSERT INTO equipment(application_id,equipment) VALUES (?,?);";
		List<EEquipment>list=appData.getEquipment();
		
		for (int i=0;i<list.size();i++) {
			db.update(sql,appData.getId(),list.get(i).toString());
			
		}
				
	}

	@Override
	public List<ClientApplicationData> findAllForUser(String userId) {
		try {
			
			String sql="SELECT id, height, weight FROM ClientApplicationData WHERE id in (SELECT application_id FROM trainingSession WHERE client_id=?);";
			return db.query(sql,new RowMap(), userId);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	

}
