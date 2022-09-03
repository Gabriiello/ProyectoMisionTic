package com.proyectoUsuariosweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoUsuariosweb.DAO.UsuarioDao;
import com.proyectoUsuariosweb.models.Usuario;
import com.proyectoUsuariosweb.utils.JWTUtil;

@RestController
public class AuthController {

	@Autowired
	private UsuarioDao dao;
	
	@Autowired
	private JWTUtil jwt;
	
	@RequestMapping(value="Usuario/login")
	public String IniciarSesion(@RequestBody Usuario usuario) {
		
		Usuario usuarioLoggueado= dao.iniciarSesion(usuario);
		
		if(usuarioLoggueado != null) { //SI hay un usuaio correcto
			String token= jwt.create(String.valueOf(usuarioLoggueado.getId()), String.valueOf(usuarioLoggueado.getMail()));
			
			return token; //enviamos token
		}
		
		
		return "FAIL";
		
		
		
	}
	
}
