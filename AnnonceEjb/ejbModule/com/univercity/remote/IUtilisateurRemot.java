package com.univercity.remote;

import java.util.List;

import javax.ejb.Remote;

import com.univercity.entity.Utilisateur;

@Remote
public interface IUtilisateurRemot {

	public Utilisateur addUser(Utilisateur u);
	public void editUser(Utilisateur u);
	public void removeUser(Utilisateur u);
	public Utilisateur getUserById(int id);
	public Utilisateur getUserByMail(String mail);
	public Utilisateur getUserByMailPass(String mail,String pass);
	public List<Utilisateur> getAllUser();
	public List<Utilisateur> getAllUsers();
	public List<Utilisateur> findAllUser();
	
}
