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
@Table(name = "imageUser", catalog = "db_annonce")
public class ImageUser implements Serializable{

	private int idImage;	
	private String path;
	private Utilisateur utilisateur;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_image", unique = true, nullable = false)
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
		
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUtilisateur")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	

}
