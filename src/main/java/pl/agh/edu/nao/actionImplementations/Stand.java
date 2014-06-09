package pl.agh.edu.nao.actionImplementations;

import java.util.Arrays;

import pl.agh.edu.nao.actionImplementations.base.ActionImplementation;
import pl.agh.edu.nao.actions.AbstractAction;
import pl.agh.edu.nao.actions.ComplexAction;
import pl.agh.edu.nao.actions.Posture;
import pl.agh.edu.nao.actions.Say;

public class Stand implements ActionImplementation {
	
	@Override
	public ComplexAction getAction() {
		AbstractAction stand = new Posture("Stand", new Float(0.8));
		AbstractAction say = new Say("Go to hell");
		AbstractAction sit = new Posture("Sit", new Float(0.8));
    	return new ComplexAction(Arrays.asList(stand,say,sit), "complex");
	}

}
