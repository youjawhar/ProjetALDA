package com.univercity.remote;

import java.util.List;

import javax.ejb.Remote;

import com.univercity.entity.Message;
@Remote
public interface IMessageRemote {
	public void addMessage(Message message);
	public  List<Message> getMessageById(int id);
	public void suprimerMessage(int id);
}
