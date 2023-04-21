// MEDICALVISITS
 	function get_params_medicalvisits(){
 		var params='';
		var visit_date=document.getElementsByName("visit_date")[0].value;
		//var medicines=document.getElementsByName("medicines")[0].value;
		//var remarks=document.getElementsByName("remarks")[0].value;
		var patient=document.getElementsByName("patient")[0].value;
		var doctor=document.getElementsByName("doctor")[0].value;
		
		if(visit_date || patient || doctor){
			params='/search?visit_date='+visit_date+'&patient='+patient+'&doctor='+doctor;
		}
		return params;
 	}
 	
 	function get_medicalvisits(table){
		var xml=sendAjax(server_url+"medicalvisits"+(table ? get_params_medicalvisits() : ''), 'GET', '');
 		var medicalvisits=read_medicalvisits(xml);
 		//if(table) load_medicalvisits_table(medicalvisits);
 		//else load_medicalvisits_select(medicalvisits);
 		load_medicalvisits_table(medicalvisits);
 	}
	
	function read_medicalvisits(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=new Array();
		tab=read_xml2(xmlDoc);
		return tab['medicalvisits'][0]['medicalvisits'];
 	}
 	
 	/*function load_medicalvisits_select(doctors){
 		var wzor='<option value="">---</option>';
 		for(var i=0; i<medicalvisits.length; i++){
 			var medicalvisit=medicalvisits[i];
 			wzor+='<option value="'+medicalvisit.id+'">'+medicalvisit.date+'</option>';
 		}
 		document.getElementsByName('doctor')[0].innerHTML=wzor;
 	}//*/
 	
 	function load_medicalvisits_table(medicalvisits){
 		var wzor='';
 		for(var i=0; i<medicalvisits.length; i++){
 			var medicalvisit=medicalvisits[i];
 			var doctor=medicalvisit.doctor ? medicalvisit.doctor[0] : {name: '', surname: ''};
 			var patient=medicalvisit.patient ? medicalvisit.patient[0] : {name: '', surname: ''};
 			wzor+='<tr><td>'+medicalvisit.id+'</td><td>'+medicalvisit.visit_date+'</td><td>'+(doctor.id ? doctor.name.charAt(0)+'. '+doctor.surname : '---')+'</td>';
 			wzor+='<td>'+medicalvisit.medicines+'</td><td>'+medicalvisit.remarks+'</td><td>'+(patient.pesel ? patient.name.charAt(0)+'. '+patient.surname : '---')+'</td>';
 			wzor+='<td><span class="linkButton" onclick="edit_medicalvisit('+medicalvisit.id+')">edytuj</span>';
			wzor+='<span class="linkButton" onclick="delete_medicalvisit(this, '+medicalvisit.id+')">usuń</span></td></tr>';
 		}
 		document.getElementById('medicalvisits').innerHTML=wzor;
 	}
 	
 	function edit_medicalvisit(id){
 		edit_entity_object(id, 'medicalvisit');
 	}
 	
 	function delete_medicalvisit(element, id){
 		delete_entity_object(element, id, 'medicalvisits');
 	}
 	
	function save_medicalvisit(){
		var medicalvisit=create_medicalvisit_object();
		var xml=generate_xml(medicalvisit, 'medicalvisit');
		var xml=sendAjax(server_url+"medicalvisits", (medicalvisit.medicalvisit.id ? 'PUT' : 'POST'), xml);
		if(xml=='error') alert("Wystąpił błąd podczas zapisywania");
 		else location.href=page_url+'medicalvisits.html'; // wraca do widoku głównego
	}
	
	function create_medicalvisit_object(){
		var id=document.getElementsByName("id")[0].value;
		var visit_date=document.getElementsByName("visit_date")[0].value;
		var medicines=document.getElementsByName("medicines")[0].value;
		var remarks=document.getElementsByName("remarks")[0].value;
		//var patient=document.getElementsByName("patient")[0].value;
		//var doctor=document.getElementsByName("doctor")[0].value;
		var tmp=document.getElementsByName("patient")[0];
		var patient=JSON.parse(tmp.options[tmp.selectedIndex].getAttribute('data-obj'));
		tmp=document.getElementsByName("doctor")[0];
		var doctor=JSON.parse(tmp.options[tmp.selectedIndex].getAttribute('data-obj'));
		
		var diseases=new Array(); var i=0;
		document.getElementsByName('disease[]').forEach(function(val, j){
			//if(val.checked) diseases[i++]={id: val.value};
			if(val.checked) diseases[i++]=JSON.parse(val.getAttribute('data-obj'));
		});
		//var obj={id: id, visit_date: visit_date, medicines: medicines, remarks: remarks, patient: patient, doctor: doctor};
		var obj={id: id, visit_date: visit_date, medicines: medicines, remarks: remarks, patient: patient, doctor: doctor, diseases: diseases};
		console.log(obj);
		return {medicalvisit: obj};
	}
 	
 	function get_medicalvisit(id){
		var xml=sendAjax(server_url+"medicalvisits/"+id, 'GET', '');
 		var medicalvisit=read_medicalvisit(xml);
 		load_medicalvisit(medicalvisit);
 	}
	
	function read_medicalvisit(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=new Array();
		tab=read_xml2(xmlDoc);
		return tab['medicalvisit'][0];
 	}
 	
 	function load_medicalvisit(obj){
		document.getElementsByName("id")[0].value=obj.id ? obj.id : null;
		document.getElementsByName("visit_date")[0].value=obj.visit_date ? obj.visit_date : null;
		document.getElementsByName("medicines")[0].value=obj.medicines ? obj.medicines : null;
		document.getElementsByName("remarks")[0].value=obj.remarks ? obj.remarks : null;
		document.getElementsByName("patient")[0].value=obj.patient ? obj.patient[0].pesel : null;
		document.getElementsByName("doctor")[0].value=obj.doctor ? obj.doctor[0].id : null;
		
		if(obj.diseases) document.getElementsByName('disease[]').forEach(function(val, i){
			obj.diseases.forEach(function(val2, i2){
				if(val.value==val2.id) val.checked=true;
			});
		});
		
		if(obj.id){
			document.getElementById('buttons').innerHTML+='<span class="linkButton" onclick="delete_medicalvisit(this, '+obj.id+')">usuń</span>'
		}
 	}