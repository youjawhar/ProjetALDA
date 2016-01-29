package com.univercity.local;

import java.util.List;

import javax.ejb.Local;

import com.univercity.entity.Utilisateur;

@Local
public interface IUtilisateurLocal {

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
