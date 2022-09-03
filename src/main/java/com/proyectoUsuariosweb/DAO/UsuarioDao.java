package com.proyectoUsuariosweb.DAO;

import java.util.List;

import com.proyectoUsuariosweb.models.Usuario;

public interface UsuarioDao {

	List<Usuario> getUsuarios();

	void eliminar(int id);

	void agregar(Usuario NuevoUsuario);

	Usuario iniciarSesion(Usuario usuario);
	
}
