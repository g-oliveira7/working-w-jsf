package br.com.learning.beanmanagers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.learning.dao.GenericDao;
import br.com.learning.entities.User;

@RequestScoped
@ManagedBean(name = "logManager", eager = true)
public class LogManager {

	private User user = new User();
	private GenericDao<User> dao = new GenericDao<>();

	public LogManager() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String validar() {
		User finded = dao.encontrarPorEmail(User.class, user.getEmail());
		String redirect;
		if (finded != null && finded.getPassword().equals(user.getPassword())) {
			redirect = "main?faces-redirect=true";
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("sessionUser", finded);
		} else {
			redirect = "";
		}
		return redirect;
	}
	
	public String sair() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("sessionUser");
		return "login?faces-redirect=true";
	}
	
	public String getWelcome() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		User user = (User) externalContext.getSessionMap().get("sessionUser");
		return "Bem-vindo, " + user.getName() + "!";
	}
}
