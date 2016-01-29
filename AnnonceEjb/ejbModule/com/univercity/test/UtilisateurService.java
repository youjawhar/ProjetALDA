package com.univercity.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ejb.embeddable.EJBContainer;

import org.junit.Before;
import org.junit.Test;


import com.univercity.entity.Utilisateur;

import com.univercity.remote.IUtilisateurRemot;


public class UtilisateurService {
	private IUtilisateurRemot userservice;

	@Before
	public void setUp() throws Exception {
		EJBContainer contenedor = EJBContainer.createEJBContainer();
		userservice = (IUtilisateurRemot) contenedor.getContext().lookup("java:global/ear/AnnonceEjb/utilisateur!com.univercity.remote.IUtilisateurRemot");
	}

	@Test
	public void testEJBUserService() {
		System.out.println("Inctencie test EJB IUtilisateurRemot");
		
		assertTrue(userservice != null);
		
		//assertEquals(3, personaService.listarPersonas().size());
		System.out.println("le nombre des utilisateurs  est :" + userservice.getAllUser().size());
		
		this.deloyeduser(userservice.getAllUser());
		
		System.out.println("Fin test EJB  IUtilisateurRemot");
	}
	
	private void deloyeduser(List<Utilisateur> users){
		for (Utilisateur user : users) {
			System.out.println(user);
		}
	}

}
