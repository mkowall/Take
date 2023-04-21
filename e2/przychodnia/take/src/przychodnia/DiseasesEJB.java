package przychodnia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import przychodnia.entities.Disease;


@Stateless
public class DiseasesEJB {

	@PersistenceContext(name="przychodnia")
	EntityManager manager;
	
	public void create(Disease disease) {
		System.out.println(disease);
		System.out.println("Creating disease!");
		manager.persist(disease);
	}

	public void delete(int id) {
		System.out.println("Deleting disease!");
		Disease disease = manager.find(Disease.class, id);
		manager.remove(disease);
	}

	public List<Disease> findBy(String name, String symptoms, String causes, String cure) {
		System.out.println("Find diseases!");
		String query="select d from Disease d where "
				+ "d.name like '%"+name+"%' "
				+ "and d.symptoms like '%"+symptoms+"%' "
				+ "and d.causes like '%"+causes+"%' "
				+ "and d.cure like '%"+cure+"%'";
		
		Query q = manager.createQuery(query);
		@SuppressWarnings("unchecked")
		List<Disease> lista =q.getResultList();
		return lista;
	}

	public Disease find(int id) {
		return manager.find(Disease.class, id);
	}

	public List<Disease> get() {
		System.out.println("Get diseases!");
		Query q = manager.createQuery("select c from Disease c");
		@SuppressWarnings("unchecked")
		List<Disease> list = q.getResultList();
		return list;
	}

	public void update(Disease disease) {
		System.out.println("Updating disease!");
		//System.out.println(disease);
		disease = manager.merge(disease);;
		//System.out.println(disease);
	}
	
}
