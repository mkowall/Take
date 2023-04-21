<%
response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
response.setHeader("Access-Control-Allow-Headers", "content-type");
response.setHeader("Access-Control-Allow-Credentials", "true");
response.setHeader("Access-Control-Max-Age", "10");

if(request.getCookies()!=null)
for(Cookie c:request.getCookies())
	out.println(c.getName()+"="+c.getValue());
%>
ala ma kota