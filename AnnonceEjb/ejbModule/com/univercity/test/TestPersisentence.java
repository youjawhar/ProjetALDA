package com.univercity.test;

import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.univercity.entity.Utilisateur;

public class TestPersisentence {
	
	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	Logger log = Logger.getLogger("Test Persisentence Objet");

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

	@Test
	public void testPersistenciaCascada() {

		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		
		Utilisateur user1=new Utilisateur("Bordeaux", "youssef.jawhar@gmail.com", "Youssef", "Youssef", "JAWHAR", "00000", "user");
		
		
		
		
		
		em.persist(user1);
		
		tx1.commit();
		
		
		log.debug("Objeto recuperado:" + user1);
	}
	
	@After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
    }

}
