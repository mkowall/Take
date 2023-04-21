package przychodnia;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import przychodnia.entities.Disease;
import przychodnia.entities.Doctor;
import przychodnia.entities.Medicalvisit;
import przychodnia.entities.Patient;


//@Stateless

@Consumes({ "application/xml" })
@Produces({ "application/xml" })
public class TestPrzychodni {
	
	public static TestPrzychodni main_class;
	private String serverURL="http://localhost:8080/take/rest/";
	
	public static void main(String[] args){
		main_class=new TestPrzychodni();
		/*
		 * Dodawanie: dzia³a
		 * Pobieranie listy: dzia³a (filtrowanie te¿)
		 * Usuwanie: dzia³a (ale tylko niepowi¹zanych obiektów)
		 * Edycja: nie dzia³a
		 */
		/*main_class.write_doctors();
		main_class.write_patients();//*
		main_class.write_diseases();//*
		main_class.write_medicalvisits();//*/
		//main_class.edit_disease();//*/
		//main_class.delete_disease();//*/
		
	}
	
	private void edit_disease(){
		Disease tmp=(Disease) main_class.getObject("diseases/6", Disease.class);
		tmp.setName("Test");
		System.out.println(tmp);
		//System.out.println(main_class.createXml(tmp));
		String x=main_class.sendXml("DOPUT", "diseases", main_class.createXml(tmp));
		System.out.println(x);
	}
	
	private void delete_disease(){
		main_class.sendXml("DODELETE", "diseases/4", "");
	}
	
	private String createXml(Object tmp){
		String xmlString="";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(tmp.getClass());
			Marshaller jaxbMarshaller;
			jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(tmp, sw);
			xmlString = sw.toString();
			//System.out.println( xmlString );
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlString;
	}
	
	private String sendXml(String method, String tabName, String xmlString){
		String txt="";
		//String serverUrl = "http://localhost:8080/take/rest/"+tabName;
		try{
			String url = main_class.serverURL+tabName;
			String message = xmlString;
			//System.out.println(method+" "+url );
			
			if(method=="DOGET") txt = HttpHelper.doGet(url);
			else if(method=="DOPOST") txt = HttpHelper.doPost(url, message, "application/xml");
			else if(method=="DOPUT") txt = HttpHelper.doPut(url, message, "application/xml");
			else if(method=="DODELETE") txt = HttpHelper.doDelete(url);
			
			//System.out.println(txt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txt;
	}
	
	private Object getObject(String tab_path,Class clazz){
		Object x=new Object();
		JAXBContext jaxbContext;
		try
		{
		  jaxbContext = JAXBContext.newInstance(clazz);        
		 
		  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		  x = (Object) jaxbUnmarshaller.unmarshal(new StringReader(HttpHelper.doGet(main_class.serverURL+tab_path)));
		}
		catch (JAXBException e) 
		{
		  e.printStackTrace();
		}
		return x;
	}
	
	private void write_diseases(){
		// dodanie z konstruktorem domyœlnym
		main_class.sendXml("DOPOST", "diseases", main_class.createXml(new Disease()));
		
		//dodanie z u¿yciem funkcji set
		Disease tmp=new Disease();
		//tmp.setId(4);
		tmp.setName("Grypa zoladkowa");
		tmp.setSymptoms("Wymioty, biegunka");
		tmp.setCauses("Zarazenie od drugiej osoby");
		tmp.setCure("Nie wiem");
		main_class.sendXml("DOPOST", "diseases", main_class.createXml(tmp));
		//System.out.println(main_class.createXml(tmp));
		
		//dodanie z u¿yciem konstruktorów parametrycznych
		main_class.sendXml("DOPOST", "diseases", main_class.createXml(new Disease("Zlamana noga", "Ogromny bol, opuchlizna, siny kolor", "Gdyby kozka nie skaka³a itd.", "Do gipsu, miesiac L4")));
	}
	
	private void write_doctors(){
		// dodanie z konstruktorem domyœlnym
		main_class.sendXml("DOPOST", "doctors", main_class.createXml(new Doctor()));
		
		//dodanie z u¿yciem funkcji set
		Doctor tmp=new Doctor();
		tmp.setLogin("dr_oetker");
		tmp.setPassword("razdwatrzy");
		tmp.setName("August");
		tmp.setSurname("Oetker");
		tmp.setSpecialization("Ciasta");
		main_class.sendXml("DOPOST", "doctors", main_class.createXml(tmp));
		
		//dodanie z u¿yciem konstruktorów parametrycznych
		main_class.sendXml("DOPOST", "doctors", main_class.createXml(new Doctor("dr_gerard", "trzydwajeden", "Doktor", "Gerard", "Pryncypalki")));
	}

	private void write_patients(){
		// dodanie z konstruktorem domyœlnym
		main_class.sendXml("DOPOST", "patients", main_class.createXml(new Patient()));
		
		//dodanie z u¿yciem funkcji set
		Patient tmp=new Patient();
		tmp.setPesel(new BigInteger("12123434560"));
		tmp.setName("Grzegorz");
		tmp.setSurname("Brzeczyszczykiweicz");
		tmp.setPhone("111 222 333");
		tmp.setEmail("email@test.com");
		tmp.setPassword("haslo1");
		main_class.sendXml("DOPOST", "patients", main_class.createXml(tmp));
		
		//dodanie z u¿yciem konstruktorów parametrycznych
		main_class.sendXml("DOPOST", "patients", main_class.createXml(new Patient(new BigInteger("12123434561"), "Vincent", "van Gogh", "30 03 1853 00", "oduchowiony@gmail.com", "kajmojeucho")));
	}
	
	private void write_medicalvisits(){
		// dodanie z konstruktorem domyœlnym
		main_class.sendXml("DOPOST", "medicalvisits", main_class.createXml(new Medicalvisit()));
		
		//dodanie z u¿yciem funkcji set
		
		Medicalvisit tmp=new Medicalvisit();
		tmp.setDoctor((Doctor) main_class.getObject("doctors/2", Doctor.class));
		tmp.setPatient((Patient) main_class.getObject("patients/12123434560", Patient.class));
		tmp.setDisease((Disease) main_class.getObject("diseases/5", Disease.class));
		tmp.setDisease((Disease) main_class.getObject("diseases/6", Disease.class));//*/
		tmp.setVisit_date(new Date(System.currentTimeMillis()));
		tmp.setRemarks("Opuchlizna, bol");
		tmp.setMedicines("Altacet");
		//System.out.println( tmp.getDoctor().getId() );
		//System.out.println( main_class.createXml(tmp) );
		main_class.sendXml("DOPOST", "medicalvisits", main_class.createXml(tmp));//*/
		
		//dodanie z u¿yciem konstruktorów parametrycznych
		main_class.sendXml("DOPOST", "medicalvisits", main_class.createXml(new Medicalvisit(new Date(System.currentTimeMillis()), "Wysypka", "Masc")));//*/
	}
	
}
