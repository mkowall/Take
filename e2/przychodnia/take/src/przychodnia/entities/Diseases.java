package przychodnia.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Diseases {
	private List<Disease> diseases = new ArrayList<Disease>();

	public Diseases(List<Disease> diseases) {
		super();
		this.diseases = diseases;
	}

	public Diseases() {	}
	
	public List<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}
}
