package de.taujhe.fsd.core.database;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Utility class for JPA related methods. Also used for bootstrapping access to the JPA entity management.
 *
 * @author Jan Henke (Jan.Henke@taujhe.de)
 */
@Singleton
public class JPAUtil
{
	private static JPAUtil ourInstance = new JPAUtil();

	static JPAUtil getInstance()
	{
		return ourInstance;
	}

	private EntityManagerFactory _entityManagerFactory;

	private JPAUtil()
	{
		_entityManagerFactory = Persistence.createEntityManagerFactory("fsd-database");
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		_entityManagerFactory.close();
		_entityManagerFactory = null;
	}

	EntityManager getEntityManager()
	{
		return _entityManagerFactory.createEntityManager();
	}
}
