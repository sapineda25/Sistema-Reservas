package base.accesoDatos.utils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This implementation uses JPA 2.0 Criteria API.
 * Will not work with JPA versions previous to 2.0 (before 2008) 
 * @author Jaime Chavarriaga
 *
 * @param <T>
 * @param <ID>
 */
public class GenericJpaDAO<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private Class<T> persistentClass;

	private String persistentClassName = null;
	
	@PersistenceContext ( unitName="app_PU" )
	private EntityManager em;

	
	// == constructores
	
	@SuppressWarnings("unchecked")
	public GenericJpaDAO() {
		
		try {
			this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		} catch (Exception e) {
			this.persistentClass = (Class<T>) getClass().getGenericSuperclass();
		}		
	}

	public GenericJpaDAO(EntityManager em) {
		this();
		this.em = em;
	}
	
	public GenericJpaDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public String getPersistentClassName() {
		if (persistentClassName == null) { 
			persistentClassName = getPersistentClass().getName().substring(
					getPersistentClass().getPackage().getName().length()+1);
			
		}
		return persistentClassName;
	}
	
	protected void setEntityManager( EntityManager em ) {
		this.em = em;
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	// == simple queries

	public T findById(ID id) {
		return findById( id, false );
	}
	
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = (T) getEm().find(getPersistentClass(), id);
			getEm().lock(entity, LockModeType.WRITE);
		} else {
			entity = getEm().find(getPersistentClass(), id);
		}

		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		
		// in Hibernate you can use complete name
		//    getPersistentClass().getName()
		// in EclipseLink it is not possible
		
		Query query = getEm().createQuery("select x from " 
				+ getPersistentClassName()
				+ " x ");
		return query.getResultList();
	}

	
	// == operaciones CRUD
	
	public T create(T entity) {
		getEm().persist(entity);
		return entity;
	}
	
    public T update(T entity) {
        return getEm().merge(entity);
    }	

	public void delete(T entity) {
		getEm().remove(entity);
	}

	public void deleteAll() {
		List<T> all = findAll();
		beginTransaction();
		for( T element : all ) {
			delete( element );			
		}
		commit();
	}

	// == Transacciones
	
	public void beginTransaction() {
		// inicia una transaccion
		getEm().getTransaction().begin();
	}
	
	public void commit() {
		// hace commit de la transaccion
		getEm().getTransaction().commit();
	}

	public void rollback() {
		try {
			// hace rollback  de la transaccion
			getEm().getTransaction().rollback();
		} catch (Exception e) {
			// no haga nada
		}
	}	
	
	public void flush() {
		getEm().flush();
	}

	public void clear() {
		getEm().clear();
	}

	
	// == queries support
	
	@SuppressWarnings("unchecked")
	protected T executeSingleResultNamedQuery(String namedQuery) {
		Query consulta = getEm().createNamedQuery(namedQuery);
		T resultado = (T) consulta.getSingleResult();
		return resultado;	
	}

	@SuppressWarnings("unchecked")
	protected T executeSingleResultNamedQuery(String namedQuery, Object... params) {
		Query consulta = getEm().createNamedQuery(namedQuery);
		int paramNumber = 0;
		for(Object param: params) {
			if (param instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) param;
				for(String paramName: map.keySet()) {
					consulta.setParameter(paramName, map.get(paramName));
				}				
			} else {
				consulta.setParameter(paramNumber++, param);
			}
		}
		try {
			T resultado = (T) consulta.getSingleResult();
			return resultado;	
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	@SuppressWarnings("unchecked")
	protected List<T> executeListResultNamedQuery(String namedQuery) {
		Query consulta = getEm().createNamedQuery(namedQuery);
		List<T> resultado = consulta.getResultList();
		return resultado;	
	}

	@SuppressWarnings("unchecked")
	protected List<T> executeListResultNamedQuery(String namedQuery, Object... params) {
		Query consulta = getEm().createNamedQuery(namedQuery);
		int paramNumber = 0;
		for(Object param: params) {
			if (param instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) param;
				for(String paramName: map.keySet()) {
					consulta.setParameter(paramName, map.get(paramName));
				}				
			} else {
				consulta.setParameter(paramNumber++, param);
			}
		}
		try {
			List<T> resultado = consulta.getResultList();
			return resultado;	
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected T executeSingleResultQuery(String query) {
		Query consulta = getEm().createQuery(query);
		T resultado = (T) consulta.getSingleResult();
		return resultado;	
	}

	@SuppressWarnings("unchecked")
	protected T executeSingleResultQuery(String query, Object... params) {
		Query consulta = getEm().createQuery(query);
		int paramNumber = 0;
		for(Object param: params) {
			if (param instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) param;
				for(String paramName: map.keySet()) {
					consulta.setParameter(paramName, map.get(paramName));
				}				
			} else {
				consulta.setParameter(paramNumber++, param);
			}
		}
		try {
			T resultado = (T) consulta.getSingleResult();
			return resultado;	
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> executeListResultQuery(String query) {
		Query consulta = getEm().createQuery(query);
		List<T> resultado = consulta.getResultList();
		return resultado;	
	}

	@SuppressWarnings("unchecked")
	protected List<T> executeListResultQuery(String query, Object... params) {
		Query consulta = getEm().createQuery(query);
		int paramNumber = 0;
		for(Object param: params) {
			if (param instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) param;
				for(String paramName: map.keySet()) {
					consulta.setParameter(paramName, map.get(paramName));
				}				
			} else {
				consulta.setParameter(paramNumber++, param);
			}
		}
		try {
			List<T> resultado = consulta.getResultList();
			return resultado;	
		} catch (NoResultException e) {
			return null;
		}
	}

}
