package przychodnia;

import java.util.List;

import przychodnia.entities.Doctor;
//import przychodnia.entities.Doctors;

public interface DoctorsInterface2 {
	public abstract void create(Doctor doctor);

	public abstract Doctor find(int id);

	public abstract List<Doctor> get();
	//public abstract Doctors get();

	public abstract void update(Doctor doctor);

	public abstract void delete(int id);
}
