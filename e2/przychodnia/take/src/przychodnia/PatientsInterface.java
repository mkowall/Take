package przychodnia;


import java.math.BigInteger;

import przychodnia.entities.Patient;
import przychodnia.entities.Patients;

public interface PatientsInterface {
	
	public abstract Patients get();

	public abstract Patients findBy(String name, String surname);
	
	public abstract String create(Patient Patient);

	public abstract Patient find(BigInteger pesel);

	public abstract String update(Patient Patient);

	public abstract void delete(BigInteger pesel);
}
