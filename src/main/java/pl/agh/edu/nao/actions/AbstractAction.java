package pl.agh.edu.nao.actions;

import com.google.gson.Gson;

public abstract class AbstractAction {
	
	protected String kod;
	
	
	
	public AbstractAction(String s) {
		super();
		initKod(s);
	}

	protected void initKod(String s){
		kod = s;
	}
	
	@Override
	public final String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

}
