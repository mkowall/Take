package przychodnia;

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

import przychodnia.entities.Medicalvisit;
import przychodnia.entities.Medicalvisits;



@Path("/medicalvisits")

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class MedicalvisitsREST implements MedicalvisitsInterface {
	@EJB
	MedicalvisitsEJB bean;

	@Override
	@GET
	public Medicalvisits get() {
		List<Medicalvisit> lMedicalvisits = bean.get();
		Medicalvisits medicalvisits = new Medicalvisits(lMedicalvisits);
		return medicalvisits;
	}
	
	@Override
	@GET
	@Path("/search") //?visit_date=&patient=&doctor=l
	public Medicalvisits findBy(
			@QueryParam("visit_date") String visit_date,
			/*@QueryParam("patient_pesel") String patient_pesel,
			@QueryParam("doctor_id") String doctor_id//*/
			@QueryParam("patient") String patient_pesel,
			@QueryParam("doctor") String doctor_id//*/
	) {
		List<Medicalvisit> lMedicalvisits = bean.findBy(visit_date, patient_pesel, doctor_id);
		Medicalvisits medicalvisits = new Medicalvisits(lMedicalvisits);
		
		return medicalvisits;
	}
	
	@Override
	@POST
	public String create(Medicalvisit medicalvisit) {
		bean.create(medicalvisit);
		return "Medicalvisit created!";
	}

	@Override
	@GET
	@Path("/{id}")
	public Medicalvisit find(@PathParam("id") int id) {
		Medicalvisit medicalvisit = bean.find(id);
		return medicalvisit;
	}

	@Override
	@PUT
	public String update(Medicalvisit medicalvisit) {
		try {
			bean.update(medicalvisit);
			return "Medicalvisit updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Medicalvisit not updated :(";
		}
	}


	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") int id) {
		bean.delete(id);
	}
	
}
