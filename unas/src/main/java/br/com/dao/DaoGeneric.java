package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

// criando metodo dao - usado para colocar todos os metodos para acesso ao banco de dados

public class DaoGeneric<QL> {
	
	public void salva(QL entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	
	// merge-update 
	public QL merge(QL entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		QL retorno = entityManager.merge(entidade);
		
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
	}
	/* delete
	public void delete(QL entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.remove(entidade);
		entityTransaction.commit();
		entityManager.close();
	}
	*/
	// delete por ID
	
	public void deletePorId(QL entidade){
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Object id = JPAUtil.getPrimaryKey(entidade);
		entityManager.createQuery("delete from " + entidade.getClass().getCanonicalName() + " where id = " + id).executeUpdate();
		
		entityTransaction.commit();
		entityManager.close();
	}
	//criando lista de dados
	public List<QL> getListEntity(Class<QL> entidade){
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<QL> retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
		entityTransaction.commit();
		entityManager.close();
		
		return retorno;
		
	}
	

}
