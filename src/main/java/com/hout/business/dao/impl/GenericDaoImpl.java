package com.hout.business.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import com.hout.business.dao.GenericDao;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements
		GenericDao<T, ID> {


	protected Class<T> persistentClass;

	protected final String FINDALL_QUERY;
	protected final String COUNT_ALL_QUERY;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDaoImpl(Class c) {
		persistentClass = c;
		FINDALL_QUERY = "select c from " + persistentClass.getSimpleName()
				+ " c";
		COUNT_ALL_QUERY = "select count(*) from "
				+ persistentClass.getSimpleName() + " c";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected GenericDaoImpl(Class c, String findAllQuery) {
		persistentClass = c;
		FINDALL_QUERY = findAllQuery;
		COUNT_ALL_QUERY = "select count(*) from "
				+ persistentClass.getSimpleName() + " c";
	}

	public GenericDaoImpl() {
		FINDALL_QUERY = null;
		COUNT_ALL_QUERY = null;
	}

	public T findById(ID id) {
		return getEntityManager().find(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query q = getEntityManager().createQuery(FINDALL_QUERY);
		return q.getResultList();
	}

	public Set<T> findAllAsSet() {
		return new HashSet<T>(findAll());
	}

	public void persist(T entity) {
		getEntityManager().persist(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public void remove(Long id) {
		getEntityManager().remove(
				getEntityManager().getReference(persistentClass, id));
	}

	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	public T unique(Collection<T> result) {
		HashSet<T> hashSet = new HashSet<T>(result);
		assert (hashSet.size() <= 1);
		if (hashSet.size() == 0) {
			return null;
		} else {
			return hashSet.iterator().next();
		}
	}

	public void refresh(T detachedInstance) {
		getEntityManager().refresh(detachedInstance);
	}

	public boolean removeById(ID id) {
		T obj = findById(id);
		if (obj != null) {
			remove(obj);
			return true;
		}
		return false;
	}

	public void writeLock(T obj) {
		getEntityManager().lock(obj, LockModeType.WRITE);
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}

	public Long countAll() {
		Query q = getEntityManager().createQuery(COUNT_ALL_QUERY);
		Long count = (Long) q.getSingleResult();
		return count == null ? 0L : count;
	}

	protected abstract EntityManager getEntityManager();

	protected abstract void setEntityManager(EntityManager entityManager);
}