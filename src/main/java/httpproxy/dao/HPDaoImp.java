package httpproxy.dao;

import httpproxy.domain.HttpProxy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("hpDao")
public class HPDaoImp implements HPDao {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void updateLastValidDateTime(HttpProxy hp) {

	}

	public void updatelastInvalidDateTime(HttpProxy hp) {

	}

	public HttpProxy getByHostAndPort(String host, int port) {
		Query q = entityManager
				.createQuery("from HttpProxy as hp where hp.host=:host and hp.port = :port");
		q.setParameter("host", host);
		q.setParameter("port", port);
		
		@SuppressWarnings("unchecked")
		List<HttpProxy> hp = (List<HttpProxy>) q.getResultList();
		if (hp.size() == 1) {
			return hp.get(0);
		}
		return null;
	}

	public void saveHttpProxy(HttpProxy hp) {
		entityManager.persist(hp);
	}
}
