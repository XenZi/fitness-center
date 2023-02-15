package fitnesscenter.models;



public class Trainer {
	
	private User user;
	private boolean diploma;
	private String certificate;
	private String vocation;
	private boolean accepted;
	
	public Trainer() {
		
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


	
	
	
	
	

}
