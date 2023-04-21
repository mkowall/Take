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

import przychodnia.entities.Doctor;
import przychodnia.entities.Doctors;



@Path("/doctors")

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class DoctorsREST implements DoctorsInterface {
	@EJB
	DoctorsEJB bean;

	@Override
	@GET
	public Doctors get() {
		List<Doctor> ldoctors = bean.get();
		Doctors doctors = new Doctors(ldoctors);
		return doctors;
	}
	
	@Override
	@GET
	@Path("/search") //?login=dr&name=&surname=&specialization=l
	public Doctors findBy(
			@QueryParam("login") String login,
			@QueryParam("name") String name,
			@QueryParam("surname") String surname,
			@QueryParam("specialization") String specialization//*/
	) {
		List<Doctor> ldoctors = bean.findBy(login, name, surname, specialization);
		Doctors doctors = new Doctors(ldoctors);
		return doctors;
	}
	
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
	@PUT
	public String update(Doctor doctor) {
		try {
			bean.update(doctor);
			return "doctor updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "doctor not updated :(";
		}
	}


	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") int id) {
		bean.delete(id);
	}
	
}
