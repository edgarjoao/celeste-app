package com.condominium.common.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

/**
 * @author Edgar Joao
 * 
 */
@Repository
public abstract class GenericDAO {

	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	@PersistenceContext	
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	@PersistenceUnit
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
