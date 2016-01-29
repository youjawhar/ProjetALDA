package com.univercity.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.univercity.commun.AnnonceDTO;
import com.univercity.commun.MessageDTO;
import com.univercity.commun.UserDTO;
import com.univercity.entity.Annonce;
import com.univercity.entity.Image;
import com.univercity.entity.ImageUser;
import com.univercity.entity.Message;
import com.univercity.entity.Utilisateur;
import com.univercity.remote.IAnnonceRemote;
import com.univercity.remote.IMessageRemote;
import com.univercity.remote.IUtilisateurRemot;

@ManagedBean(name = "web")
@SessionScoped
public class WEB {

	private UploadedFile file;

	@EJB
	IUtilisateurRemot userMetier;
	@EJB
	IAnnonceRemote annonceMetier;
	@EJB
	IMessageRemote messageMetier;
	Utilisateur userBean = new Utilisateur();
	Utilisateur useradd = userBean.cloneUtilisateur();

	AnnonceDTO annonceDto = new AnnonceDTO();
	UserDTO userDto = new UserDTO();

	Annonce annonceBean = new Annonce();
	List<Utilisateur> usersList = new ArrayList<Utilisateur>();
	List<Annonce> annonceList = new ArrayList<Annonce>();
	private boolean connexion = false;
	String mailUser, passUser;
	MenuWeb menu = new MenuWeb();

	MessageDTO messageDto;

	private final static String DESTINATION = "images/upload/";

	List<String> listImgUpload;

	private boolean disabledDetail = false;

	private FileUploadEvent event;

	public boolean isConnexion() {
		return connexion;
	}

	public void setConnexion(boolean connexion) {
		this.connexion = connexion;
	}

	public String deconnecter() {
		setConnexion(false);
		return menu.index();
	}

	public String mesMessages() {
		return "message?faces-redirect=true";
	}

	public String sendMessage() {
		Message message = new Message();
		message.setEmailSender(messageDto.getEmailSender());
		message.setPhone(messageDto.getPhone());
		message.setMessage(messageDto.getMessage());
		message.setNomSender(messageDto.getNomSender());
		message.setIdAnnonce(messageDto.getIdAnnonce());
		Annonce ann = this.getAnnonceByID(messageDto.getIdAnnonce());
		Utilisateur user = ann.getUtilisateur();
		message.setUtilisateurDest(user);
		messageMetier.addMessage(message);
		return menu.index();
	}

	public String contact(int idAnnonce) {
		messageDto = new MessageDTO();
		messageDto.setIdAnnonce(idAnnonce);
		if (userBean != null) {
			messageDto.setEmailSender(userBean.getMail());
			messageDto.setPhone(userBean.getTele());
			messageDto.setNomSender(userBean.getNom() + " "
					+ userBean.getPrenom());
		}
		return menu.contact();
	}

	public String rechercheAvecCritaire() {
		annonceList = annonceMetier.getAllAnnoceBYCritaire(
				annonceDto.getRegion(), annonceDto.getSurface(),
				annonceDto.getPrixMax(), annonceDto.getPrixMin());
		return menu.allAnnonces();
	}

	public String connexion() {
		
		userBean = userMetier.getUserByMailPass(userDto.getEmail(),
				userDto.getPasword());
		if (userBean != null) {
			setConnexion(true);
			return menu.index();
		} else {
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "erreur";
		}
	}

	public Utilisateur getUserBean() {
		return userBean;
	}

	public void setUserBean(Utilisateur userBean) {
		this.userBean = userBean;
	}

