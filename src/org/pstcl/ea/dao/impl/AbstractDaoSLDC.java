package org.pstcl.ea.dao.impl;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
@Transactional(value="sldcTxnManager")
public abstract class AbstractDaoSLDC<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDaoSLDC(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

//	@Autowired
//	public AbstractDaoSLDC(EntityManagerFactory factory) {
//		if(factory.unwrap(SessionFactory.class) == null){
//			throw new NullPointerException("factory is not a hibernate factory");
//		}
//		this.sessionFactory = factory.unwrap(SessionFactory.class);
//		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//		
//	}


	@Autowired 
	@Qualifier("sldcSessionFactory")
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {


		getSession().persist(entity);
	}
	
	public void saveOrUpdate(T entity) {


		getSession().saveOrUpdate(entity);
	}




	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}


}
