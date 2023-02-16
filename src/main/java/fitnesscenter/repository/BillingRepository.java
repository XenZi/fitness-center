package fitnesscenter.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fitnesscenter.interfaces.repository.IBillingRepository;
import fitnesscenter.interfaces.repository.ICompanyRepository;
import fitnesscenter.interfaces.repository.IUserRepository;
import fitnesscenter.models.Billing;
import fitnesscenter.models.User;

@Repository
public class BillingRepository implements IBillingRepository {
	
	
	@Autowired
	private JdbcTemplate db;
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private ICompanyRepository companyRepo;
	
	
	private class RowMap implements RowMapper<Billing>{

		@Override
		public Billing mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int index = 1;
			String id = rs.getString(index++);
			User trainer =userRepo.findOneById(rs.getString(index++));
			User client=userRepo.findOneById(rs.getString(index++));
			String ccNumber=companyRepo.findAll().get(0).getCcNumber();
			float price=rs.getFloat(index++);
			LocalDate date =rs.getDate(index++).toLocalDate();
			
			Billing billing = new Billing();
			
			billing.setId(id);
			billing.setTrainer(trainer);
			billing.setClient(client);
			billing.setCcNumber(ccNumber);
			billing.setPrice(price);
			billing.setDate(date);
			
			return billing;
		}
		
	}

	@Override
	public List<Billing> findAll() {
		String sql = "SELECT id,trainer_id,client_id,price,date_of FROM billing;"; 
		return db.query(sql, new RowMap());
	}

	@Override
	public Billing findOne(String id) {
		try {
			
			String sql = "SELECT id,trainer_id,client_id,price,date_of FROM billing WHERE id=?;"; 
			return db.queryForObject(sql, new RowMap(), id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Billing> findForPeriod(String startDate, String endDate) {
		try {
			
			String sql = "SELECT id,trainer_id,client_id,price,date_of FROM billing WHERE date_of BETWEEN ? and ?;"; 
			return db.query(sql, new RowMap(),startDate,endDate);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public void save(Billing billing) {
		String sql = "INSERT INTO billing(id,trainer_id,client_id,price,date_of) VALUES (?,?,?,?,?);";
		db.update(sql, billing.getId(), billing.getTrainer().getId(),billing.getClient().getId(),billing.getPrice(),billing.getDate());
	}

}
