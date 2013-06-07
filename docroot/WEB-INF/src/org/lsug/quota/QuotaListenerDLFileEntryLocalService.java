package org.lsug.quota;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceWrapper;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.File;
import java.io.InputStream;

import java.util.Map;

import org.lsug.quota.service.QuotaLocalServiceUtil;

/**
 * Wrapper for DL File modifications
 */
public class QuotaListenerDLFileEntryLocalService
	extends DLFileEntryLocalServiceWrapper {

	public QuotaListenerDLFileEntryLocalService(
		DLFileEntryLocalService dlFileEntryLocalService) {

		super(dlFileEntryLocalService);
	}

	public DLFileEntry addFileEntry(
			long userId, long groupId, long repositoryId, long folderId,
			String sourceFileName, String mimeType, String title,
			String description, String changeLog, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (!QuotaLocalServiceUtil.hasQuota(groupId, userId, size))
			throw new QuotaExceededException();

		DLFileEntry dlFileEntry = super.addFileEntry(
			userId, groupId, repositoryId, folderId, sourceFileName, mimeType,
			title, description, changeLog, fileEntryTypeId, fieldsMap, file, is,
			size, serviceContext);

		QuotaLocalServiceUtil.increaseQuotaUsage(groupId, userId, size);

		return dlFileEntry;
	}

	protected void deleteFileEntry(DLFileEntry dlFileEntry)
			throws PortalException, SystemException {

		super.deleteDLFileEntry(dlFileEntry);

		long dlFileEntryTotalSize =
				QuotaLocalServiceUtil.getDLFileEntryTotalSize(
					dlFileEntry.getFileEntryId());

		QuotaLocalServiceUtil.decreaseQuotaUsage(
				dlFileEntry.getGroupId(), dlFileEntry.getUserId(),
				dlFileEntryTotalSize);
	}

	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = super.getFileEntry(fileEntryId);

		super.deleteDLFileEntry(fileEntryId);

		long dlFileEntryTotalSize =
				QuotaLocalServiceUtil.getDLFileEntryTotalSize(fileEntryId);

		QuotaLocalServiceUtil.decreaseQuotaUsage(
			dlFileEntry.getGroupId(), dlFileEntry.getUserId(),
				dlFileEntryTotalSize);
	}

	public void deleteFileEntry(long userId, long fileEntryId)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = super.getFileEntry(fileEntryId);

		super.deleteFileEntry(userId, fileEntryId);

		long dlFileEntryTotalSize =
				QuotaLocalServiceUtil.getDLFileEntryTotalSize(fileEntryId);

		QuotaLocalServiceUtil.decreaseQuotaUsage(
				dlFileEntry.getGroupId(), dlFileEntry.getUserId(),
				dlFileEntryTotalSize);
	}

	public DLFileEntry updateFileEntry(
			long userId, long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, long fileEntryTypeId,
			Map<String, Fields> fieldsMap, File file, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
			fileEntryId);
		long groupId = fileEntry.getGroupId();

		if (!QuotaLocalServiceUtil.hasQuota(groupId, userId, size))
			throw new QuotaExceededException();

		DLFileEntry dlFileEntry =
			super.updateFileEntry(
				userId, fileEntryId, sourceFileName, mimeType, title,
				description, changeLog, majorVersion, fileEntryTypeId,
				fieldsMap, file, is, size, serviceContext);

		QuotaLocalServiceUtil.increaseQuotaUsage(groupId, userId, dlFileEntry.getSize());

		return dlFileEntry;
	}

}