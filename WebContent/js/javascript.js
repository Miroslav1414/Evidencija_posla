/*	function izmjeniIncident(x){
		var req = new XMLHttpRequest();
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200){
				console.log('id je ' + x);
			}
		};
		
		req.open("POST","Controller",true);
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.send("action=izmjenaIncidenta_" + x);	


	}
	*/
	
	function checkUsername(){
		console.log("asd");
	var fname = document.getElementById("username");
	
	var letters = /^[A-Za-z.]+$/;
	
	if(fname.value != ""){
		if(fname.value.length < 2){
			fname.setCustomValidity("Korisnicko ime treba da ima od 2 do 200 slova");
		}
		else if (!fname.value.match(letters)){
			fname.setCustomValidity("Ime moze da sadrzai samo slova i .");
		}
		else {
			fname.setCustomValidity("");
	      }
	}
}

function checkImeIPrezime(){
		console.log("asd");
	var fname = document.getElementById("imeIPrezime");
	
	var letters = /^[A-Za-z ]+$/;
	
	if(fname.value != ""){
		if(fname.value.length < 2){
			fname.setCustomValidity("Korisnicko ime treba da ima od 2 do 220 slova");
		}
		else if (!fname.value.match(letters)){
			fname.setCustomValidity("Ime moze da sadrzai samo slova i razmak");
		}
		else {
			fname.setCustomValidity("");
	      }
	}
}
	
	function deleteIncident(x){
		if (confirm('Da li ste sigurni da zelite da obrisete incident?')) {
			var req = new XMLHttpRequest();
			req.onreadystatechange = function(){
				if(req.readyState == 4 && req.status == 200){
					document.getElementById(x).remove();
					console.log('id je ' + x);
				}
			};
			req.open("POST","Controller",true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.send("action=deleteIncident&id=" + x);		  
		  
		}
	}
	
	function updateCompleated(xx){
		var req = new XMLHttpRequest();
		var vrijednost = document.getElementById("newToCompleated" + xx);
		var selected = vrijednost.options[vrijednost.selectedIndex].text;
		
		req.onreadystatechange = function(){
			if(req.readyState == 4 && req.status == 200){
				console.log('id je ' + xx +' vrijednost je' + selected);
			}
		};
		req.open("POST","Controller",true);
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.send("action=updateCompleated&id=" + xx + "&vrijednost=" + selected);	
	}

function checkPassword(){
	var pass = document.getElementById("password");	
	
	
	if(pass == null || pass.value != ""){
		if(pass.value.length < 6){
			pass.setCustomValidity("Lozikna mora da ima od 6 do 32 karaktera");
		}
		else {
			pass.setCustomValidity("");
	      }
	}
} 

function checkSeccondPassword(){
	var pass1 = document.getElementById("password");
	var pass2 = document.getElementById("password2");
	
	if(pass1.value != null && pass2.value !=  null && 
	pass1.value != "" && pass2.value != "" && pass1.value == pass2.value){
		pass2.setCustomValidity("");
		}
	else if (pass2.value == ""){
		pass2.setCustomValidity("");
	      }
	else {
		pass2.setCustomValidity("Lozinke se ne poklapaju");
      }
} 