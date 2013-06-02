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
import com.liferay.portal.kernel.util.CalendarUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.lsug.quota.NoSuchQuotaDailyLogException;
import org.lsug.quota.model.QuotaDailyLog;
import org.lsug.quota.model.impl.QuotaDailyLogImpl;
import org.lsug.quota.model.impl.QuotaDailyLogModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistence implementation for the quota daily log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see QuotaDailyLogPersistence
 * @see QuotaDailyLogUtil
 * @generated
 */
public class QuotaDailyLogPersistenceImpl extends BasePersistenceImpl<QuotaDailyLog>
	implements QuotaDailyLogPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link QuotaDailyLogUtil} to access the quota daily log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = QuotaDailyLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_QUOTAID = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED,
			QuotaDailyLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByQuotaId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUOTAID =
		new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED,
			QuotaDailyLogImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByQuotaId", new String[] { Long.class.getName() },
			QuotaDailyLogModelImpl.QUOTAID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUOTAID = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByQuotaId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED,
			QuotaDailyLogImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByQuotaIdDayAnalyzed",
			new String[] { Long.class.getName(), Date.class.getName() },
			QuotaDailyLogModelImpl.QUOTAID_COLUMN_BITMASK |
			QuotaDailyLogModelImpl.DAYANALYZED_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_QUOTAIDDAYANALYZED = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByQuotaIdDayAnalyzed",
			new String[] { Long.class.getName(), Date.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED,
			QuotaDailyLogImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED,
			QuotaDailyLogImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the quota daily log in the entity cache if it is enabled.
	 *
	 * @param quotaDailyLog the quota daily log
	 */
	public void cacheResult(QuotaDailyLog quotaDailyLog) {
		EntityCacheUtil.putResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogImpl.class, quotaDailyLog.getPrimaryKey(),
			quotaDailyLog);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
			new Object[] {
				Long.valueOf(quotaDailyLog.getQuotaId()),
				
			quotaDailyLog.getDayAnalyzed()
			}, quotaDailyLog);

		quotaDailyLog.resetOriginalValues();
	}

	/**
	 * Caches the quota daily logs in the entity cache if it is enabled.
	 *
	 * @param quotaDailyLogs the quota daily logs
	 */
	public void cacheResult(List<QuotaDailyLog> quotaDailyLogs) {
		for (QuotaDailyLog quotaDailyLog : quotaDailyLogs) {
			if (EntityCacheUtil.getResult(
						QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
						QuotaDailyLogImpl.class, quotaDailyLog.getPrimaryKey()) == null) {
				cacheResult(quotaDailyLog);
			}
			else {
				quotaDailyLog.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all quota daily logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(QuotaDailyLogImpl.class.getName());
		}

		EntityCacheUtil.clearCache(QuotaDailyLogImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the quota daily log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(QuotaDailyLog quotaDailyLog) {
		EntityCacheUtil.removeResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogImpl.class, quotaDailyLog.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(quotaDailyLog);
	}

	@Override
	public void clearCache(List<QuotaDailyLog> quotaDailyLogs) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (QuotaDailyLog quotaDailyLog : quotaDailyLogs) {
			EntityCacheUtil.removeResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
				QuotaDailyLogImpl.class, quotaDailyLog.getPrimaryKey());

			clearUniqueFindersCache(quotaDailyLog);
		}
	}

	protected void clearUniqueFindersCache(QuotaDailyLog quotaDailyLog) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
			new Object[] {
				Long.valueOf(quotaDailyLog.getQuotaId()),
				
			quotaDailyLog.getDayAnalyzed()
			});
	}

	/**
	 * Creates a new quota daily log with the primary key. Does not add the quota daily log to the database.
	 *
	 * @param quotaDailyLogId the primary key for the new quota daily log
	 * @return the new quota daily log
	 */
	public QuotaDailyLog create(long quotaDailyLogId) {
		QuotaDailyLog quotaDailyLog = new QuotaDailyLogImpl();

		quotaDailyLog.setNew(true);
		quotaDailyLog.setPrimaryKey(quotaDailyLogId);

		return quotaDailyLog;
	}

	/**
	 * Removes the quota daily log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param quotaDailyLogId the primary key of the quota daily log
	 * @return the quota daily log that was removed
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog remove(long quotaDailyLogId)
		throws NoSuchQuotaDailyLogException, SystemException {
		return remove(Long.valueOf(quotaDailyLogId));
	}

	/**
	 * Removes the quota daily log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the quota daily log
	 * @return the quota daily log that was removed
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuotaDailyLog remove(Serializable primaryKey)
		throws NoSuchQuotaDailyLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			QuotaDailyLog quotaDailyLog = (QuotaDailyLog)session.get(QuotaDailyLogImpl.class,
					primaryKey);

			if (quotaDailyLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQuotaDailyLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(quotaDailyLog);
		}
		catch (NoSuchQuotaDailyLogException nsee) {
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
	protected QuotaDailyLog removeImpl(QuotaDailyLog quotaDailyLog)
		throws SystemException {
		quotaDailyLog = toUnwrappedModel(quotaDailyLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, quotaDailyLog);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(quotaDailyLog);

		return quotaDailyLog;
	}

	@Override
	public QuotaDailyLog updateImpl(
		org.lsug.quota.model.QuotaDailyLog quotaDailyLog, boolean merge)
		throws SystemException {
		quotaDailyLog = toUnwrappedModel(quotaDailyLog);

		boolean isNew = quotaDailyLog.isNew();

		QuotaDailyLogModelImpl quotaDailyLogModelImpl = (QuotaDailyLogModelImpl)quotaDailyLog;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, quotaDailyLog, merge);

			quotaDailyLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !QuotaDailyLogModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((quotaDailyLogModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUOTAID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(quotaDailyLogModelImpl.getOriginalQuotaId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_QUOTAID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUOTAID,
					args);

				args = new Object[] {
						Long.valueOf(quotaDailyLogModelImpl.getQuotaId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_QUOTAID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUOTAID,
					args);
			}
		}

		EntityCacheUtil.putResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
			QuotaDailyLogImpl.class, quotaDailyLog.getPrimaryKey(),
			quotaDailyLog);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
				new Object[] {
					Long.valueOf(quotaDailyLog.getQuotaId()),
					
				quotaDailyLog.getDayAnalyzed()
				}, quotaDailyLog);
		}
		else {
			if ((quotaDailyLogModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(quotaDailyLogModelImpl.getOriginalQuotaId()),
						
						quotaDailyLogModelImpl.getOriginalDayAnalyzed()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_QUOTAIDDAYANALYZED,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
					new Object[] {
						Long.valueOf(quotaDailyLog.getQuotaId()),
						
					quotaDailyLog.getDayAnalyzed()
					}, quotaDailyLog);
			}
		}

		return quotaDailyLog;
	}

	protected QuotaDailyLog toUnwrappedModel(QuotaDailyLog quotaDailyLog) {
		if (quotaDailyLog instanceof QuotaDailyLogImpl) {
			return quotaDailyLog;
		}

		QuotaDailyLogImpl quotaDailyLogImpl = new QuotaDailyLogImpl();

		quotaDailyLogImpl.setNew(quotaDailyLog.isNew());
		quotaDailyLogImpl.setPrimaryKey(quotaDailyLog.getPrimaryKey());

		quotaDailyLogImpl.setQuotaDailyLogId(quotaDailyLog.getQuotaDailyLogId());
		quotaDailyLogImpl.setDayAnalyzed(quotaDailyLog.getDayAnalyzed());
		quotaDailyLogImpl.setQuotaId(quotaDailyLog.getQuotaId());
		quotaDailyLogImpl.setQuotaAssigned(quotaDailyLog.getQuotaAssigned());
		quotaDailyLogImpl.setQuotaUsed(quotaDailyLog.getQuotaUsed());
		quotaDailyLogImpl.setQuotaStatus(quotaDailyLog.getQuotaStatus());

		return quotaDailyLogImpl;
	}

	/**
	 * Returns the quota daily log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the quota daily log
	 * @return the quota daily log
	 * @throws com.liferay.portal.NoSuchModelException if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuotaDailyLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the quota daily log with the primary key or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	 *
	 * @param quotaDailyLogId the primary key of the quota daily log
	 * @return the quota daily log
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog findByPrimaryKey(long quotaDailyLogId)
		throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = fetchByPrimaryKey(quotaDailyLogId);

		if (quotaDailyLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + quotaDailyLogId);
			}

			throw new NoSuchQuotaDailyLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				quotaDailyLogId);
		}

		return quotaDailyLog;
	}

	/**
	 * Returns the quota daily log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the quota daily log
	 * @return the quota daily log, or <code>null</code> if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public QuotaDailyLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the quota daily log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param quotaDailyLogId the primary key of the quota daily log
	 * @return the quota daily log, or <code>null</code> if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog fetchByPrimaryKey(long quotaDailyLogId)
		throws SystemException {
		QuotaDailyLog quotaDailyLog = (QuotaDailyLog)EntityCacheUtil.getResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
				QuotaDailyLogImpl.class, quotaDailyLogId);

		if (quotaDailyLog == _nullQuotaDailyLog) {
			return null;
		}

		if (quotaDailyLog == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				quotaDailyLog = (QuotaDailyLog)session.get(QuotaDailyLogImpl.class,
						Long.valueOf(quotaDailyLogId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (quotaDailyLog != null) {
					cacheResult(quotaDailyLog);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(QuotaDailyLogModelImpl.ENTITY_CACHE_ENABLED,
						QuotaDailyLogImpl.class, quotaDailyLogId,
						_nullQuotaDailyLog);
				}

				closeSession(session);
			}
		}

		return quotaDailyLog;
	}

	/**
	 * Returns all the quota daily logs where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @return the matching quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findByQuotaId(long quotaId)
		throws SystemException {
		return findByQuotaId(quotaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the quota daily logs where quotaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param quotaId the quota ID
	 * @param start the lower bound of the range of quota daily logs
	 * @param end the upper bound of the range of quota daily logs (not inclusive)
	 * @return the range of matching quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findByQuotaId(long quotaId, int start, int end)
		throws SystemException {
		return findByQuotaId(quotaId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the quota daily logs where quotaId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param quotaId the quota ID
	 * @param start the lower bound of the range of quota daily logs
	 * @param end the upper bound of the range of quota daily logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findByQuotaId(long quotaId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_QUOTAID;
			finderArgs = new Object[] { quotaId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_QUOTAID;
			finderArgs = new Object[] { quotaId, start, end, orderByComparator };
		}

		List<QuotaDailyLog> list = (List<QuotaDailyLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (QuotaDailyLog quotaDailyLog : list) {
				if ((quotaId != quotaDailyLog.getQuotaId())) {
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

			query.append(_SQL_SELECT_QUOTADAILYLOG_WHERE);

			query.append(_FINDER_COLUMN_QUOTAID_QUOTAID_2);

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

				qPos.add(quotaId);

				list = (List<QuotaDailyLog>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Returns the first quota daily log in the ordered set where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota daily log
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog findByQuotaId_First(long quotaId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = fetchByQuotaId_First(quotaId,
				orderByComparator);

		if (quotaDailyLog != null) {
			return quotaDailyLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("quotaId=");
		msg.append(quotaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaDailyLogException(msg.toString());
	}

	/**
	 * Returns the first quota daily log in the ordered set where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog fetchByQuotaId_First(long quotaId,
		OrderByComparator orderByComparator) throws SystemException {
		List<QuotaDailyLog> list = findByQuotaId(quotaId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last quota daily log in the ordered set where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota daily log
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog findByQuotaId_Last(long quotaId,
		OrderByComparator orderByComparator)
		throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = fetchByQuotaId_Last(quotaId,
				orderByComparator);

		if (quotaDailyLog != null) {
			return quotaDailyLog;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("quotaId=");
		msg.append(quotaId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchQuotaDailyLogException(msg.toString());
	}

	/**
	 * Returns the last quota daily log in the ordered set where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog fetchByQuotaId_Last(long quotaId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByQuotaId(quotaId);

		List<QuotaDailyLog> list = findByQuotaId(quotaId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the quota daily logs before and after the current quota daily log in the ordered set where quotaId = &#63;.
	 *
	 * @param quotaDailyLogId the primary key of the current quota daily log
	 * @param quotaId the quota ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next quota daily log
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a quota daily log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog[] findByQuotaId_PrevAndNext(long quotaDailyLogId,
		long quotaId, OrderByComparator orderByComparator)
		throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = findByPrimaryKey(quotaDailyLogId);

		Session session = null;

		try {
			session = openSession();

			QuotaDailyLog[] array = new QuotaDailyLogImpl[3];

			array[0] = getByQuotaId_PrevAndNext(session, quotaDailyLog,
					quotaId, orderByComparator, true);

			array[1] = quotaDailyLog;

			array[2] = getByQuotaId_PrevAndNext(session, quotaDailyLog,
					quotaId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected QuotaDailyLog getByQuotaId_PrevAndNext(Session session,
		QuotaDailyLog quotaDailyLog, long quotaId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_QUOTADAILYLOG_WHERE);

		query.append(_FINDER_COLUMN_QUOTAID_QUOTAID_2);

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

		qPos.add(quotaId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(quotaDailyLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<QuotaDailyLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or throws a {@link org.lsug.quota.NoSuchQuotaDailyLogException} if it could not be found.
	 *
	 * @param quotaId the quota ID
	 * @param dayAnalyzed the day analyzed
	 * @return the matching quota daily log
	 * @throws org.lsug.quota.NoSuchQuotaDailyLogException if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog findByQuotaIdDayAnalyzed(long quotaId, Date dayAnalyzed)
		throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = fetchByQuotaIdDayAnalyzed(quotaId,
				dayAnalyzed);

		if (quotaDailyLog == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("quotaId=");
			msg.append(quotaId);

			msg.append(", dayAnalyzed=");
			msg.append(dayAnalyzed);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchQuotaDailyLogException(msg.toString());
		}

		return quotaDailyLog;
	}

	/**
	 * Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param quotaId the quota ID
	 * @param dayAnalyzed the day analyzed
	 * @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog fetchByQuotaIdDayAnalyzed(long quotaId,
		Date dayAnalyzed) throws SystemException {
		return fetchByQuotaIdDayAnalyzed(quotaId, dayAnalyzed, true);
	}

	/**
	 * Returns the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param quotaId the quota ID
	 * @param dayAnalyzed the day analyzed
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching quota daily log, or <code>null</code> if a matching quota daily log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog fetchByQuotaIdDayAnalyzed(long quotaId,
		Date dayAnalyzed, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { quotaId, dayAnalyzed };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
					finderArgs, this);
		}

		if (result instanceof QuotaDailyLog) {
			QuotaDailyLog quotaDailyLog = (QuotaDailyLog)result;

			if ((quotaId != quotaDailyLog.getQuotaId()) ||
					!Validator.equals(dayAnalyzed,
						quotaDailyLog.getDayAnalyzed())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_QUOTADAILYLOG_WHERE);

			query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_QUOTAID_2);

			if (dayAnalyzed == null) {
				query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_1);
			}
			else {
				query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(quotaId);

				if (dayAnalyzed != null) {
					qPos.add(CalendarUtil.getTimestamp(dayAnalyzed));
				}

				List<QuotaDailyLog> list = q.list();

				result = list;

				QuotaDailyLog quotaDailyLog = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
						finderArgs, list);
				}
				else {
					quotaDailyLog = list.get(0);

					cacheResult(quotaDailyLog);

					if ((quotaDailyLog.getQuotaId() != quotaId) ||
							(quotaDailyLog.getDayAnalyzed() == null) ||
							!quotaDailyLog.getDayAnalyzed().equals(dayAnalyzed)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
							finderArgs, quotaDailyLog);
					}
				}

				return quotaDailyLog;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_QUOTAIDDAYANALYZED,
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
				return (QuotaDailyLog)result;
			}
		}
	}

	/**
	 * Returns all the quota daily logs.
	 *
	 * @return the quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the quota daily logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of quota daily logs
	 * @param end the upper bound of the range of quota daily logs (not inclusive)
	 * @return the range of quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the quota daily logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of quota daily logs
	 * @param end the upper bound of the range of quota daily logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<QuotaDailyLog> findAll(int start, int end,
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

		List<QuotaDailyLog> list = (List<QuotaDailyLog>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_QUOTADAILYLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_QUOTADAILYLOG;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<QuotaDailyLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<QuotaDailyLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the quota daily logs where quotaId = &#63; from the database.
	 *
	 * @param quotaId the quota ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByQuotaId(long quotaId) throws SystemException {
		for (QuotaDailyLog quotaDailyLog : findByQuotaId(quotaId)) {
			remove(quotaDailyLog);
		}
	}

	/**
	 * Removes the quota daily log where quotaId = &#63; and dayAnalyzed = &#63; from the database.
	 *
	 * @param quotaId the quota ID
	 * @param dayAnalyzed the day analyzed
	 * @return the quota daily log that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public QuotaDailyLog removeByQuotaIdDayAnalyzed(long quotaId,
		Date dayAnalyzed) throws NoSuchQuotaDailyLogException, SystemException {
		QuotaDailyLog quotaDailyLog = findByQuotaIdDayAnalyzed(quotaId,
				dayAnalyzed);

		return remove(quotaDailyLog);
	}

	/**
	 * Removes all the quota daily logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (QuotaDailyLog quotaDailyLog : findAll()) {
			remove(quotaDailyLog);
		}
	}

	/**
	 * Returns the number of quota daily logs where quotaId = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @return the number of matching quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByQuotaId(long quotaId) throws SystemException {
		Object[] finderArgs = new Object[] { quotaId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_QUOTAID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_QUOTADAILYLOG_WHERE);

			query.append(_FINDER_COLUMN_QUOTAID_QUOTAID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(quotaId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_QUOTAID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of quota daily logs where quotaId = &#63; and dayAnalyzed = &#63;.
	 *
	 * @param quotaId the quota ID
	 * @param dayAnalyzed the day analyzed
	 * @return the number of matching quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByQuotaIdDayAnalyzed(long quotaId, Date dayAnalyzed)
		throws SystemException {
		Object[] finderArgs = new Object[] { quotaId, dayAnalyzed };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_QUOTAIDDAYANALYZED,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_QUOTADAILYLOG_WHERE);

			query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_QUOTAID_2);

			if (dayAnalyzed == null) {
				query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_1);
			}
			else {
				query.append(_FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(quotaId);

				if (dayAnalyzed != null) {
					qPos.add(CalendarUtil.getTimestamp(dayAnalyzed));
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_QUOTAIDDAYANALYZED,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of quota daily logs.
	 *
	 * @return the number of quota daily logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_QUOTADAILYLOG);

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
	 * Initializes the quota daily log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.org.lsug.quota.model.QuotaDailyLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<QuotaDailyLog>> listenersList = new ArrayList<ModelListener<QuotaDailyLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<QuotaDailyLog>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(QuotaDailyLogImpl.class.getName());
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
	private static final String _SQL_SELECT_QUOTADAILYLOG = "SELECT quotaDailyLog FROM QuotaDailyLog quotaDailyLog";
	private static final String _SQL_SELECT_QUOTADAILYLOG_WHERE = "SELECT quotaDailyLog FROM QuotaDailyLog quotaDailyLog WHERE ";
	private static final String _SQL_COUNT_QUOTADAILYLOG = "SELECT COUNT(quotaDailyLog) FROM QuotaDailyLog quotaDailyLog";
	private static final String _SQL_COUNT_QUOTADAILYLOG_WHERE = "SELECT COUNT(quotaDailyLog) FROM QuotaDailyLog quotaDailyLog WHERE ";
	private static final String _FINDER_COLUMN_QUOTAID_QUOTAID_2 = "quotaDailyLog.quotaId = ?";
	private static final String _FINDER_COLUMN_QUOTAIDDAYANALYZED_QUOTAID_2 = "quotaDailyLog.quotaId = ? AND ";
	private static final String _FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_1 = "quotaDailyLog.dayAnalyzed IS NULL";
	private static final String _FINDER_COLUMN_QUOTAIDDAYANALYZED_DAYANALYZED_2 = "quotaDailyLog.dayAnalyzed = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "quotaDailyLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No QuotaDailyLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No QuotaDailyLog exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(QuotaDailyLogPersistenceImpl.class);
	private static QuotaDailyLog _nullQuotaDailyLog = new QuotaDailyLogImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<QuotaDailyLog> toCacheModel() {
				return _nullQuotaDailyLogCacheModel;
			}
		};

	private static CacheModel<QuotaDailyLog> _nullQuotaDailyLogCacheModel = new CacheModel<QuotaDailyLog>() {
			public QuotaDailyLog toEntityModel() {
				return _nullQuotaDailyLog;
			}
		};
}