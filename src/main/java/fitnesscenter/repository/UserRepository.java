package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.enums.ERole;
import fitnesscenter.interfaces.repository.ILanguageRepository;
import fitnesscenter.interfaces.repository.IUserRepository;
import fitnesscenter.models.Language;
import fitnesscenter.models.User;


@Repository
public class UserRepository implements IUserRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	@Autowired
	private ILanguageRepository langRepo;
	
	private class RowMap implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			String phoneNumber = rs.getString(index++);
			String address = rs.getString(index++);
			String firstName = rs.getString(index++);
			String lastName= rs.getString(index++);
			String password = rs.getString(index++);
			String email= rs.getString(index++);
			String ccNumber = rs.getString(index++);
			Language language=langRepo.findOneById(rs.getString(index++));
			List<Language> allLanguages=langRepo.findAllForUser(id);
			ERole role=ERole.valueOf(rs.getString(index++));
			TimeZone timezone=TimeZone.getTimeZone(rs.getString(index++));
			
			
			User user = new User();
			
			user.setId(id);
			user.setPhoneNumber(phoneNumber);
			user.setAddress(address);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			user.setCcNumber(ccNumber);
			user.setMainLanguage(language);
			user.setAllLanguages(allLanguages);
			user.setRole(role);
			user.setTimezone(timezone);
			
			return user;
		}
		
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id FROM users;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public List<User> findAllActive() {
		String sql = "SELECT id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id FROM users WHERE active=1;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public List<User> findAllByRole(String role) {
		String sql = "SELECT id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id FROM users WHERE active=1 AND role=?;"; 
		return db.query(sql, new RowMap(),role);
	}

	@Override
	public User findOneByEmailAndPassword(String email, String password) {
		String sql = "SELECT id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id FROM users WHERE active=1 AND email=? AND password=? ;"; 
		return db.queryForObject(sql, new RowMap(),email,password);
	}

	@Override
	public User findOneById(String id) {
		String sql = "SELECT id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id FROM users WHERE id=?;"; 
		return db.queryForObject(sql, new RowMap(), id);
	}

	@Override
	public void save(User user) {
		String sql = "INSERT INTO Users(id,phone_number,address,first_name,last_name,password,email,credit_card_number,main_language,role,timezone_id,active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
		db.update(sql, user.getId(),user.getPhoneNumber(),user.getAddress(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getEmail(),user.getCcNumber(),user.getMainLanguage(),user.getRole(),user.getTimezone().getID(),true);
		for(int i=0;i<user.getAllLanguages().size();i++) {
			connectLanguageUser(user.getAllLanguages().get(i).getId(),user.getId());
		}
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE Users SET phone_number=? ,address=?,first_name=?,last_name=?,password=?,email=?,credit_card_number=?,main_language=?,role=?,timezone_id=? WHERE id=?;";
		db.update(sql, user.getPhoneNumber(),user.getAddress(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getEmail(),user.getCcNumber(),user.getMainLanguage(),user.getRole(),user.getTimezone().getID(), user.getId());
		disconnectLanguageUser(user.getId());
		
		for(int i=0;i<user.getAllLanguages().size();i++) {
			connectLanguageUser(user.getAllLanguages().get(i).getId(),user.getId());
		}
	}
	
	private void connectLanguageUser(String languageId,String userId) {
		String sql="INSERT INTO UsersLanguages(user_id,language_id) VALUES (?,?);";
		db.update(sql,userId,languageId);				
	}
	

	@Override
	public void delete(String id) {
		String sql = "UPDATE Users SET active=0 WHERE id=?";
		db.update(sql, id);
	}
	
	private void disconnectLanguageUser(String userId) {
		String sql="DELETE FROM UsersLanguages WHERE user_id=?;";
		db.update(sql,userId);
	}

}
