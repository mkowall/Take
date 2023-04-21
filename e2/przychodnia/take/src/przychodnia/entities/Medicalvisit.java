package przychodnia.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@XmlRootElement
public class Medicalvisit {

	public Medicalvisit(){
		visit_date=new Date(System.currentTimeMillis());
		remarks="bole brzucha, wymioty";
		medicines="rapacholin";
	}

	public Medicalvisit(Date nvisit_date, String nremarks, String nmedicines){
		visit_date=nvisit_date;
		remarks=nremarks;
		medicines=nmedicines;
	}

	public Medicalvisit(Date nvisit_date, String nremarks, String nmedicines, Doctor ndoctor, Patient npatient){
		visit_date=nvisit_date;
		remarks=nremarks;
		medicines=nmedicines;
		
		doctor=ndoctor;
		patient=npatient;
		//diseases=new ArrayList<Disease>();
	}
	
	@Override
	public String toString() {
		String obj="<Medicalvisit> remarks: "+remarks+", medicines: "+medicines+", doctor: "+doctor+", patient: "+patient;
		return obj;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	Date visit_date;
	
	String remarks;
	String medicines;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="doctor_id")
	Doctor doctor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="patient_id")
	@JoinColumn(name="patient_pesel")
	Patient patient;

	//@ManyToMany(targetEntity=Disease.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@ManyToMany(targetEntity=Disease.class, fetch=FetchType.EAGER)
	@JoinTable(name = "medicalvisits_diseases",joinColumns = @JoinColumn(name = "medicalvisit_id"),inverseJoinColumns = @JoinColumn(name = "disease_id"))
	List<Disease> diseases = new ArrayList<Disease>();

	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
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
	}
	
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public List<Disease> getDiseases() {
		return diseases;
	}
	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}
	public void setDisease(Disease disease) {
		this.diseases.add(disease);
	}//*/
}
