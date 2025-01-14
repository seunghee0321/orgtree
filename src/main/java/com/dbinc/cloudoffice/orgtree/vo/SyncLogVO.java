package com.dbinc.cloudoffice.orgtree.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncLogVO {
	public String tempDomainId;
	public String domainId;
	public String companyCd;
	public String empSyncResult;
	public String deptSyncResult;
	public String empDeptSyncResult;
	public String syncDiv;
	public String createUsr;
}
