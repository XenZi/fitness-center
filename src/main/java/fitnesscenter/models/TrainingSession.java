package fitnesscenter.models;

import java.time.LocalDateTime;

import fitnesscenter.enums.EStatus;

public class TrainingSession {
	
	private String id;
	private LocalDateTime startTime;
	private int duration;
	private EStatus status;
	private Trainer trainer;
	private Client client;
	private ClientApplicationData application;
	private WatchData watch;
	private float price;
	
	
	public TrainingSession() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public EStatus getStatus() {
		return status;
	}


	public void setStatus(EStatus status) {
		this.status = status;
	}


	public Trainer getTrainer() {
		return trainer;
	}


	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public ClientApplicationData getApplication() {
		return application;
	}


	public void setApplication(ClientApplicationData application) {
		this.application = application;
	}


	public WatchData getWatch() {
		return watch;
	}


	public void setWatch(WatchData watch) {
		this.watch = watch;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	

}
