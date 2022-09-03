// Call the dataTables jQuery plugin
$(document).ready(function() {
	llamarUsuarios()

  $('#usuarios').DataTable();
  cargarEmailUsuario()
});


function cargarEmailUsuario(){
	document.getElementById("txtEmailUsuario").outerHTML= localStorage.email;
}

async function llamarUsuarios(){
	
  const request = await fetch('Usuario/tabla', {
    method: 'GET',
    headers: cargarHeaders()
    
  });
  const usuarios = await request.json();

  console.log(usuarios);

  
  let tablaLlena= '';
  
  
  //for each PARA iterar la la lista y crear las personas correspondientes
  for(let usuario of usuarios){
	nombreCompleto= usuario.nombre+" "+usuario.apellido
	let btEliminar= '<a href="#" onclick="eliminarUsuarios('+usuario.id+ ')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>'
	let usuarioIterado= '<tr><td>'+usuario.id+'</td><td>'+nombreCompleto+'</td><td>'+usuario.mail+'</td><td>'+usuario.cel+'</td><td>'+btEliminar+'</td></tr>'
	tablaLlena +=usuarioIterado
	
  }

  document.querySelector('#usuarios tbody').outerHTML= tablaLlena

}


function cargarHeaders(){
	return{
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': localStorage.token
    };
}

async function eliminarUsuarios(id){
	
	if(!confirm("Esta seguro de eliminar este usuario")){
		return
	}
	
	const request = await fetch('Usuario/TablaDelete/'+id, {
    method: 'DELETE',
    headers: cargarHeaders()
    
  });
  
  location.reload()
}
