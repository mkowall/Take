package przychodnia;


import przychodnia.entities.Doctor;
import przychodnia.entities.Doctors;

public interface DoctorsInterface {
	
	public abstract Doctors get();

	public abstract Doctors findBy(String login, String name, String surname, String specialization);
	
	public abstract String create(Doctor doctor);

	public abstract Doctor find(int id);

	public abstract String update(Doctor doctor);

	public abstract void delete(int id);
}
