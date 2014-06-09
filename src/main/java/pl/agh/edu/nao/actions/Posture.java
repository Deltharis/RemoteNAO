package pl.agh.edu.nao.actions;

public class Posture extends AbstractAction {
	
	private String name;
	private Float speed;

	public Posture(String name, Float speed) {
		super("posture");
		this.name = name;
		this.speed = speed;
		
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = speed;
	}

}
