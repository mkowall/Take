package pl.kurs.komis.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
//@Entity(name="medicalvisits")
public class Medicalvisit {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	Date visit_date;
	String remarks;
	String medicines;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Doctor doctor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Patient patient;
	
	//@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    //@JoinTable(name = "medicalvisits_diseases",joinColumns = @JoinColumn(name = "medicalvisit_id"),inverseJoinColumns = @JoinColumn(name = "disease_id"))
	List<Disease> diseases = new ArrayList<Disease>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getVisit_date() {
		return visit_date;
	}
	public void setRemarks(Date visit_date) {
		this.visit_date = visit_date;
	}

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMedicines() {
		return medicines;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}//*/
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}//*/
	

	public List<Disease> getDisease() {
		return diseases;
	}
	public void setDisease(List<Disease> diseases) {
		this.diseases = diseases;
	}//*/
}
