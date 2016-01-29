package com.univercity.imp;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.univercity.entity.Annonce;
import com.univercity.entity.Image;
import com.univercity.entity.ImageUser;
import com.univercity.entity.Message;
import com.univercity.entity.Utilisateur;
import com.univercity.local.IAnnonceLocal;
import com.univercity.remote.IAnnonceRemote;

@Stateless(name = "annonce")
public class AnnonceImp implements IAnnonceLocal, IAnnonceRemote {

	@PersistenceContext(name = "UP_annonce")
	private EntityManager em;

	@Override
	public Annonce addAnnonce(Annonce a) {
		return em.merge(a);

	}

	@Override
	public Image addImage(Image a) {
		return em.merge(a);

	}

	@Override
	public void editAnnonce(Annonce a) {
		Annonce an = em.find(Annonce.class, a.getIdAnnonce());
		an.setDescription(a.getDescription());
		an.setPrix(a.getPrix());
		an.setRegion(a.getRegion());
		an.setSurface(a.getSurface());
		an.setTitre(a.getTitre());
		em.persist(an);

	}

	@Override
	public void removAnnonce(int idAnnonce) {
		Annonce an = em.find(Annonce.class, idAnnonce);
		em.remove(an);
	}

	@Override
	public Annonce getAnnonceByID(int idAnnonce) {
		Query req = em
				.createQuery("select a from Annonce a Join a.utilisateur u where a.idAnnonce=:id");
		req.setParameter("id", idAnnonce);
		return (Annonce) req.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Annonce> getAnnoceByIdUser(int idUser) {
		Query req = em
				.createQuery("select a from Annonce a Join a.utilisateur u where u.idUtilisateur=:id");
		req.setParameter("id", idUser);
		return (List<Annonce>) req.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Annonce> getAllAnnoce() {
		Query req = em.createQuery("select a from Annonce a");
		System.out.println("List :" + req.getResultList());
		return req.getResultList();
	}

	@Override
	public List<Annonce> getAllAnnoceBYCritaire(String region, String surface,
			String prixMax, String prixMin) {
		String request = "select a from Annonce a where a.vondu='non'";
		if ( !region.isEmpty() ) {
			request += " and a.region like '" + region + "'";
		}
		if ( !surface.isEmpty()) {
			request += " and a.surface like '" + surface + "'";
		}
		if (!prixMax.isEmpty()) {
			request += " and a.prix <='" + Double.valueOf(prixMax) + "'";
		}
		if (!prixMin.isEmpty()) {
			request += " and a.prix >= '" + Double.valueOf(prixMin) + "'";
		}
		return (List<Annonce>) em.createQuery(request).getResultList();
	}

	@Override
	public ImageUser addImageUser(ImageUser a) {
		return em.merge(a);
	}

	@Override
	public void supprimerAnnonce(Integer idAnnonce) {
		String request = "delete from Annonce where idAnnonce="+idAnnonce;
		em.createQuery(request).executeUpdate();
	}

	
	
	public List<Annonce> findFavorites(int annonce_id) {
		
		
			Query req=em.createNamedQuery("SELECT u FROM Annonce u join  u.favory t where u.idAnnonce = :annonce_id");
			req.setParameter("annonce_id", annonce_id);
			return (List<Annonce>) req.getSingleResult();
	
		
	}
	
	

}
