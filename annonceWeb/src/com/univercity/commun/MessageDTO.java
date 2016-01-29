package com.univercity.commun;

import com.univercity.entity.Message;
import com.univercity.entity.Utilisateur;

public class MessageDTO {
	
	private int idMessage;
	private String emailSender;
	private String phone;	
	private String nomSender;
	private String message;
	private int idAnnonce;
	private Utilisateur utilisateurDest;
	private Message mesage;
	public int getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Utilisateur getUtilisateurDest() {
		return utilisateurDest;
	}
	public void setUtilisateurDest(Utilisateur utilisateurDest) {
		this.utilisateurDest = utilisateurDest;
	}
	public String getNomSender() {
		return nomSender;
	}
	public void setNomSender(String nomSender) {
		this.nomSender = nomSender;
	}
	public int getIdAnnonce() {
		return idAnnonce;
	}
	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}
	public Message getMesage() {
		return mesage;
	}
	public void setMesage(Message mesage) {
		this.mesage = mesage;
	}
	
	

}
