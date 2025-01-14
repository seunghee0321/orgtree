package com.dbinc.cloudoffice.orgtree.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyMasterVO {
	public String domainId;
	public String companyCd;
	public String companyNm;
	public String companyEngNm;
	public String languageCd;
	public String useYn;
	public String autoEpSyncYn;
	public String epEmpSyncApiUrl;
	public String epDeptSyncApiUrl;
	public String epEmpDeptXrefSyncApiUrl;
	public String tempDomainId;
	public String apiAuthKey;
	public String updateUsr;
	public String updateDt;
	public String createUsr;
	public String createDt;
}
