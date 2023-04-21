package przychodnia;


import przychodnia.entities.Disease;
import przychodnia.entities.Diseases;

public interface DiseasesInterface {

	public abstract Diseases get();

	public abstract Diseases findBy(String name, String symptoms, String causes, String cure);
	
	public abstract String create(Disease doctor);

	public abstract Disease find(int id);

	public abstract String update(Disease doctor);

	public abstract void delete(int id);
}
