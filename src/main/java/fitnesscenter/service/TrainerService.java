package fitnesscenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitnesscenter.interfaces.repository.ITrainerRepository;
import fitnesscenter.interfaces.service.IUserService;

@Service
public class TrainerService {

	@Autowired
	private ITrainerRepository traiRepo;
	
	@Autowired
	private IUserService userServ;
	
	
}
