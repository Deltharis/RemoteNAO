package pl.agh.edu.nao.actions;

import java.util.List;

public class AngleInterpolation extends AbstractAction {
	
	private List<String> names;
	private List<Float> angles;
	private List<Float> times;
	
	public AngleInterpolation(List<String> names, List<Float> angles,
			List<Float> times) {
		super("angle");
		
		this.names = names;
		this.angles = angles;
		this.times = times;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<Float> getAngles() {
		return angles;
	}

	public void setAngles(List<Float> angles) {
		this.angles = angles;
	}

	public List<Float> getTimes() {
		return times;
	}

	public void setTimes(List<Float> times) {
		this.times = times;
	}
	
}
