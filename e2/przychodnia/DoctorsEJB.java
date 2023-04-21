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
	
	public void create(Doctor doctor) {
		System.out.println("Creating doctor!");
		manager.persist(doctor);
	}

	public void delete(int id) {
		Doctor doctor = manager.find(Doctor.class, id);
		manager.remove(doctor);
	}

	public List<Doctor> findByMake(String make) {
		Query q = manager.createQuery("select c from Doctor c where c.make like :make");
		q.setParameter("make", make);
		@SuppressWarnings("unchecked")
		List<Doctor> lista =q.getResultList();
		return lista;
	}

	public Doctor find(int id) {
		return manager.find(Doctor.class, id);
	}

	public List<Doctor> get() {
		Query q = manager.createQuery("select c from Doctor c");
		@SuppressWarnings("unchecked")
		List<Doctor> list = q.getResultList();
		return list;
	}

	public void update(Doctor doctor) {
		doctor = manager.merge(doctor);
	}
	
}
