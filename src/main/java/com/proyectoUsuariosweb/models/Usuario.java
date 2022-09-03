package com.proyectoUsuariosweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// tabla a consultar
@Entity
@Table(name="personas")
public class Usuario {

	@Id  //LLave primaria
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id") //indico columna db
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String mail;
	
	@Column(name="cel")
	private String cel;
	
	public Usuario(int id, String nombre, String apellido, String password, String mail, String cel) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.mail = mail;
		this.cel = cel;
	}
	
	
	
	public Usuario() {
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	
	
	
	
	
	
	
}
