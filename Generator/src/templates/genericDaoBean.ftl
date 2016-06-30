package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GenericDaoBean<T,ID extends Serializable> {

	private Class<T> entityType;
	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("${persistenceName?lower_case}");
	protected static EntityManager em = emf.createEntityManager();
	
	@SuppressWarnings("unchecked")
	public GenericDaoBean() {
		entityType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Class<T> getEntityType() {
		return entityType;
	}

	public T findById(ID id) {
		T entity;
		entity = em.find(entityType, id);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Query q = em.createQuery("SELECT x from " + entityType.getSimpleName() + " x");
		List<T> result = q.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(String query) {
		Query q = em.createQuery(query);
		List<T> result = q.getResultList();
		return result;
	}

	
	public T persist(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}
	
	public List<T> persist(List<T> entity) {
		em.getTransaction().begin();
		for(T t : entity){
			em.persist(t);
		}
		em.getTransaction().commit();
		return entity;
	}

	public T merge(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		return entity;
	}

	public void remove(T entity) {
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();
		em.remove(entity);
		
	}

	public void flush() {
		em.getTransaction().begin();
		em.flush();
		em.getTransaction().commit();
	}

	public void clear() {
		em.getTransaction().begin();
		em.clear();
		em.getTransaction().commit();
	}

}