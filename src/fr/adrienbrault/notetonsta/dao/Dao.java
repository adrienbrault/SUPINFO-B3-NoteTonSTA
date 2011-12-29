package fr.adrienbrault.notetonsta.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

abstract class Dao<T, ID extends Serializable> {
	
	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public Dao(EntityManager entityManager) {
		this.entityManager = entityManager;
		
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}
	
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}
	
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	public T findById(ID id) {
		return entityManager.find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T>findAll() {
		return entityManager.createQuery("SELECT a FROM " + entityClass.getName() + " AS a").getResultList();
	}
	
	public Long countAll() {
		return (Long) entityManager.createQuery("SELECT COUNT(a) FROM " + entityClass.getName() + " AS a").getSingleResult();
	}
	
}
