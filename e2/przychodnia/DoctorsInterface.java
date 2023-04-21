package przychodnia;


import przychodnia.entities.Doctor;
import przychodnia.entities.Doctors;

public interface DoctorsInterface {
	public abstract String create(Doctor doctor);

	public abstract Doctor find(int id);

	//public abstract List<Doctor> get();
	public abstract Doctors get();

	public abstract String update(Doctor doctor);

	public abstract void delete(int id);
}
