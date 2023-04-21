package przychodnia.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Patient {

	public Patient(){
		pesel=new BigInteger("12345678901");
		name="Krzysztof";
		surname="Jarzyna";
		phone="(+00) 000 000 000";
		email="k.jarz@polsl.pl";
		password="niezgadniesz";
	}

	public Patient(BigInteger npesel, String nname, String nsurname, String nphone, String nemail, String npassword){
		pesel=npesel;
		name=nname;
		surname=nsurname;
		phone=nphone;
		email=nemail;
		password=npassword;
	}
	
	@Override
	public String toString() {
		String obj="<Patient> pesel: "+pesel+", name: "+name+", surname: "+surname+", phone: "+phone+", email: "+email+", password: "+password;
		return obj;
	}
	
	@Id
	BigInteger pesel;
	String name;
	String surname;
	String phone;
	String email;
	String password;
	
	@OneToMany(mappedBy = "patient", targetEntity=Medicalvisit.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	List<Medicalvisit> medical_visits = new ArrayList<Medicalvisit>();

	public BigInteger getPesel(){
		return pesel;
	}
	public void setPesel(BigInteger pesel){
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
	
	@XmlTransient
	public List<Medicalvisit> getMedicalvisit() {
		return medical_visits;
	}
	public void setMedicalvisit(List<Medicalvisit> medical_visits) {
		this.medical_visits = medical_visits;
	}//*/
	
}
