package com.univercity.test;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.univercity.entity.Annonce;
import com.univercity.entity.Utilisateur;
import com.univercity.remote.IAnnonceRemote;
import com.univercity.remote.IUtilisateurRemot;





public class TestJPQL {

	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;	
	
	@EJB
	IUtilisateurRemot userMetier;
	@EJB
	IAnnonceRemote annonceMetier;
	
	
	Logger log = Logger.getLogger("TestJPQL");

	@BeforeClass
	public static void init() throws Exception {
		EJBContainer.createEJBContainer();
		emf = Persistence.createEntityManagerFactory("UP_annonce");		
	}

	@Before
	public void setup() {
		try {
			em = emf.createEntityManager();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testActualiserObjeto() {

		String jpql = null;
		Query q=null;
		List<Utilisateur> listuser = null;
		Utilisateur user = null;
		Iterator iter = null;
		Object[] tupla = null;
		List<String> nombres = null;
		List<Annonce> annonce = null;
		
		EntityTransaction tx1 = null;
		tx1 = em.getTransaction();
		tx1.begin();
	
		
		//2) Consulter la liste des personnes d'ou  id = 1:
		log.debug("\n2) Consulter la liste des personnes d'ou  id = 1:");
		jpql = "select p from Utilisateur p where p.idUtilisateur = 1";
		user = (Utilisateur) em.createQuery(jpql).getSingleResult();
		log.debug(user);
		
		//3) Consulta de la Persona por nombre: 
		log.debug("\n3) Consulter la personne par nom : ");
		jpql = "select p from Utilisateur p where p.nom = 'Youssef'";
		listuser = em.createQuery(jpql).getResultList();
		MontrerUtilisateur( listuser );
		
		
		
		log.debug("\n5) //// ");
		jpql = "select p, p.idUtilisateur from Utilisateur p";
		iter = em.createQuery(jpql).getResultList().iterator();
		while(iter.hasNext()){
			tupla = (Object[]) iter.next();
			user = (Utilisateur) tupla[0];
			int iduser =  (int) tupla[1];
			log.debug("Objet Personne:  " + user);
			log.debug("id user:  " + iduser);
		}

		//6) Consulter tout les utilisateurs: 
		log.debug("\n6) Consulter tout les utilisateurs");
		jpql = "select com.univercity.entity.Utilisateur( p.idUtilisateur) from Utilisateur p";
		listuser = em.createQuery(jpql).getResultList();
		MontrerUtilisateur(listuser);
		
		
		
		//8)Le nombre des personnes qui sont connectés 
		log.debug("\n8) le nombre des personnes qui sont different ");
		jpql = "select count(distinct p.idUtilisateur) from Utilisateur p";
		Long contador = (Long) em.createQuery(jpql).getSingleResult();
		log.debug("no. le nombre des personnes qui sont connectés:" + contador);
		
		//9) Concatener le nom et prenom 
		log.debug("\n9) Concatener le nom et prenom ");
		jpql = "select CONCAT (p.nom, ' ' , p.prenom) as Nombre FROM Utilisateur p";
		nombres= em.createQuery(jpql).getResultList();
		for(String nombreCompleto : nombres){
			log.debug(nombreCompleto);
		}
		
		//10) Un objet qui contient en parametre 1 
		log.debug("\n10) Un objet qui contient en parametre 1 ");
		int iduser = 1;
		jpql = "select p from Utilisateur p where p.idUtilisateur = :id";
		q = em.createQuery(jpql);
		q.setParameter("id", iduser);
		user = (Utilisateur) q.getSingleResult();
		log.debug( user );

		//11) Les gens qui contient la lettre a dependant de majuscules et minuscules 
		log.debug("\n11)Les gens qui contient la lettre a dependant de majuscules et minuscules ");
		jpql = "select p from Utilisateur p where upper(p.nom) like upper(:param1)";
		String cadena ="%a%";
		q = em.createQuery(jpql);
		q.setParameter("param1", cadena);
		listuser = q.getResultList();
		MontrerUtilisateur(listuser);
		
		//12) between: 
		log.debug("\n12) )between: ");
		jpql = "select p from Utilisateur p where p.idUtilisateur between 1 and 2";
		listuser = em.createQuery(jpql).getResultList();
		MontrerUtilisateur(listuser);
		tx1.commit();
	
		//13) Ordre:
		log.debug("\n13)  Ordre:");
		jpql = "select p from Utilisateur p where p.idUtilisateur > 3 order by p.nom desc, p.prenom desc";
		listuser = em.createQuery(jpql).getResultList();
		MontrerUtilisateur(listuser);
		
		//14) ////)
		log.debug("\n14) ////");
		jpql = "select p from Utilisateur p where p.idUtilisateur in ( select min(p1.idUtilisateur) from Utilisateur p1)";
		listuser = em.createQuery(jpql).getResultList();
		MontrerUtilisateur(listuser);
		
		//15) ////: 
		log.debug("\n15) ///");
		jpql = "select u from Annonce u join u.utilisateur p";
		annonce = em.createQuery(jpql).getResultList();
		MontrerAnnonce(annonce);
		
		//16)//////
		log.debug("\n15) ////");
		jpql = "select u from Annonce u left join fetch u.utilisateur";
		annonce = em.createQuery(jpql).getResultList();
		MontrerAnnonce(annonce);
		
		tx1.commit();
		
	}

	private void MontrerUtilisateur(List<Utilisateur> users) {
		for (Utilisateur p : users) {
			log.debug(p);
		}
	}
	@Test
	public void testConsulterPersonne() {
		
		List<Utilisateur> listUser = userMetier.getAllUser();
		Assert.assertNotNull(listUser);
//		tx1.commit();
//		MontrerUtilisateur(listuser);
	}
	
	private void MontrerAnnonce(List<Annonce> annonces) {
		for (Annonce u : annonces) {
			log.debug(u);
		}
	}

	@After
	public void tearDown() throws Exception {
		if (em != null) {
			em.close();
		}
	}

}

