package com.dbinc.cloudoffice.orgtree.vo;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptMasterVO {
	private String domainId;
	private String companyCd;
	private String companyNm;
	private String deptCd;
	private String deptNm;
	private String deptEngNm;
	private String parentDeptNm;
	private String parentDeptCd;
	private String deptOrd;
	private String deptUseYn;
	private String manualMngYn;
	private String updateUsr;
	private String updateDt;
	private String createUsr;
	private String createDt;
}
