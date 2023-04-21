package przychodnia.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Medicalvisits {
	private List<Medicalvisit> medicalvisits = new ArrayList<Medicalvisit>();

	public Medicalvisits(List<Medicalvisit> medicalvisits) {
		super();
		this.medicalvisits = medicalvisits;
	}

	public Medicalvisits() {	}
	
	public List<Medicalvisit> getMedicalVisits() {
		return medicalvisits;
	}

	public void setMedicalVisits(List<Medicalvisit> medicalvisits) {
		this.medicalvisits = medicalvisits;
	}
}
