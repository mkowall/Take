package przychodnia;


import przychodnia.entities.Medicalvisit;
import przychodnia.entities.Medicalvisits;

public interface MedicalvisitsInterface {
	
	public abstract Medicalvisits get();

	//public abstract Medicalvisits findBy(String visit_date, BigInteger patient_pesel, int doctor_id, String diseases);
	public abstract Medicalvisits findBy(String visit_date, String patient_pesel, String doctor_id);
	
	public abstract String create(Medicalvisit medicalvisit);

	public abstract Medicalvisit find(int id);

	public abstract String update(Medicalvisit medicalvisit);

	public abstract void delete(int id);
}
