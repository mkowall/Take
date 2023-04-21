// DISEASES
 	function get_params_diseases(){
 		var params='';
		var name=document.getElementsByName("name")[0].value;
		var symptoms=document.getElementsByName("symptoms")[0].value;
		var causes=document.getElementsByName("causes")[0].value;
		var cure=document.getElementsByName("cure")[0].value;
		if(name || symptoms || causes || cure){
			//String login, String name, String surname, String specialization
			params='/search?name='+name+'&symptoms='+symptoms+'&causes='+causes+'&cure='+cure;
		}
		return params;
 	}
 	
 	function get_diseases(table){
		var xml=sendAjax(server_url+"diseases"+(table ? get_params_diseases() : ''), 'GET', '');
 		var diseases=read_diseases(xml);
 		if(table) load_diseases_table(diseases);
 		//else load_diseases_select(diseases);
 		else load_diseases_checkboxes(diseases);
 	}
 	
 	function read_diseases(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		return tab['diseases'][0]['diseases'];
 	}
 	
 	/*function load_diseases_select(diseases){
 		var wzor='<option value="">---</option>';
 		for(var i=0; i<diseases.length; i++){
 			var disease=diseases[i];
 			var disease=Object.assign({}, diseases[i]);
 			//wzor+='<option value="'+disease.id+'">'+disease.name+'</option>';
 			wzor+='<option value="'+disease.id+'" data-obj=\''+JSON.stringify(disease)+'\'>'+disease.name+'</option>';
 		}
 		document.getElementsByName('disease')[0].innerHTML=wzor;
 	}//*/
 	
 	function load_diseases_checkboxes(diseases){
 		var wzor='';
 		for(var i=0; i<diseases.length; i++){
 			//var disease=diseases[i];
 			var disease=Object.assign({}, diseases[i]);
 			//wzor+='<div><input type="checkbox" name="disease[]" value="'+disease.id+'" /> '+disease.name+'</div>';
 			wzor+='<div><input type="checkbox" name="disease[]" value="'+disease.id+'" data-obj=\''+JSON.stringify(disease)+'\' /> '+disease.name+'</div>';
 		}
 		document.getElementById('diseases').innerHTML=wzor;
 	}
 	
 	function load_diseases_table(diseases){
 		var wzor='';
 		for(var i=0; i<diseases.length; i++){
 			var disease=diseases[i];
 			wzor+='<tr><td>'+disease.id+'</td><td>'+disease.name+'</td><td>'+disease.symptoms+'</td>';
 			wzor+='<td>'+disease.causes+'</td><td>'+disease.cure+'</td>';
 			wzor+='<td><span class="linkButton" onclick="edit_disease('+disease.id+')">edytuj</span>';
			wzor+='<span class="linkButton" onclick="delete_disease(this, '+disease.id+')">usuń</span></td></tr>';
 		}
 		document.getElementById('diseases').innerHTML=wzor;
 	}
 	
 	function edit_disease(id){
 		edit_entity_object(id, 'disease');
 	}
 	
 	function delete_disease(element, id){
 		delete_entity_object(element, id, 'diseases');
 	}
 	
	function save_disease(){
		var disease=create_disease_object();
		var xml=generate_xml(disease, 'disease');
		//console.log(xml);
		var xml=sendAjax(server_url+"diseases", (disease.disease.id ? 'PUT' : 'POST'), xml);
		if(xml=='error') alert("Wystąpił błąd podczas usuwania");
 		else location.href=page_url+'diseases.html'; // wraca do widoku głównego
	}
	
	function create_disease_object(){
		var id=document.getElementsByName("id")[0].value;
		var name=document.getElementsByName("name")[0].value;
		var symptoms=document.getElementsByName("symptoms")[0].value;
		var causes=document.getElementsByName("causes")[0].value;
		var cure=document.getElementsByName("cure")[0].value;
		var obj={id: id, name: name, symptoms: symptoms, causes: causes, cure: cure};
		return {disease: obj};
		//return obj;
	}
	
	function get_disease(id){
		var xml=sendAjax(server_url+"diseases/"+id, 'GET', '');
 		var disease=read_disease(xml);
 		load_disease(disease);
 	}
 	
	function read_disease(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=read_xml2(xmlDoc);
		//console.log(tab);
		return tab['disease'][0];
 	}
 	
	function load_disease(obj){
		document.getElementsByName("id")[0].value=obj.id ? obj.id : null;
		document.getElementsByName("name")[0].value=obj.name ? obj.name : null;
		document.getElementsByName("symptoms")[0].value=obj.symptoms ? obj.symptoms : null;
		document.getElementsByName("causes")[0].value=obj.causes ? obj.causes : null;
		document.getElementsByName("cure")[0].value=obj.cure ? obj.cure : null;
		if(obj.id){
			document.getElementById('buttons').innerHTML+='<span class="linkButton" onclick="delete_disease(this, '+obj.id+')">usuń</span>'
		}
 	}