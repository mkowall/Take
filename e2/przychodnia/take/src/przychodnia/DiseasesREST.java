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

import przychodnia.entities.Disease;
import przychodnia.entities.Diseases;



@Path("/diseases")

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class DiseasesREST implements DiseasesInterface {
	@EJB
	DiseasesEJB bean;

	@Override
	@GET
	public Diseases get() {
		List<Disease> ldoctors = bean.get();
		Diseases diseases = new Diseases(ldoctors);
		return diseases;
	}
	
	@Override
	@GET
	@Path("/search") //?login=dr&name=&surname=&specialization=l
	public Diseases findBy(
			@QueryParam("name") String name,
			@QueryParam("symptoms") String symptoms,
			@QueryParam("causes") String causes,
			@QueryParam("cure") String cure//*/
	) {
		List<Disease> ldisease = bean.findBy(name, symptoms, causes, cure);
		Diseases diseases = new Diseases(ldisease);
		return diseases;
	}

	@Override
	@POST
	public String create(Disease disease) {
		bean.create(disease);
		return "disease created!";
	}

	@Override
	@GET
	@Path("/{id}")
	public Disease find(@PathParam("id") int id) {
		Disease doctor = bean.find(id);
		return doctor;
	}

	@Override
	@PUT
	public String update(Disease disease) {
		System.out.println(disease);
		try {
			bean.update(disease);
			return "disease updated!";
		} catch (Exception e) {
			e.printStackTrace();
			return "disease not updated :(";
		}
	}


	@Override
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") int id) {
		bean.delete(id);
	}
}
