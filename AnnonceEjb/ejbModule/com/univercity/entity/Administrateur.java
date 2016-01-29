package com.univercity.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "admin", catalog = "db_annonce", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Administrateur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_admin;
	private String name;
	private String password;
	public Administrateur(){
		
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_admin", unique = true, nullable = false)
	public int getId_admin() {
		return id_admin;
	}
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Administrateur [id_admin=" + id_admin + ", name=" + name
				+ ", password=" + password + "]";
	}

}
