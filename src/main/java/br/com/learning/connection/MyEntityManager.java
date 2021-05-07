package br.com.learning.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManager {

	private static EntityManagerFactory managerFactory = null;
	private static EntityManager manager;
	
	public static EntityManager getEntityManager() {
		if (managerFactory == null) {
			managerFactory = Persistence.createEntityManagerFactory("learning");
			manager = managerFactory.createEntityManager();
		}
		return manager;
	}
}
