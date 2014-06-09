package pl.agh.edu.nao.actionImplementations;

import java.util.Arrays;

import pl.agh.edu.nao.actionImplementations.base.ActionImplementation;
import pl.agh.edu.nao.actions.AbstractAction;
import pl.agh.edu.nao.actions.AngleInterpolation;
import pl.agh.edu.nao.actions.ComplexAction;

public class Yes implements ActionImplementation {
	
	@Override
	public ComplexAction getAction() {
		AbstractAction a1 = new AngleInterpolation(Arrays.asList("HeadPitch"), Arrays.asList(-0.3f, 0.3f, 0.0f),Arrays.asList(1.0f, 2.0f, 3.0f));
    	return new ComplexAction(Arrays.asList(a1), "complex");
	}

}
