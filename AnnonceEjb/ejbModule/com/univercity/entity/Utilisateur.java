package com.univercity.entity;

// Generated 16 d�c. 2015 00:08:23 by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name = "utilisateur", catalog = "db_annonce", uniqueConstraints = @UniqueConstraint(columnNames = "mail"))
public class Utilisateur implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idUtilisateur;
	private String adress;
	private String mail;
	private String nom;
	private String pass;
	private String prenom;
	private String tele;
	private String type;
	private List<Annonce> favorite_user;
	private Set<Annonce> annonces = new HashSet<Annonce>();
	private Set<ImageUser> imagesUser = new HashSet<ImageUser>();
	private Set<Message> messageUser = new HashSet<Message>();

	public Utilisateur() {
	}

	public Utilisateur(String adress, String mail, String nom, String pass,
			String prenom, String tele, String type) {
		this.adress = adress;
		this.mail = mail;
		this.nom = nom;
		this.pass = pass;
		this.prenom = prenom;
		this.tele = tele;
		this.type = type;
		// this.annonces = annonces;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_utilisateur", unique = true, nullable = false)
	public Integer getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	@Column(name = "adress")
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "mail", unique = true)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "nom")
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "pass")
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "prenom")
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "tele")
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Utilisateur(String mail, String pass) {
		super();
		this.mail = mail;
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", adress="
				+ adress + ", mail=" + mail + ", nom=" + nom + ", pass=" + pass
				+ ", prenom=" + prenom + ", tele=" + tele + ", type=" + type
				+ "]";
	}
	
		

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Set<Annonce> getAnnonces() {
		return this.annonces;
	}

	public void setAnnonces(Set<Annonce> annonces) {
		this.annonces = annonces;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
	public Set<ImageUser> getImagesUser() {
		return imagesUser;
	}

	public void setImagesUser(Set<ImageUser> imagesUser) {
		this.imagesUser = imagesUser;
	}

	public Utilisateur cloneUtilisateur() {
		Utilisateur u = new Utilisateur();
		u.adress = this.adress;
		u.mail = this.mail;
		u.nom = this.nom;
		u.pass = this.pass;
		u.prenom = this.prenom;
		u.tele = this.tele;
		u.type = this.type;
		return u;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateurDest")
	public Set<Message> getMessageUser() {
		return messageUser;
	}
	
	

	public void setMessageUser(Set<Message> messageUser) {
		this.messageUser = messageUser;
	}
	
	@ManyToMany(mappedBy="favory")
	public List<Annonce> getFavorite_user() {
		return favorite_user;
	}

	public void setFavorite_user(List<Annonce> favorite_user) {
		this.favorite_user = favorite_user;
	}
}
