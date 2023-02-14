package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.ILanguageRepository;
import fitnesscenter.models.Language;

@Repository
public class LanguageRepository implements ILanguageRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<Language>{

		@Override
		public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			String languageName = rs.getString(index++);
			
			Language language = new Language();
			
			language.setId(id);
			language.setLanguageName(languageName);
			
			return language;
		}
		
	}

	@Override
	public List<Language> findAll() {
		String sql = "SELECT id, language_name FROM Languages;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public List<Language> findAllForUser(String id) {
		
		String sql = "SELECT id,language_name FROM Languages WHERE id in (SELECT language_id FROM userslanguages WHERE user_id = ?);"; 
		return db.query(sql, new RowMap());		
	}

	@Override
	public List<Language> findAllActive() {
		String sql = "SELECT id, language_name FROM Languages WHERE active=1;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public Language findOneById(String id) {
		String sql = "SELECT id, language_name FROM Languages WHERE id=?;"; 
		return db.queryForObject(sql, new RowMap(), id);
	}

	@Override
	public void save(Language language) {
		String sql = "INSERT INTO Languages (id, language_name, active) VALUES (?,?,?);";
		db.update(sql, language.getId(), language.getLanguageName(), true);
	}

	@Override
	public void update(Language language) {
		String sql = "UPDATE Languages SET language_name=? WHERE id=?";
		db.update(sql, language.getLanguageName(), language.getId());
	}

	@Override
	public void delete(String id) {
		String sql = "UPDATE Languages SET active=0 WHERE id=?";
		db.update(sql, id);
	}

}
