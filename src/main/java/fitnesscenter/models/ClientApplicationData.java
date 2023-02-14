package fitnesscenter.models;

import java.util.List;

import fitnesscenter.enums.EEquipment;
import fitnesscenter.enums.EGoals;
import fitnesscenter.enums.EHealthConditions;

public class ClientApplicationData {
	
	private String id;
	private float height;
	private float weight;
	private List<EHealthConditions> healthConditions;
	private List<EEquipment> equipment;
	private List<EGoals> goals;
	
	public ClientApplicationData() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public List<EHealthConditions> getHealthConditions() {
		return healthConditions;
	}

	public void setHealthConditions(List<EHealthConditions> healthConditions) {
		this.healthConditions = healthConditions;
	}

	public List<EEquipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(List<EEquipment> equipment) {
		this.equipment = equipment;
	}

	public List<EGoals> getGoals() {
		return goals;
	}

	public void setGoals(List<EGoals> goals) {
		this.goals = goals;
	}
	
	
	
	
	
	

}
