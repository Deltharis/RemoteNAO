package pl.agh.edu.nao.actions;

public class Say extends AbstractAction {
	
	private String text;

	public Say(String text) {
		super("say");
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


}
