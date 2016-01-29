package com.univercity.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.univercity.entity.Message;
import com.univercity.local.IMessageLocal;
import com.univercity.remote.IMessageRemote;

@Stateless(name = "message")
public class MessageImp implements IMessageLocal,IMessageRemote{
	@PersistenceContext(name = "UP_annonce")
	private EntityManager em;

	@Override
	public void addMessage(Message message) {
		em.persist(message);
		
	}

	@Override
	public List<Message> getMessageById(int id) {
		Query req = em
				.createQuery("select a from Message a Join a.utilisateurDest u where u.idUtilisateur=:id");
		req.setParameter("id", id);
		return req.getResultList();
	}

	@Override
	public void suprimerMessage(int id) {
		String request = "delete from Message where idMessage="+id;
		em.createQuery(request).executeUpdate();
		
	}
	
	
}
