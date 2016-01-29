package com.univercity.local;

import java.util.List;

import javax.ejb.Local;

import com.univercity.entity.Annonce;
import com.univercity.entity.Image;
import com.univercity.entity.ImageUser;
import com.univercity.entity.Message;

@Local
public interface IAnnonceLocal {

	public Annonce addAnnonce(Annonce a);

	public Image addImage(Image a);

	public ImageUser addImageUser(ImageUser a);

	public void editAnnonce(Annonce a);

	public void removAnnonce(int idAnnonce);

	public Annonce getAnnonceByID(int idAnnonce);

	public List<Annonce> getAnnoceByIdUser(int idUser);

	public List<Annonce> getAllAnnoce();

	public List<Annonce> getAllAnnoceBYCritaire(String region, String surface,
			String prixMax, String prixMin);
	public void supprimerAnnonce(Integer idAnnonce);
	
}
