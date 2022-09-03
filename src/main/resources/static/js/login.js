// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function initSesion(){
	
	//guardara el json convertido a string
	let datos= {}

	datos.password= document.getElementById('txtpassword').value
	datos.mail= document.getElementById('txtEmail').value
	
	
  const request = await fetch('Usuario/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  
   const respuesta = await request.text();
   
   if(respuesta !="FAIL"){
	
	localStorage.token= respuesta;
	localStorage.email= datos.mail;
	window.location.href= "usuarios.html"
	alert("BIENVENIDO")
   }
   else{
	alert("CREDENCIALES INCORRECTAS")
   }

  

  

  

}