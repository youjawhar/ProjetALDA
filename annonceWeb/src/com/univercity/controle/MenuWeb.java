package com.univercity.controle;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="menuWeb")
@SessionScoped
public class MenuWeb {

	public String index(){
		
		return "index?faces-redirect=true";
	}
	public String mesAnnonce(){
		return "mesAnnonces?faces-redirect=true";
	}
	public String allUsers(){
		return "allUsers?faces-redirect=true";
	}
	public String profil(){
		return "profil?faces-redirect=true";
	}	
	public String cnx(){
		return "cnx?faces-redirect=true";
	}
	public String addUser(){
		return "SignUp?faces-redirect=true";
	}
	public String addAnnonce(){
		return "addAnnonce?faces-redirect=true";
	}
	public String editUser(){
		return "editUser?faces-redirect=true";
	}
	public String allAnnonces() {
		
		return "allAnnonces?faces-redirect=true";
	}
	public String showAnnonce(){
		return "showAnnonce?faces-redirect=true";
	}
	public String editAnnonce(){
		return "editAnnonce?faces-redirect=true";
	}
	
	public String mesMessages(){
		return "message?faces-redirect=true";
	}
	
	public String contact(){
		return "contact?faces-redirect=true";
	}
	
	

}
