package przychodnia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import przychodnia.entities.Doctor;


@Stateless
public class DoctorsEJB {

	@PersistenceContext(name="przychodnia")
	EntityManager manager;

	public List<Doctor> get() {
		Query q = manager.createQuery("select c from Doctor c");
		@SuppressWarnings("unchecked")
		List<Doctor> list = q.getResultList();
		return list;
	}

	public List<Doctor> findBy(String login, String name, String surname, String specialization) {
		String query="select d from Doctor d where "
				+ "d.login like '%"+login+"%' "
				+ "and d.name like '%"+name+"%' "
				+ "and d.surname like '%"+surname+"%' "
				+ "and d.specialization like '%"+specialization+"%'";
		
		Query q = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Doctor> lista=q.getResultList();
		return lista;
	}
	
	public void create(Doctor doctor) {
		System.out.println("Creating doctor!");
		manager.persist(doctor);
	}

	public Doctor find(int id) {
		//return manager.find(Doctor.class, id);
		
		Doctor d = manager.find(Doctor.class, id);
		System.out.println(d.getMedicalvisit());
		return d;
	}

	public void update(Doctor doctor) {
		doctor = manager.merge(doctor);
	}

	public void delete(int id) {
		Doctor doctor = manager.find(Doctor.class, id);
		manager.remove(doctor);
	}
	
}