	public List<Utilisateur> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Utilisateur> usersList) {
		this.usersList = usersList;
	}

	public String getMailUser() {
		return mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getPassUser() {
		return passUser;
	}

	public void setPassUser(String passUser) {
		this.passUser = passUser;
	}

	public List<Utilisateur> allUser() {
		return userMetier.getAllUser();
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public List<Annonce> allAnnonces() {
		return annonceMetier.getAllAnnoce();
	}

	
	public List<Message> allMessages(){
		UserDTO userdto=new UserDTO();
		userdto.setIdUtilisateur(userBean.getIdUtilisateur());
		return messageMetier.getMessageById(userdto.getIdUtilisateur());
		

//		
	}

	public String showAnnonce(Integer idAnnonce) {
		annonceBean = this.getAnnonceByID(idAnnonce);
		disabledDetail = true;
		return "showAnnonce?faces-redirect=true";
	}

	public Annonce getAnnonceByID(Integer idAnnonce) {

		return annonceMetier.getAnnonceByID(idAnnonce);
	}

	public String userById(int idUser) {
		setUserBean(userMetier.getUserById(idUser));
		return menu.editUser();
	}

	public String editUser() {
		userMetier.editUser(useradd);
		System.out.println(useradd.toString());
		disabledDetail = false;
		return menu.allUsers();
	}

	public String editAnnonce() {
		annonceMetier.editAnnonce(annonceBean);
		System.out.println(annonceBean.toString());
		return menu.mesAnnonce();
	}

	public String addUser() throws InterruptedException, IOException {
		Utilisateur useradd = userBean.cloneUtilisateur();
		userBean = userMetier.addUser(useradd);
		if (file != null) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
					"yyyy-MM-DD-HH-mm-ss");
			String date = DATE_FORMAT.format(new Date());
			copyFile(date + file.getFileName(), file.getInputstream());
			ImageUser img = new ImageUser();
			img.setPath(DESTINATION + date + file.getFileName());
			img.setUtilisateur(userBean);
			annonceMetier.addImageUser(img);
		}
		userDto.setEmail(useradd.getMail());
		userDto.setPasword(useradd.getPass());
		return connexion();
	}

	public String editAnnonce(Integer idAnnonce) {
		annonceBean = this.getAnnonceByID(idAnnonce);
		return menu.editAnnonce();
	}

	public String addAnnonce() throws IOException {

		annonceBean.setVondu("Non");
		annonceBean.setUtilisateur(userBean);
		annonceBean = annonceMetier.addAnnonce(annonceBean);
		for (String obj : listImgUpload) {
			Image img = new Image();
			img.setPath(obj);
			img.setAnnonce(annonceBean);
			annonceMetier.addImage(img);
		}
		listImgUpload = new ArrayList<String>();
		return menu.mesAnnonce();
	}

	public String addAnnonceView() {
		annonceBean = new Annonce();
		return menu.addAnnonce();
	}

	public String supprimerAnnonce(Integer idAnnonce) {
		annonceMetier.supprimerAnnonce(idAnnonce);
		return menu.mesAnnonce();
	}
	public String supprimerMessage(int id){
		messageMetier.suprimerMessage(id);
		return menu.mesMessages();
	}
	

	public List<Annonce> getAnnonceList() {
		return annonceList;
	}

	public void setAnnonceList(List<Annonce> annonceList) {
		this.annonceList = annonceList;
	}

	public List<Annonce> mesAnnonce() {
		return annonceMetier.getAnnoceByIdUser(userBean.getIdUtilisateur());

	}

	public Annonce getAnnonceBean() {
		return annonceBean;
	}

	public void setAnnonceBean(Annonce annonceBean) {
		this.annonceBean = annonceBean;
	}

	// ---------------------------------upload----------------------------------------------------
	private String destination = "D:\\Master GL 2\\ADLA\\AnnonceJEEv1\\webejb19\\annonceWeb\\WebContent\\Vues\\images\\upload\\";

	public void upload(FileUploadEvent event) throws InterruptedException {
		
		try {
			Date yourDate = new Date();

			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
					"yyyy-MM-DD-HH-mm-ss");
			String date = DATE_FORMAT.format(yourDate);
			listImgUpload.add(DESTINATION + date
					+ event.getFile().getFileName());
			copyFile(date + event.getFile().getFileName(), event.getFile()
					.getInputstream());
			// hna fin t3ayet liha ;)
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void copyFile(String fileName, InputStream in)
			throws InterruptedException {
		try {

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// -----------------------------------fin
	// upload------------------------------------------------------------------------

	public FileUploadEvent getEvent() {
		return event;
	}

	public void setEvent(FileUploadEvent event) {
		this.event = event;
	}

	public List<String> getListImgUpload() {
		if (listImgUpload == null)
			listImgUpload = new ArrayList<String>();
		return listImgUpload;
	}

	public void setListImgUpload(List<String> listImgUpload) {
		this.listImgUpload = listImgUpload;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public AnnonceDTO getAnnonceDto() {
		return annonceDto;
	}

	public void setAnnonceDto(AnnonceDTO annonceDto) {
		this.annonceDto = annonceDto;
	}

	public boolean isDisabledDetail() {
		return disabledDetail;
	}

	public void setDisabledDetail(boolean disabledDetail) {
		this.disabledDetail = disabledDetail;
	}

	public MessageDTO getMessageDto() {
		return messageDto;
	}

	public void setMessageDto(MessageDTO messageDto) {
		this.messageDto = messageDto;
	}
}
