package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.models.Language;

@Repository
public class LanguageRepository {
	
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

}
