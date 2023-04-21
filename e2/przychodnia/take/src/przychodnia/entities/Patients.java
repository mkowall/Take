package przychodnia.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Patients {
	private List<Patient> patients = new ArrayList<Patient>();

	public Patients(List<Patient> patients) {
		super();
		this.patients = patients;
	}

	public Patients() {	}
	
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
}
