package pl.agh.edu.nao.actions;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class ComplexAction extends AbstractAction {
	
	private List<AbstractAction> actionChain;

	public ComplexAction(List<AbstractAction> actionChain, String kod) {
		super(kod);
		this.actionChain = actionChain;
	}
	
	public void addAction(AbstractAction a){
		actionChain.add(a);
	}
	
	public void removeAction(AbstractAction a){
		actionChain.remove(a);
	}

}
