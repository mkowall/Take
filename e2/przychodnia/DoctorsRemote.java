package przychodnia;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;

import przychodnia.entities.Doctor;
import przychodnia.entities.Doctors;

public class DoctorsRemote implements DoctorsInterface2 {

	String url = "http://localhost:8080/take/doctors";

	@Override
	public void create(Doctor doctor) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(doctor, sw);
		HttpHelper.doPost(url,sw.toString(),"application/xml");
	}

	@Override
	public Doctor find(int idc) {
		String txt = HttpHelper.doGet(url+"/"+idc);
		Doctor c = JAXB.unmarshal(new StringReader(txt), Doctor.class);
		return c;
	}


	@Override
	public List<Doctor> get() {
		String txt = HttpHelper.doGet(url);
		Doctors doctors = JAXB.unmarshal(new StringReader(txt), Doctors.class);
		return doctors.getDoctors();
	}

	@Override
	public void update(Doctor doctor) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(doctor, sw);
		HttpHelper.send("PUT",url,sw.toString(),"application/xml");
	}

	@Override
	public void delete(int idc) {
		HttpHelper.send("DELETE",url+"/"+idc,null,"application/xml");
	}


}
