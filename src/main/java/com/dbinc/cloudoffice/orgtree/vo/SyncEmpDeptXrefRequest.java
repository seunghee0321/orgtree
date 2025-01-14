package com.dbinc.cloudoffice.orgtree.vo;

import java.util.List;

public class SyncEmpDeptXrefRequest {

    private String domainId;
    private String companyCd;
    private List<EmpDeptXrefListVO> empDeptXrefList;

    public SyncEmpDeptXrefRequest() {
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public List<EmpDeptXrefListVO> getEmpDeptXrefList() {
        return empDeptXrefList;
    }

    public void setEmpDeptXrefList(List<EmpDeptXrefListVO> empDeptXrefList) {
        this.empDeptXrefList = empDeptXrefList;
    }
}
