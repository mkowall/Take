package przychodnia;

public class Test {
	public static void main(String[] args) {
		String serverUrl = "http://localhost:8080/take/doctors";
		try{
			String url = serverUrl;
			String message = "<doctor id=\"0\">"
									+ "<login>oetker</login>"
									+ "<password>123</password>"
									+ "<name>Doktor</name>"
									+ "<surname>Oetker</surname>"
									+ "<specialization>pieczenie ciast</specialization>"
							+ "</doctor>";

			System.out.println("DOPOST "+url );
			String txt = HttpHelper.doPost(url,message,"application/xml");
			System.out.println("-----------------------");
			System.out.println(txt);
			System.out.println("-----------------------");

			
			url = serverUrl;
			System.out.println("DOGET "+url );
			txt = HttpHelper.doGet(url);
			System.out.println("-----------------------");
			System.out.println(txt);
			System.out.println("-----------------------");
		} catch (Exception e) {e.printStackTrace();}
	}
}
