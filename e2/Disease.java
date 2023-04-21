package pl.kurs.komis.entities;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
//@Entity(name="diseases")
public class Disease {

	public Disease(){
		name="Przeziebienie";
		symptoms="Katar, kaszel";
		causes="Zbyt lekki ubior na zimna pogode";
		cure="Teraflu w saszetkach";
	}

	public Disease(String nname, String nsymptoms, String ncauses, String ncure){
		name=nname;
		symptoms=nsymptoms;
		causes=ncauses;
		cure=ncure;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String name;
	String symptoms;
	String causes;
	String cure;
	
	//@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	@ManyToMany(mappedBy = "diseases")
	List<Medicalvisit> medical_visits = new ArrayList<Medicalvisit>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.name = symptoms;
	}

	public String getCauses() {
		return causes;
	}
	public void setCauses(String causes) {
		this.causes = causes;
	}

	public String getCure() {
		return cure;
	}
	public void setCure(String cure) {
		this.cure = cure;
	}

	public List<Medicalvisit> getMedicalvisit() {
		return medical_visits;
	}
	public void setMedicalvisit(List<Medicalvisit> medical_visits) {
		this.medical_visits = medical_visits;
	}//*/
}
