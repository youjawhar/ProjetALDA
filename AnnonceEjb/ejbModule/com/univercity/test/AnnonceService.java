package com.univercity.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;

import com.univercity.entity.Annonce;
import com.univercity.local.IAnnonceLocal;


public class AnnonceService {
	
	private IAnnonceLocal annonceservice;

	@Before
	public void setUp() throws Exception {
		EJBContainer contenedor = EJBContainer.createEJBContainer();
		annonceservice = (IAnnonceLocal) contenedor.getContext().lookup("java:global/ear/AnnonceEjb/utilisateur!com.univercity.local.IUtilisateurLocal");
	}

	@Test
	public void testEJBAnnonceService() {
		System.out.println("Inctencie test EJB IAnnonceLocal");
		
		assertTrue(annonceservice != null);
		
		//assertEquals(3, personaService.listarPersonas().size());
		System.out.println("le nombre des annonces  est :" + annonceservice.getAllAnnoce().size());
		
		this.deloyedannonce(annonceservice.getAllAnnoce());
		
		System.out.println("Fin test EJB  IAnnonceLocal");
	}
	
	private void deloyedannonce(List<Annonce> annonces){
		for (Annonce annonce : annonces) {
			System.out.println(annonce);
		}
	}

}
