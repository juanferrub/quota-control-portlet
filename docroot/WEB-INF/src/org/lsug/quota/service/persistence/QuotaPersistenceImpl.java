/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.lsug.quota.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.lsug.quota.NoSuchQuotaException;
import org.lsug.quota.model.Quota;
import org.lsug.quota.model.impl.QuotaImpl;
import org.lsug.quota.model.impl.QuotaModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the quota service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuotaPersistence
 * @see QuotaUtil
 * @generated
 */
public class QuotaPersistenceImpl extends BasePersistenceImpl<Quota>
	implements QuotaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link QuotaUtil} to access the quota persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = QuotaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() },
			QuotaModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			QuotaModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByClassNameIdClassPK",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID =
		new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByClassNameId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID =
		new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassNameId",
			new String[] { Long.class.getName() },
			QuotaModelImpl.CLASSNAMEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAMEID = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassNameId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTQUOTAID =
		new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentQuotaId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTQUOTAID =
		new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParentQuotaId",
			new String[] { Long.class.getName() },
			QuotaModelImpl.PARENTQUOTAID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTQUOTAID = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByParentQuotaId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, QuotaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the quota in the entity cache if it is enabled.
	 *
	 * @param quota the quota
	 */
	public void cacheResult(Quota quota) {
		EntityCacheUtil.putResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaImpl.class, quota.getPrimaryKey(), quota);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
			new Object[] {
				Long.valueOf(quota.getClassNameId()),
				Long.valueOf(quota.getClassPK())
			}, quota);

		quota.resetOriginalValues();
	}

	/**
	 * Caches the quotas in the entity cache if it is enabled.
	 *
	 * @param quotas the quotas
	 */
	public void cacheResult(List<Quota> quotas) {
		for (Quota quota : quotas) {
			if (EntityCacheUtil.getResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
						QuotaImpl.class, quota.getPrimaryKey()) == null) {
				cacheResult(quota);
			}
			else {
				quota.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all quotas.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(QuotaImpl.class.getName());
		}

		EntityCacheUtil.clearCache(QuotaImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the quota.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Quota quota) {
		EntityCacheUtil.removeResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaImpl.class, quota.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(quota);
	}

	@Override
	public void clearCache(List<Quota> quotas) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Quota quota : quotas) {
			EntityCacheUtil.removeResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
				QuotaImpl.class, quota.getPrimaryKey());

			clearUniqueFindersCache(quota);
		}
	}

	protected void clearUniqueFindersCache(Quota quota) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
			new Object[] {
				Long.valueOf(quota.getClassNameId()),
				Long.valueOf(quota.getClassPK())
			});
	}

	/**
	 * Creates a new quota with the primary key. Does not add the quota to the database.
	 *
	 * @param quotaId the primary key for the new quota
	 * @return the new quota
	 */
	public Quota create(long quotaId) {
		Quota quota = new QuotaImpl();

		quota.setNew(true);
		quota.setPrimaryKey(quotaId);

		return quota;
	}

	/**
	 * Removes the quota with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param quotaId the primary key of the quota
	 * @return the quota that was removed
	 * @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota remove(long quotaId)
		throws NoSuchQuotaException, SystemException {
		return remove(Long.valueOf(quotaId));
	}

	/**
	 * Removes the quota with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the quota
	 * @return the quota that was removed
	 * @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Quota remove(Serializable primaryKey)
		throws NoSuchQuotaException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Quota quota = (Quota)session.get(QuotaImpl.class, primaryKey);

			if (quota == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuotaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(quota);
		}
		catch (NoSuchQuotaException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Quota removeImpl(Quota quota) throws SystemException {
		quota = toUnwrappedModel(quota);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, quota);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(quota);

		return quota;
	}

	@Override
	public Quota updateImpl(org.lsug.quota.model.Quota quota, boolean merge)
		throws SystemException {
		quota = toUnwrappedModel(quota);

		boolean isNew = quota.isNew();

		QuotaModelImpl quotaModelImpl = (QuotaModelImpl)quota;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, quota, merge);

			quota.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !QuotaModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((quotaModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(quotaModelImpl.getOriginalClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
					args);

				args = new Object[] {
						Long.valueOf(quotaModelImpl.getClassNameId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID,
					args);
			}

			if ((quotaModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTQUOTAID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(quotaModelImpl.getOriginalParentQuotaId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTQUOTAID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTQUOTAID,
					args);

				args = new Object[] {
						Long.valueOf(quotaModelImpl.getParentQuotaId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTQUOTAID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTQUOTAID,
					args);
			}
		}

		EntityCacheUtil.putResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
			QuotaImpl.class, quota.getPrimaryKey(), quota);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
				new Object[] {
					Long.valueOf(quota.getClassNameId()),
					Long.valueOf(quota.getClassPK())
				}, quota);
		}
		else {
			if ((quotaModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(quotaModelImpl.getOriginalClassNameId()),
						Long.valueOf(quotaModelImpl.getOriginalClassPK())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
					new Object[] {
						Long.valueOf(quota.getClassNameId()),
						Long.valueOf(quota.getClassPK())
					}, quota);
			}
		}

		return quota;
	}

	protected Quota toUnwrappedModel(Quota quota) {
		if (quota instanceof QuotaImpl) {
			return quota;
		}

		QuotaImpl quotaImpl = new QuotaImpl();

		quotaImpl.setNew(quota.isNew());
		quotaImpl.setPrimaryKey(quota.getPrimaryKey());

		quotaImpl.setQuotaId(quota.getQuotaId());
		quotaImpl.setClassNameId(quota.getClassNameId());
		quotaImpl.setClassPK(quota.getClassPK());
		quotaImpl.setParentQuotaId(quota.getParentQuotaId());
		quotaImpl.setQuotaAssigned(quota.getQuotaAssigned());
		quotaImpl.setQuotaUsed(quota.getQuotaUsed());
		quotaImpl.setQuotaStatus(quota.getQuotaStatus());
		quotaImpl.setQuotaAlert(quota.getQuotaAlert());

		return quotaImpl;
	}

	/**
	 * Returns the quota with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the quota
	 * @return the quota
	 * @throws com.liferay.portal.NoSuchModelException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Quota findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the quota with the primary key or throws a {@link org.lsug.quota.NoSuchQuotaException} if it could not be found.
	 *
	 * @param quotaId the primary key of the quota
	 * @return the quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByPrimaryKey(long quotaId)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByPrimaryKey(quotaId);

		if (quota == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + quotaId);
			}

			throw new NoSuchQuotaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				quotaId);
		}

		return quota;
	}

	/**
	 * Returns the quota with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the quota
	 * @return the quota, or <code>null</code> if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Quota fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the quota with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param quotaId the primary key of the quota
	 * @return the quota, or <code>null</code> if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByPrimaryKey(long quotaId) throws SystemException {
		Quota quota = (Quota)EntityCacheUtil.getResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
				QuotaImpl.class, quotaId);

		if (quota == _nullQuota) {
			return null;
		}

		if (quota == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				quota = (Quota)session.get(QuotaImpl.class,
						Long.valueOf(quotaId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (quota != null) {
					cacheResult(quota);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(QuotaModelImpl.ENTITY_CACHE_ENABLED,
						QuotaImpl.class, quotaId, _nullQuota);
				}

				closeSession(session);
			}
		}

		return quota;
	}

	/**
	 * Returns the quota where classNameId = &#63; and classPK = &#63; or throws a {@link org.lsug.quota.NoSuchQuotaException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByClassNameIdClassPK(long classNameId, long classPK)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByClassNameIdClassPK(classNameId, classPK);

		if (quota == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchQuotaException(msg.toString());
		}

		return quota;
	}

	/**
	 * Returns the quota where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByClassNameIdClassPK(long classNameId, long classPK)
		throws SystemException {
		return fetchByClassNameIdClassPK(classNameId, classPK, true);
	}

	/**
	 * Returns the quota where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByClassNameIdClassPK(long classNameId, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
					finderArgs, this);
		}

		if (result instanceof Quota) {
			Quota quota = (Quota)result;

			if ((classNameId != quota.getClassNameId()) ||
					(classPK != quota.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<Quota> list = q.list();

				result = list;

				Quota quota = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
						finderArgs, list);
				}
				else {
					quota = list.get(0);

					cacheResult(quota);

					if ((quota.getClassNameId() != classNameId) ||
							(quota.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
							finderArgs, quota);
					}
				}

				return quota;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CLASSNAMEIDCLASSPK,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (Quota)result;
			}
		}
	}

	/**
	 * Returns all the quotas where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @return the matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByClassNameId(long classNameId)
		throws SystemException {
		return findByClassNameId(classNameId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the quotas where classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @return the range of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByClassNameId(long classNameId, int start, int end)
		throws SystemException {
		return findByClassNameId(classNameId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the quotas where classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByClassNameId(long classNameId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAMEID;
			finderArgs = new Object[] { classNameId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAMEID;
			finderArgs = new Object[] { classNameId, start, end, orderByComparator };
		}

		List<Quota> list = (List<Quota>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Quota quota : list) {
				if ((classNameId != quota.getClassNameId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				list = (List<Quota>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first quota in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByClassNameId_First(long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByClassNameId_First(classNameId, orderByComparator);

		if (quota != null) {
			return quota;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaException(msg.toString());
	}

	/**
	 * Returns the first quota in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByClassNameId_First(long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Quota> list = findByClassNameId(classNameId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last quota in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByClassNameId_Last(long classNameId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByClassNameId_Last(classNameId, orderByComparator);

		if (quota != null) {
			return quota;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("classNameId=");
		msg.append(classNameId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaException(msg.toString());
	}

	/**
	 * Returns the last quota in the ordered set where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByClassNameId_Last(long classNameId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByClassNameId(classNameId);

		List<Quota> list = findByClassNameId(classNameId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the quotas before and after the current quota in the ordered set where classNameId = &#63;.
	 *
	 * @param quotaId the primary key of the current quota
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota[] findByClassNameId_PrevAndNext(long quotaId,
		long classNameId, OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = findByPrimaryKey(quotaId);

		Session session = null;

		try {
			session = openSession();

			Quota[] array = new QuotaImpl[3];

			array[0] = getByClassNameId_PrevAndNext(session, quota,
					classNameId, orderByComparator, true);

			array[1] = quota;

			array[2] = getByClassNameId_PrevAndNext(session, quota,
					classNameId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Quota getByClassNameId_PrevAndNext(Session session, Quota quota,
		long classNameId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_QUOTA_WHERE);

		query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(classNameId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(quota);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Quota> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the quotas where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @return the matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByParentQuotaId(long parentQuotaId)
		throws SystemException {
		return findByParentQuotaId(parentQuotaId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the quotas where parentQuotaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @return the range of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByParentQuotaId(long parentQuotaId, int start,
		int end) throws SystemException {
		return findByParentQuotaId(parentQuotaId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the quotas where parentQuotaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findByParentQuotaId(long parentQuotaId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTQUOTAID;
			finderArgs = new Object[] { parentQuotaId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTQUOTAID;
			finderArgs = new Object[] {
					parentQuotaId,
					
					start, end, orderByComparator
				};
		}

		List<Quota> list = (List<Quota>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Quota quota : list) {
				if ((parentQuotaId != quota.getParentQuotaId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_PARENTQUOTAID_PARENTQUOTAID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentQuotaId);

				list = (List<Quota>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first quota in the ordered set where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByParentQuotaId_First(long parentQuotaId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByParentQuotaId_First(parentQuotaId,
				orderByComparator);

		if (quota != null) {
			return quota;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentQuotaId=");
		msg.append(parentQuotaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaException(msg.toString());
	}

	/**
	 * Returns the first quota in the ordered set where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByParentQuotaId_First(long parentQuotaId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Quota> list = findByParentQuotaId(parentQuotaId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last quota in the ordered set where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota findByParentQuotaId_Last(long parentQuotaId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = fetchByParentQuotaId_Last(parentQuotaId, orderByComparator);

		if (quota != null) {
			return quota;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentQuotaId=");
		msg.append(parentQuotaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaException(msg.toString());
	}

	/**
	 * Returns the last quota in the ordered set where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota, or <code>null</code> if a matching quota could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota fetchByParentQuotaId_Last(long parentQuotaId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByParentQuotaId(parentQuotaId);

		List<Quota> list = findByParentQuotaId(parentQuotaId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the quotas before and after the current quota in the ordered set where parentQuotaId = &#63;.
	 *
	 * @param quotaId the primary key of the current quota
	 * @param parentQuotaId the parent quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next quota
	 * @throws org.lsug.quota.NoSuchQuotaException if a quota with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Quota[] findByParentQuotaId_PrevAndNext(long quotaId,
		long parentQuotaId, OrderByComparator orderByComparator)
		throws NoSuchQuotaException, SystemException {
		Quota quota = findByPrimaryKey(quotaId);

		Session session = null;

		try {
			session = openSession();

			Quota[] array = new QuotaImpl[3];

			array[0] = getByParentQuotaId_PrevAndNext(session, quota,
					parentQuotaId, orderByComparator, true);

			array[1] = quota;

			array[2] = getByParentQuotaId_PrevAndNext(session, quota,
					parentQuotaId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Quota getByParentQuotaId_PrevAndNext(Session session,
		Quota quota, long parentQuotaId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_QUOTA_WHERE);

		query.append(_FINDER_COLUMN_PARENTQUOTAID_PARENTQUOTAID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentQuotaId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(quota);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Quota> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the quotas.
	 *
	 * @return the quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @return the range of quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the quotas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of quotas
	 * @param end the upper bound of the range of quotas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of quotas
	 * @throws SystemException if a system exception occurred
	 */
	public List<Quota> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Quota> list = (List<Quota>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_QUOTA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_QUOTA;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Quota>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Quota>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the quota where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the quota that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public Quota removeByClassNameIdClassPK(long classNameId, long classPK)
		throws NoSuchQuotaException, SystemException {
		Quota quota = findByClassNameIdClassPK(classNameId, classPK);

		return remove(quota);
	}

	/**
	 * Removes all the quotas where classNameId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByClassNameId(long classNameId) throws SystemException {
		for (Quota quota : findByClassNameId(classNameId)) {
			remove(quota);
		}
	}

	/**
	 * Removes all the quotas where parentQuotaId = &#63; from the database.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByParentQuotaId(long parentQuotaId)
		throws SystemException {
		for (Quota quota : findByParentQuotaId(parentQuotaId)) {
			remove(quota);
		}
	}

	/**
	 * Removes all the quotas from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Quota quota : findAll()) {
			remove(quota);
		}
	}

	/**
	 * Returns the number of quotas where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public int countByClassNameIdClassPK(long classNameId, long classPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEIDCLASSPK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of quotas where classNameId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @return the number of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public int countByClassNameId(long classNameId) throws SystemException {
		Object[] finderArgs = new Object[] { classNameId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CLASSNAMEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of quotas where parentQuotaId = &#63;.
	 *
	 * @param parentQuotaId the parent quota ID
	 * @return the number of matching quotas
	 * @throws SystemException if a system exception occurred
	 */
	public int countByParentQuotaId(long parentQuotaId)
		throws SystemException {
		Object[] finderArgs = new Object[] { parentQuotaId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTQUOTAID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_QUOTA_WHERE);

			query.append(_FINDER_COLUMN_PARENTQUOTAID_PARENTQUOTAID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentQuotaId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTQUOTAID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of quotas.
	 *
	 * @return the number of quotas
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_QUOTA);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the quota persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.lsug.quota.model.Quota")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Quota>> listenersList = new ArrayList<ModelListener<Quota>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Quota>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(QuotaImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = QuotaPersistence.class)
	protected QuotaPersistence quotaPersistence;
	@BeanReference(type = QuotaDailyLogPersistence.class)
	protected QuotaDailyLogPersistence quotaDailyLogPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_QUOTA = "SELECT quota FROM Quota quota";
	private static final String _SQL_SELECT_QUOTA_WHERE = "SELECT quota FROM Quota quota WHERE ";
	private static final String _SQL_COUNT_QUOTA = "SELECT COUNT(quota) FROM Quota quota";
	private static final String _SQL_COUNT_QUOTA_WHERE = "SELECT COUNT(quota) FROM Quota quota WHERE ";
	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSNAMEID_2 = "quota.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_CLASSNAMEIDCLASSPK_CLASSPK_2 = "quota.classPK = ?";
	private static final String _FINDER_COLUMN_CLASSNAMEID_CLASSNAMEID_2 = "quota.classNameId = ?";
	private static final String _FINDER_COLUMN_PARENTQUOTAID_PARENTQUOTAID_2 = "quota.parentQuotaId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "quota.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Quota exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Quota exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(QuotaPersistenceImpl.class);
	private static Quota _nullQuota = new QuotaImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Quota> toCacheModel() {
				return _nullQuotaCacheModel;
			}
		};

	private static CacheModel<Quota> _nullQuotaCacheModel = new CacheModel<Quota>() {
			public Quota toEntityModel() {
				return _nullQuota;
			}
		};
}