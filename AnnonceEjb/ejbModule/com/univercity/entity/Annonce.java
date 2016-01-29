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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Annonce generated by hbm2java
 */
@Entity
@Table(name = "annonce", catalog = "db_annonce")
public class Annonce implements java.io.Serializable {

	private Integer idAnnonce;
	private Utilisateur utilisateur;
	private String description;
	private double prix;
	private String region;
	private String surface;
	private String titre;
	private String vondu;
	
	private List<Utilisateur> favory;
    private Set<Image> images = new HashSet<Image>();

	public Annonce() {
	}

	public Annonce(double prix) {
		this.prix = prix;
	}

	public Annonce(Integer id_utilisateur, String description, double prix,
			String region, String surface, String titre, String vondu) {
//		this.id_utilisateur = id_utilisateur;
		this.description = description;
		this.prix = prix;
		this.region = region;
		this.surface = surface;
		this.titre = titre;
		this.vondu = vondu;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_annonce", unique = true, nullable = false)
	public Integer getIdAnnonce() {
		return this.idAnnonce;
	}

	public void setIdAnnonce(Integer idAnnonce) {
		this.idAnnonce = idAnnonce;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUtilisateur")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "prix", nullable = false, precision = 22, scale = 0)
	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Column(name = "region")
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "surface")
	public String getSurface() {
		return this.surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	@Column(name = "titre")
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Column(name = "vondu")
	public String getVondu() {
		return this.vondu;
	}

	public void setVondu(String vondu) {
		this.vondu = vondu;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "annonce")
	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	public List<Utilisateur> getFavory() {
		return favory;
	}

	public void setFavory(List<Utilisateur> favory) {
		this.favory = favory;
	}

	

}
