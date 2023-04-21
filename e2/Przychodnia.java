package pl.kurs.komis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.kurs.komis.entities.Disease;

public class Przychodnia {
	public static Przychodnia main_class;
	
	public static void main(String[] args){
		main_class=new Przychodnia();
		main_class.write_diseases();
		//main_class.write_doctors();
		//main_class.write_patients();
		//main_class.write_medicalvisits();
	}
	
	private void write_diseases(){
		// dodanie z konstruktorem domyœlnym
		main_class.create_disease(new Disease());
		
		//dodanie z u¿yciem funkcji set
		Disease tmp=new Disease();
		tmp.setName("Grypa zoladkowa");
		tmp.setSymptoms("Wymioty, biegunka");
		tmp.setCauses("Zarazenie od drugiej osoby");
		tmp.setCure("Nie wiem");
		main_class.create_disease(tmp);
		
		//dodanie z u¿yciem konstruktorów parametrycznych
		main_class.create_disease(new Disease("Zlamana noga", "Ogromny bol, opuchlizna, siny kolor", "Gdyby kozka nie skaka³a itd.", "Do gipsu, miesiac L4"));
		main_class.create_disease(new Disease("", "", "", ""));
	}
	
	private void create_disease(Disease tmp_disease){
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("przychodnia");
		EntityManager manager = managerFactory.createEntityManager();
		try{
			manager.getTransaction().begin();
			
			manager.persist(tmp_disease);
			
			manager.getTransaction().commit();
		}catch(Exception ex){
			try {manager.getTransaction().rollback(); } catch (Exception e) { }
		}finally{
			manager.close();
			managerFactory.close();
		}
	}
	
}
