package fitnesscenter.models;

import java.time.LocalDateTime;

import fitnesscenter.enums.EStatus;

public class TrainingSession {
	
	private String id;
	private LocalDateTime startTime;
	private int duration;
	private EStatus status;
	private User trainer;
	private User client;
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


	public User getTrainer() {
		return trainer;
	}


	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}


	public User getClient() {
		return client;
	}


	public void setClient(User client) {
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
