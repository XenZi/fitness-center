package fitnesscenter.models;

import java.util.List;

public class Client {
	
	private User user;
	private List<TrainingSession> trainingSessionList;
	
	public Client() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<TrainingSession> getTrainingSessionList() {
		return trainingSessionList;
	}

	public void setTrainingSessionList(List<TrainingSession> trainingSessionList) {
		this.trainingSessionList = trainingSessionList;
	}
	
	
	

}
