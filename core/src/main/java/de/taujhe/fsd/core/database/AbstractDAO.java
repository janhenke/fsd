package de.taujhe.fsd.core.database;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Abstract base class for Java Persistence API(JPA) Data Access Objects(DAOs). Encapsulates all logic for interacting
 * with JPA.
 *
 * @param T type of the database bean
 * @param K type of the database bean's id(key) column
 *
 * @author Jan Henke (Jan.Henke@taujhe.de)
 */
public abstract class AbstractDAO<T, K>
{
	private final EntityManager _entityManager;
	private final Class<T>      _entityClass;

	protected AbstractDAO(final Class<T> entityClass)
	{
		this._entityClass = entityClass;
		this._entityManager = JPAUtil.getInstance().getEntityManager();
	}

	@Override
	protected void finalize() throws Throwable
	{
		super.finalize();
		_entityManager.close();
	}

	@Nullable
	protected abstract T find(final K key);
}
