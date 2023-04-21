package przychodnia.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Doctor {

	public Doctor(){
		login="new_user";
		password="haslo123";
		name="Nowy";
		surname="Uzytkownik";
		specialization="brak";
	}

	public Doctor(String nlogin, String npassword, String nname, String nsurname, String nspecialization){
		login=nlogin;
		password=npassword;
		name=nname;
		surname=nsurname;
		specialization=nspecialization;
	}
	
	@Override
	public String toString() {
		String obj="<Doctor> login: "+login+", password: "+password+", name: "+name+", surname: "+surname+", specialization: "+specialization;
		return obj;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String login;
	String password;
	String name;
	String surname;
	String specialization;

	//@XmlTransient
	@OneToMany(mappedBy = "doctor", targetEntity=Medicalvisit.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	List<Medicalvisit> medical_visits = new ArrayList<Medicalvisit>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	@XmlTransient
	public List<Medicalvisit> getMedicalvisit() {
		return medical_visits;
	}
	public void setMedicalvisit(List<Medicalvisit> medical_visits) {
		this.medical_visits = medical_visits;
	}//*/
	
}
