create table LSUGQUOTA_Quota (
	quotaId LONG not null primary key,
	classNameId LONG,
	classPK LONG,
	parentQuotaId LONG,
	quotaAssigned LONG,
	quotaUsed LONG,
	quotaStatus INTEGER,
	quotaAlert INTEGER
);

create table LSUGQUOTA_QuotaDailyLog (
	quotaDailyLogId LONG not null primary key,
	dayAnalyzed DATE null,
	quotaId LONG,
	quotaAssigned LONG,
	quotaUsed LONG,
	quotaStatus INTEGER
);