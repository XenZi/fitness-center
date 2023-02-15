package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.ICompanyRepository;
import fitnesscenter.models.Company;



@Repository
public class CompanyRepository implements ICompanyRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	private class RowMap implements RowMapper<Company>{

		@Override
		public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			String companyName = rs.getString(index++);
			String ccNumber = rs.getString(index++);
			
			Company company = new Company();
			
			company.setId(id);
			company.setCompanyName(companyName);
			company.setCcNumber(ccNumber);
			
			return company;
		}
		
	}

	@Override
	public List<Company> findAll() {
		String sql = "SELECT id,c_name,credit_card_number FROM companies;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public void update(Company company) {
		String sql = "UPDATE Companies SET c_name=?,credit_card_number=? WHERE id=?";
		db.update(sql, company.getCompanyName(),company.getCcNumber(),company.getId());
	}

}
