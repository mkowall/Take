// PATIENTS
 	function get_params_patients(){
 		var params='';
		var name=document.getElementsByName("name")[0].value;
		var surname=document.getElementsByName("surname")[0].value;
		if(name || surname){
			params='/search?name='+name+'&surname='+surname;
		}
		return params;
 	}
 	
 	function get_patients(table){
		var xml=sendAjax(server_url+"patients"+(table ? get_params_patients() : ''), 'GET', '');
 		var patients=read_patients(xml);
 		if(table) load_patients_table(patients);
 		else load_patients_select(patients);
 	}
	
	function read_patients(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		return tab['patients'][0]['patients'];
 	}
 	
 	function load_patients_select(patients){
 		var wzor='<option value="">---</option>';
 		for(var i=0; i<patients.length; i++){
 			//var patient=patients[i];
 			var patient=Object.assign({}, patients[i]);
 			//wzor+='<option value="'+patient.pesel+'">'+patient.name+' '+patient.surname+'</option>';
 			wzor+='<option value="'+patient.pesel+'" data-obj=\''+JSON.stringify(patient)+'\'>'+patient.name+' '+patient.surname+'</option>';
 		}
 		document.getElementsByName('patient')[0].innerHTML=wzor;
 	}
 	
 	function load_patients_table(patients){
 		var wzor='';
 		for(var i=0; i<patients.length; i++){
 			var patient=patients[i];
 			wzor+='<tr><td>'+patient.pesel+'</td><td>'+patient.password+'</td>';
 			wzor+='<td>'+patient.name+'</td><td>'+patient.surname+'</td><td>'+patient.phone+'</td><td>'+patient.email+'</td>';
 			wzor+='<td><span class="linkButton" onclick="edit_patient('+patient.pesel+')">edytuj</span>';
			wzor+='<span class="linkButton" onclick="delete_patient(this, '+patient.pesel+')">usuń</span></td></tr>';
 		}
 		document.getElementById('patients').innerHTML=wzor;
 	}
 	
 	function edit_patient(id){
 		edit_entity_object(id, 'patient');
 	}
 	
 	function delete_patient(element, id){
 		delete_entity_object(element, id, 'patients');
 	}
	
	function save_patient(){
		var patient=create_patient_object();
		var xml=generate_xml(patient, 'patient');
		var xml=sendAjax(server_url+"patients", (patient.patient.pesel ? 'PUT' : 'POST'), xml);
		if(xml=='error') alert("Wystąpił błąd podczas usuwania");
 		else location.href=page_url+'patients.html'; // wraca do widoku głównego
	}
	
	function create_patient_object(){
		//var id=document.getElementsByName("id")[0].value;
		var pesel=document.getElementsByName("pesel")[0].value;
		var name=document.getElementsByName("name")[0].value;
		var surname=document.getElementsByName("surname")[0].value;
		var phone=document.getElementsByName("phone")[0].value;
		var email=document.getElementsByName("email")[0].value;
		var password=document.getElementsByName("password")[0].value;
		var obj={pesel: pesel, name: name, surname: surname, phone: phone, email: email, password: password};
		return {patient: obj};
		//return obj;
	}
	
	function get_patient(pesel){
		var xml=sendAjax(server_url+"patients/"+pesel, 'GET', '');
 		var patient=read_patient(xml);
		//console.log(patient);
 		load_patient(patient);
 	}
	
	function read_patient(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		//console.log(tab);
		return tab['patient'][0];
 	}
	
	function load_patient(obj){
		//document.getElementsByName("id")[0].value=obj.id ? obj.id : null;
		document.getElementsByName("pesel")[0].value=obj.pesel ? obj.pesel : null;
		document.getElementsByName("name")[0].value=obj.name ? obj.name : null;
		document.getElementsByName("surname")[0].value=obj.surname ? obj.surname : null;
		document.getElementsByName("phone")[0].value=obj.phone ? obj.phone : null;
		document.getElementsByName("email")[0].value=obj.email ? obj.email : null;
		document.getElementsByName("password")[0].value=obj.password ? obj.password : null;
		if(obj.pesel){
			document.getElementById('buttons').innerHTML+='<span class="linkButton" onclick="delete_patient(this, '+obj.pesel+')">usuń</span>'
		}
 	}