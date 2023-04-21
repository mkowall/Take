package przychodnia;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import przychodnia.entities.Patient;


@Stateless
public class PatientsEJB {

	@PersistenceContext(name="przychodnia")
	EntityManager manager;

	public List<Patient> get() {
		Query q = manager.createQuery("select c from Patient c");
		@SuppressWarnings("unchecked")
		List<Patient> list = q.getResultList();
		return list;
	}

	public List<Patient> findBy(String name, String surname) {
		String query="select p from Patient p where "
				+ "p.name like '%"+name+"%' "
				+ "and p.surname like '%"+surname+"%' ";
		
		Query q = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Patient> lista=q.getResultList();
		return lista;
	}
	
	public void create(Patient Patient) {
		System.out.println("Creating Patient!");
		manager.persist(Patient);
	}

	public Patient find(BigInteger pesel) {
		return manager.find(Patient.class, pesel);
	}

	public void update(Patient Patient) {
		Patient = manager.merge(Patient);
	}

	public void delete(BigInteger pesel) {
		Patient Patient = manager.find(Patient.class, pesel);
		manager.remove(Patient);
	}
	
}
