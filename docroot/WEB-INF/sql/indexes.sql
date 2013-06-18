create index IX_9D84DA90 on LSUGQUOTA_Quota (classNameId);
create index IX_44F8DB53 on LSUGQUOTA_Quota (classNameId, classPK);
create index IX_5B3BA625 on LSUGQUOTA_Quota (classPK);
create index IX_45C6F0DB on LSUGQUOTA_Quota (parentQuotaId);

create index IX_D21CDEFA on LSUGQUOTA_QuotaDailyLog (quotaId);
create index IX_5278DACA on LSUGQUOTA_QuotaDailyLog (quotaId, dayAnalyzed);