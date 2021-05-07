package br.com.learning.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.learning.connection.MyEntityManager;

public class GenericDao<T> {

	private EntityManager em;

	public GenericDao() {
		em = MyEntityManager.getEntityManager();
	}

	public void salvar(T entity) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(entity);
		transaction.commit();
	}

	public T salvarOuAtualizar(T entity) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		entity = em.merge(entity);
		transaction.commit();
		return entity;
	}

	public T encontrarPorId(Class<T> entityClass, Integer id) {
		T entity = em.find(entityClass, id);
		return entity;
	}

	public T encontrarPorEmail(Class<T> entityClass, String email) {
		T entity = em.createQuery("FROM " + entityClass.getName() + " WHERE email = :email", entityClass)
				.setParameter("email", email).getSingleResult();
		return entity;
	}

	public List<T> encontrarTodos(Class<T> entityClass) {
		List<T> finded = em.createQuery("FROM " + entityClass.getName() + " ORDER BY id", entityClass).getResultList();
		return finded;
	}

	public void deletarPorId(Class<T> entityClass, Integer id) {
		T entity = encontrarPorId(entityClass, id);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(entity);
		transaction.commit();
	}
}
