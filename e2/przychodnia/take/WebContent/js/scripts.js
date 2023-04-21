	const server_url="http://localhost:8080/take/rest/";
	const page_url="http://localhost:8080/take/";
	
	function sendAjax(url, method, xml){
		var response;
		xmlHttp=new XMLHttpRequest();
		xmlHttp.onload=function(){
			//response=this.responseText;
			if(xmlHttp.status>=400) response="error";
			else response=this.responseText;
		};
		xmlHttp.open(method, url, false);
	    xmlHttp.setRequestHeader('Content-Type', 'application/xml');
		xmlHttp.send(xml);
		return response;
	}
 	
 	/*function get_params(){
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
 	}//*/
 	
	function generate_xml1(obj, entity){
		var xml='<'+entity+'>';
		Object.keys(obj).forEach(el => {
			xml+='<'+el+'>'+obj[el]+'</'+el+'>';
		});
		xml+='</'+entity+'>';
		return xml;
	}
	
	function generate_xml(obj){
		var xml='';
		Object.keys(obj).forEach(el => {
			if(typeof obj[el] !== 'object'){
				xml+='<'+el+'>'+obj[el]+'</'+el+'>';
			}else if(Array.isArray(obj[el])){
				obj[el].forEach(function(val, i){
					xml+='<'+el+'>';
					xml+=generate_xml(val);
					xml+='</'+el+'>';
				});
			}else{
				xml+='<'+el+'>';
				if(obj[el]!=null && Object.keys(obj[el]).length>0) xml+=generate_xml(obj[el]);
				xml+='</'+el+'>';
			}
		});
		return xml;
	}
	
	/*function read_xml(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
		var tab=new Array();
		var i=0;
 		for(let el of xmlDoc.children[0].children){
 			var obj=new Object;
 			for(let el2 of el.children){
 				obj[el2.localName]=el2.innerHTML;
 			}
			tab[i++]=obj;
		}
		return tab;
 	}//*/
 	
	function read_xml2(xmlDoc){
		if(!xmlDoc) return;
		var tab=new Array();
		var i_tab=new Array();
		var name='';
 		if(xmlDoc.children) for(var j=0; j<xmlDoc.children.length; j++){
 			name=xmlDoc.children[j].localName.toLowerCase();
 			if(xmlDoc.children && xmlDoc.children[j].children.length>0){
 				if(!i_tab[name]){
 					i_tab[name]=0;
 					tab[name]=new Array();
 				}
 				tab[name][i_tab[name]++]=read_xml2(xmlDoc.children[j]);
 			}
 			else tab[name]=xmlDoc.children[j].innerHTML;
 		}
 		return tab;
 	}
	
	/*function read_xml_single(xml){
		if(!xml) return;
 		var parser = new DOMParser();
		var xmlDoc = parser.parseFromString(xml,"text/xml");
 		var obj=new Object;
 		for(let el of xmlDoc.children[0].children){
			obj[el.localName]=el.innerHTML; // tu pobiera stringa
 		}
		return obj;
 	}//*/
 	
 	function edit_entity_object(id, entity){
 		//if(id) window.open(page_url+'disease.html?id='+id); // otwiera w nowym oknie
 		if(id && entity){
 			//console.log(entity, entity=='patient');
 			if(entity=='patient') location.href=page_url+entity+'.html?pesel='+id;
 			else location.href=page_url+entity+'.html?id='+id;
 		}
 	}
 	
 	function delete_entity_object(element, id, entity){
 		if(confirm("Na pewno chcesz usunąć?")){
			/*var xml=sendAjax(server_url+entity+"/"+id, 'DELETE', '');
 			if(xml=='error') alert("Wystąpił błąd podczas usuwania");
 			else element.parentElement.parentElement.remove();//*/ // usuwa wiersz z tabeli
			var xml=sendAjax(server_url+entity+"/"+id, 'DELETE', '');
			if(xml=='error') alert("Wystąpił błąd podczas usuwania");
 			else location.href=page_url+entity+'.html'; // wraca do widoku głównego
 		}
 	}