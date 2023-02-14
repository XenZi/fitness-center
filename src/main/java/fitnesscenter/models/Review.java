package fitnesscenter.models;

public class Review {
	
	private String id;
	private User submitted;
	private User target;
	private int rating;
	private String txt;
	
	public Review() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSubmitted() {
		return submitted;
	}

	public void setSubmitted(User submitted) {
		this.submitted = submitted;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	
	
	
	
	

}
