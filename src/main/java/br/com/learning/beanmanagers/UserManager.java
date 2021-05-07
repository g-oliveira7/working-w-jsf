package br.com.learning.beanmanagers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.learning.dao.GenericDao;
import br.com.learning.entities.User;

@ViewScoped
@ManagedBean(name = "userManager", eager = true)
public class UserManager {

	private User user = new User();
	private GenericDao<User> dao = new GenericDao<>();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GenericDao<User> getDao() {
		return dao;
	}

	public void setDao(GenericDao<User> dao) {
		this.dao = dao;
	}
	
	public List<User> getAllUsers() {
		return dao.encontrarTodos(User.class);
	}
	
	public String novo() {
		user = new User();
		return "";
	}
	
	public String salvar() {
		dao.salvarOuAtualizar(user);
		user = new User();
		return "";
	}
	
	public String deletar() {
		dao.deletarPorId(User.class, user.getId());
		user = new User();
		return "";
	}
	
	public boolean validar(String email, String password) {
		List<User> allUsers = getAllUsers();
		boolean validate = false;
		for (User u : allUsers) {
			if (email.equalsIgnoreCase(u.getEmail()) && password.equals(u.getPassword())) {
				validate = true;
				break;
			}
		}
		return validate;
	}
}
