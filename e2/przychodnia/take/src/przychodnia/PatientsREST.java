package przychodnia;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import przychodnia.entities.Patient;
import przychodnia.entities.Patients;



@Path("/patients")

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class PatientsREST implements PatientsInterface {
	@EJB
	PatientsEJB bean;

	@Override
	@GET
	public Patients get() {
		List<Patient> lPatients = bean.get();
		Patients patients = new Patients(lPatients);
		return patients;
	}
	
	@Override
	@GET
	@Path("/search") //?&name=&surname=
	public Patients findBy(
			@QueryParam("name") String name,
			@QueryParam("surname") String surname
	) {
		List<Patient> lPatients = bean.findBy(name, surname);
		Patients patients = new Patients(lPatients);
		return patients;
	}
	
	@Override
	@POST
	public String create(Patient patient) {
		bean.create(patient);
		return "Patient created!";
	}

	@Override
	@GET
	@Path("/{pesel}")
	public Patient find(@PathParam("pesel") BigInteger pesel) {
		Patient patient = bean.find(pesel);
		return patient;
	}

	@Override
	@PUT
	public String update(Patient patient) {
		try {
			bean.update(patient);
			return "patient updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "patient not updated :(";
		}
	}


	@Override
	@DELETE
	@Path("/{pesel}")
	public void delete(@PathParam("pesel") BigInteger pesel) {
		bean.delete(pesel);
	}
	
}
