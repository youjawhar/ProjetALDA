package com.univercity.commun;

import java.util.HashSet;
import java.util.Set;

import com.univercity.entity.Message;

public class UserDTO {
	private String email;
	private String pasword;
	private int idUtilisateur;
	private Set<Message> messageUser = new HashSet<Message>();
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasword() {
		return pasword;
	}
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public Set<Message> getMessageUser() {
		return messageUser;
	}
	public void setMessageUser(Set<Message> messageUser) {
		this.messageUser = messageUser;
	}
	

}
