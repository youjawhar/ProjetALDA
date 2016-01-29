package com.univercity.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "message", catalog = "db_annonce")
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9065606923644473432L;
	
	private int idMessage;
	private String emailSender;
	private String nomSender;
	private String phone;	
	private String message;
	private int idAnnonce;
	private Utilisateur utilisateurDest;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_message", unique = true, nullable = false)
	public int getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	@Column(name = "email_sender")
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUtilisateur")
	public Utilisateur getUtilisateurDest() {
		return utilisateurDest;
	}
	public void setUtilisateurDest(Utilisateur utilisateurDest) {
		this.utilisateurDest = utilisateurDest;
	}
	@Column(name = "nom_sender")
	public String getNomSender() {
		return nomSender;
	}
	public void setNomSender(String nomSender) {
		this.nomSender = nomSender;
	}
	@Column(name = "idAnnonce")
	public int getIdAnnonce() {
		return idAnnonce;
	}
	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}
	
	
	
	
	
	
}
