package com.univercity.local;

import java.util.List;

import javax.ejb.Local;

import com.univercity.entity.Message;
@Local
public interface IMessageLocal {
	
	public void addMessage(Message message);
	public  List<Message> getMessageById(int id);
	public void suprimerMessage(int id);
}
