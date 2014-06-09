package pl.agh.edu.nao;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listWarpper")
public class ListWrapper implements Serializable {

	private static final long serialVersionUID = 9131592506762109609L;
	private List<String> list;

	public ListWrapper() {
	}

	public ListWrapper(List<String> list) {
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}