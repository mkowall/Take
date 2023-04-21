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

import przychodnia.entities.Doctor;
import przychodnia.entities.Doctors;



@Path("/doctors")

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class DoctorsREST implements DoctorsInterface {
	@EJB
	DoctorsEJB bean;
	
	@Override
	@POST
	public String create(Doctor doctor) {
		bean.create(doctor);
		return "doctor created!";
	}

	@Override
	@GET
	@Path("/{id}")
	public Doctor find(@PathParam("id") int id) {
		Doctor doctor = bean.find(id);
		return doctor;
	}

	@Override
	@GET
	public Doctors get() {
		List<Doctor> ldoctors = bean.get();
		Doctors doctors = new Doctors(ldoctors);
		return doctors;
	}

	@Override
	@PUT
	public String update(Doctor car) {
		try {
			bean.update(car);
			return "car updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "car not updated :(";
		}
	}


	@Override
	@DELETE
	@Path("/{idc}")
	public void delete(@PathParam("idc") int idc) {
		bean.delete(idc);
	}
	
}
