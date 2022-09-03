// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function crearUsuario(){
	
	//guardara el json convertido a string
	let datos= {}
	datos.nombre= document.getElementById('txtNombre').value
	datos.apellido= document.getElementById('txtApellido').value
	datos.password= document.getElementById('txtpassword').value
	datos.mail= document.getElementById('txtEmail').value
	datos.cel= document.getElementById('txtCel').value
	let repeatContra= document.getElementById('txtReapeatPassword').value
	
	if(repeatContra != document.getElementById('txtpassword').value){
		alert("Las contraseñas no coinciden!")
		return
	}
	
  const request = await fetch('Usuario/TablaAdd', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  


  

  

  

}
