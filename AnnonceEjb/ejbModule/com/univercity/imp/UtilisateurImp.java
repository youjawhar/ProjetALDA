package com.univercity.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.univercity.entity.Utilisateur;
import com.univercity.local.IUtilisateurLocal;
import com.univercity.remote.IUtilisateurRemot;

@Stateless(name="utilisateur")
public class UtilisateurImp implements IUtilisateurLocal,IUtilisateurRemot{

	@PersistenceContext(name="UP_annonce")
	private EntityManager em;
	
    protected EntityManager getEntityManager() {
        return em;
    }
	 
	 	 
	@Override
	public Utilisateur addUser(Utilisateur u) {
		return em.merge(u);
	}

	@Override
	public void editUser(Utilisateur u) {
		Utilisateur us = em.find(Utilisateur.class, u.getIdUtilisateur());
		us.setAdress(u.getAdress());
		us.setMail(u.getMail());
		us.setNom(u.getNom());
		us.setPrenom(u.getPrenom());
		us.setPass(u.getPass());
		us.setTele(u.getTele());
		em.persist(us);
	}

	 

	@Override
	public void removeUser(Utilisateur u) {
		Utilisateur us = em.find(Utilisateur.class, u.getIdUtilisateur());
		em.remove(us);
	}

	@Override
	public Utilisateur getUserById(int id) {
		Utilisateur u = em.find(Utilisateur.class, id);
		return u;
	}

	@Override
	public Utilisateur getUserByMail(String mail) {
		Query req = em.createQuery("select u From Utilisateur u where u.mail = :x");
		req.setParameter("x",mail);
		List<Utilisateur> us = req.getResultList();
		if(us.size()>0){
		System.out.println(us);
		Utilisateur u =us.get(0);
		return u;
		}
		else{
			return null;
		}
	}

	@Override
	public Utilisateur getUserByMailPass(String mail, String pass) {
		Query req = em.createQuery("select u From Utilisateur u where u.mail =:x and u.pass =:ps");
		req.setParameter("x",mail);
		req.setParameter("ps",pass);
		@SuppressWarnings("unchecked")
		List<Utilisateur> us = req.getResultList();
		if(us.size()>0){
			System.out.println(us);
			Utilisateur u =us.get(0);
			return u;
		}
		else{
				return null;
			}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getAllUser() {
		Query req = em.createQuery("select u From Utilisateur u ");
		System.out.println("List :"+req.getResultList());
		return req.getResultList();
	}
	@Override
	public List<Utilisateur> findAllUser() {
		
	    javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	    cq.select(cq.from(Utilisateur.class));
	    return getEntityManager().createQuery(cq).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getAllUsers(){  
        Query query = em.createNativeQuery("select * from Utilisateur",Utilisateur.class);  
        return query.getResultList();  
    } 

}
