package fitnesscenter.models;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
	
	private User user;
	private boolean diploma;
	private String certificate;
	private String vocation;
	private boolean accepted;
	private List<TrainingSession> trainingSessionList;
	
	public Trainer() {
		this.trainingSessionList = new ArrayList<TrainingSession>();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDiploma() {
		return diploma;
	}

	public void setDiploma(boolean diploma) {
		this.diploma = diploma;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public List<TrainingSession> getTrainingSessionList() {
		return trainingSessionList;
	}

	public void setTrainingSessionList(List<TrainingSession> trainingSessionList) {
		this.trainingSessionList = trainingSessionList;
	}
	
	
	
	

}
