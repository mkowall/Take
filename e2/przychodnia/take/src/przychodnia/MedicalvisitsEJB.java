package przychodnia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import przychodnia.entities.Medicalvisit;


@Stateless
public class MedicalvisitsEJB {

	@PersistenceContext(name="przychodnia")
	EntityManager manager;

	public List<Medicalvisit> get() {
		Query q = manager.createQuery("select c from Medicalvisit c");
		@SuppressWarnings("unchecked")
		List<Medicalvisit> list = q.getResultList();
		return list;
	}

	//public List<Medicalvisit> findBy(String visit_date, BigInteger patient_pesel, int doctor_id, String diseases) {
	public List<Medicalvisit> findBy(String visit_date, String patient_pesel, String doctor_id) {
		String query="select mv from Medicalvisit mv JOIN mv.patient p JOIN mv.doctor d where "
				+ "mv.visit_date like '%"+visit_date+"%' and "
				+ "p.pesel like '%"+patient_pesel+"%' "
				+ "and d.id like '%"+doctor_id+"%' ";//*/
		//if(!visit_date.isEmpty()) query+="and mv.visit_date = '"+visit_date+"'";
		
		Query q = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Medicalvisit> lista=q.getResultList();
		return lista;
	}
	
	public void create(Medicalvisit medicalvisit) {
		System.out.println("Creating Medicalvisit!");
		manager.persist(medicalvisit);
	}

	public Medicalvisit find(int id) {
		return manager.find(Medicalvisit.class, id);
	}

	public void update(Medicalvisit medicalvisit) {
		medicalvisit = manager.merge(medicalvisit);
	}

	public void delete(int id) {
		Medicalvisit medicalvisit = manager.find(Medicalvisit.class, id);
		manager.remove(medicalvisit);
	}
	
}
