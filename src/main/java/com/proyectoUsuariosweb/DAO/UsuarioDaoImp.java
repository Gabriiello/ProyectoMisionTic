package com.proyectoUsuariosweb.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoUsuariosweb.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


//esta clase se encarga de hacer la conexion y los querys

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Usuario> getUsuarios() {
		//asigno que tipo de consulta se va a hacer
		String query= "FROM Usuario";
		
		//le mando la consulta a la variable encargada
		List <Usuario> ListaUsuarios= entityManager.createQuery(query).getResultList();
		
		return ListaUsuarios;
	}

	@Override
	public void eliminar(int id) {
		Usuario usuario= entityManager.find(Usuario.class, id); //buscamos usuario bd por la primary key
		entityManager.remove(usuario); 
		
	}

	@Override
	public void agregar(Usuario NuevoUsuario) {
		
		entityManager.merge(NuevoUsuario);
		
	}

	@Override
	public Usuario iniciarSesion(Usuario usuario) {
		
		String query= "FROM Usuario WHERE mail= :email";
		List<Usuario> usuarioCorrecto = entityManager.createQuery(query)
		.setParameter("email", usuario.getMail())
		.getResultList();
		
		//encriptador
		Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		
		//obtenemos  la pw de la bd
		String passwordBd= usuarioCorrecto.get(0).getPassword();
		
		//si no se encontro el usuario
		if(usuarioCorrecto.isEmpty()) {
			return null;
		}
		
		
		
		//si corresponden las contraseñas
		
		if(argon2.verify(passwordBd, usuario.getPassword())) {
			return usuarioCorrecto.get(0);
		}
		return null;
	}

	

}
