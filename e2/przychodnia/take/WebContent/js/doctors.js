// DOCTORS
 	function get_params_doctors(){
 		var params='';
		var login=document.getElementsByName("login")[0].value;
		var name=document.getElementsByName("name")[0].value;
		var surname=document.getElementsByName("surname")[0].value;
		var specialization=document.getElementsByName("specialization")[0].value;
		if(login || name || surname || specialization){
			//String login, String name, String surname, String specialization
			params='/search?login='+login+'&name='+name+'&surname='+surname+'&specialization='+specialization;
		}
		return params;
 	}
 	
 	function get_doctors(table){
		var xml=sendAjax(server_url+"doctors"+(table ? get_params_doctors() : ''), 'GET', '');
 		var doctors=read_doctors(xml);
 		if(table) load_doctors_table(doctors);
 		else load_doctors_select(doctors);
 	}
	
	function read_doctors(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		return tab['doctors'][0]['doctors'];
 	}
 	
 	function load_doctors_select(doctors){
 		var wzor='<option value="">---</option>';
 		for(var i=0; i<doctors.length; i++){
 			//var doctor=doctors[i];
 			var doctor=Object.assign({}, doctors[i]);
 			//console.log(doctor, JSON.stringify(doctor));
 			//wzor+='<option value="'+doctor.id+'">'+doctor.name+' '+doctor.surname+'</option>';
 			wzor+='<option value="'+doctor.id+'" data-obj=\''+JSON.stringify(doctor)+'\'>'+doctor.name+' '+doctor.surname+'</option>';
 		}
 		document.getElementsByName('doctor')[0].innerHTML=wzor;
 	}
 	
 	function load_doctors_table(doctors){
 		var wzor='';
 		//console.log(medicalvisits);
 		for(var i=0; i<doctors.length; i++){
 			var doctor=doctors[i];
 			wzor+='<tr><td>'+doctor.id+'</td><td>'+doctor.login+'</td><td>'+doctor.password+'</td>';
 			wzor+='<td>'+doctor.name+'</td><td>'+doctor.surname+'</td><td>'+doctor.specialization+'</td>';
 			wzor+='<td><span class="linkButton" onclick="edit_doctor('+doctor.id+')">edytuj</span>';
			wzor+='<span class="linkButton" onclick="delete_doctor(this, '+doctor.id+')">usuń</span></td></tr>';
 		}
 		document.getElementById('doctors').innerHTML=wzor;
 	}
 	
 	function edit_doctor(id){
 		edit_entity_object(id, 'doctor');
 	}
 	
 	function delete_doctor(element, id){
 		delete_entity_object(element, id, 'doctors');
 	}
 	
	function save_doctor(){
		var doctor=create_doctor_object();
		var xml=generate_xml(doctor, 'doctor');
		var xml=sendAjax(server_url+"doctors", (doctor.doctor.id ? 'PUT' : 'POST'), xml);
		if(xml=='error') alert("Wystąpił błąd podczas usuwania");
 		else location.href=page_url+'doctors.html'; // wraca do widoku głównego
	}
	
	function create_doctor_object(){
		var id=document.getElementsByName("id")[0].value;
		var login=document.getElementsByName("login")[0].value;
		var password=document.getElementsByName("password")[0].value;
		var name=document.getElementsByName("name")[0].value;
		var surname=document.getElementsByName("surname")[0].value;
		var specialization=document.getElementsByName("specialization")[0].value;
		var obj={id: id, login: login, password: password, name: name, surname: surname, specialization: specialization};
		return {doctor: obj};
		//return obj;
	}
 	
 	function get_doctor(id){
		var xml=sendAjax(server_url+"doctors/"+id, 'GET', '');
 		var doctor=read_doctor(xml);
 		load_doctor(doctor);
 	}
 	
 	function read_doctor(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		//console.log(tab);
		return tab['doctor'][0];
 	}
 	
 	function load_doctor(obj){
		document.getElementsByName("id")[0].value=obj.id ? obj.id : null;
		document.getElementsByName("login")[0].value=obj.login ? obj.login : null;
		document.getElementsByName("password")[0].value=obj.password ? obj.password : null;
		document.getElementsByName("name")[0].value=obj.name ? obj.name : null;
		document.getElementsByName("surname")[0].value=obj.surname ? obj.surname : null;
		document.getElementsByName("specialization")[0].value=obj.specialization ? obj.specialization : null;
		if(obj.id){
			document.getElementById('buttons').innerHTML+='<span class="linkButton" onclick="delete_doctor(this, '+obj.id+')">usuń</span>'
		}
 	}