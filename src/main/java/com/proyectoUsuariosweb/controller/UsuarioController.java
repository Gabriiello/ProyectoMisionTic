package com.proyectoUsuariosweb.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoUsuariosweb.DAO.UsuarioDao;
import com.proyectoUsuariosweb.models.Usuario;
import com.proyectoUsuariosweb.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

//controlador pero usando la arquitectura Rest
@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;
	
	@Autowired
	private JWTUtil jwt;
	
	
	private boolean validarToken(String token) {
		
		String idUsuario= jwt.getKey(token);
		
		return idUsuario != null;
	}
	
	@RequestMapping(value="Usuario/{id}")
	public Usuario getUsuario(@PathVariable int id) {
		return new Usuario(1,"Enrique","Panama","enriquillo23","Enriquepan23@gmail.com","3214424242");
	}
	
	@RequestMapping(value="Usuario/tabla")
	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
		
		//si el token no es valido
		if(!validarToken(token)) {
			return null;
		}
		
		
		
		return dao.getUsuarios();
	}
	
	
	@RequestMapping(value="Usuario/TablaDelete/{id}", method= RequestMethod.DELETE)
	public void EliminarUsuario(@PathVariable int id,@RequestHeader(value="Authorization") String token) {
		
		
		//se corta el metodo
		if(!validarToken(token)) {
			return;
		}
		
		dao.eliminar(id);
	}
	
	@RequestMapping(value="Usuario/TablaAdd", method= RequestMethod.POST)
	public void agregarUsuario(@RequestBody Usuario nuevoUsuario) {
		//encriptamos pw
		Argon2 pwEncriptada= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
		
		String textEncrip= pwEncriptada.hash(1, 1024, 1, nuevoUsuario.getPassword());
		//cambiamos por la  pw encriptada
		nuevoUsuario.setPassword(textEncrip);
		dao.agregar(nuevoUsuario);
	}
	
}
