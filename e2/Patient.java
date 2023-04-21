package pl.kurs.komis.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int pesel;
	String name;
	String surname;
	String phone;
	String email;
	String password;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,orphanRemoval=true)
	List<Medicalvisit> medical_visits = new ArrayList<Medicalvisit>();

	public int getPesel(){
		return pesel;
	}
	public void setPesel(int pesel){
		this.pesel=pesel;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Medicalvisit> getMedicalvisit() {
		return medical_visits;
	}
	public void setMedicalvisit(List<Medicalvisit> medical_visits) {
		this.medical_visits = medical_visits;
	}//*/
	
}
