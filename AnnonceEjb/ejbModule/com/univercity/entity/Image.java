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
@Table(name = "image", catalog = "db_annonce")
public class Image implements Serializable{
	
	
	private int idImage;	
	private Annonce annonce;	
	private String path;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_image", unique = true, nullable = false)
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAnnonce")
	public Annonce getAnnonce() {
		return annonce;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


}
