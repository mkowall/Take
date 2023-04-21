package przychodnia.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Doctors {
	private List<Doctor> doctors = new ArrayList<Doctor>();

	public Doctors(List<Doctor> doctors) {
		super();
		this.doctors = doctors;
	}

	public Doctors() {	}
	
	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
}
