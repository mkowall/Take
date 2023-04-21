package przychodnia.entities;

import java.util.List;
import java.util.ArrayList;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
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
	
	@Override
	public String toString() {
		String obj="<Disease> name: "+name+", symptoms: "+symptoms+", causes: "+causes+", cure: "+cure;
		return obj;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String name;
	String symptoms;
	String causes;
	String cure;
	
	@XmlTransient
	//@ManyToMany (mappedBy = "diseases", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@ManyToMany (mappedBy = "diseases", fetch=FetchType.EAGER)
	//@JoinTable(name = "medicalvisits_diseases",inverseJoinColumns = @JoinColumn(name = "medicalvisit_id"),joinColumns = @JoinColumn(name = "disease_id"))
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
		this.symptoms = symptoms;
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
	
	@XmlTransient
	public List<Medicalvisit> getMedicalvisit() {
		return medical_visits;
	}
	public void setMedicalvisit(List<Medicalvisit> medical_visits) {
		this.medical_visits = medical_visits;
	}//*/
}
