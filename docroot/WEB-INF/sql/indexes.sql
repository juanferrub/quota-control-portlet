create index IX_44F8DB53 on LSUGQUOTA_Quota (classNameId, classPK);

create index IX_D21CDEFA on LSUGQUOTA_QuotaDailyLog (quotaId);
create index IX_5278DACA on LSUGQUOTA_QuotaDailyLog (quotaId, dayAnalyzed);