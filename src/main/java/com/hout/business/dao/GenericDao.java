package com.hout.business.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface GenericDao<E, ID extends Serializable> {

	public void persist(E transientInstance);

	public void remove(E persistentInstance);

	public E merge(E detachedInstance);

	public E findById(ID id);

	public List<E> findAll();

	public Set<E> findAllAsSet();

	public void refresh(E detachedInstance);

	public boolean removeById(ID id);

	public void writeLock(E obj);

	public void flush();

	public void clear();
	
	public Long countAll();

}