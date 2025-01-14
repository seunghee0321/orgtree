package com.dbinc.cloudoffice.orgtree.vo;

import java.util.List;

public class SyncEmpRequest {
    private String domainId;
    private String companyCd;
    private List<EmpMasterVO> empList;

    public SyncEmpRequest() {
    }

    public String getDomainId() {
        return this.domainId;
    }

    public String getCompanyCd() {
        return this.companyCd;
    }

    public List<EmpMasterVO> getEmpList() {
        return this.empList;
    }

    public void setDomainId(final String domainId) {
        this.domainId = domainId;
    }

    public void setCompanyCd(final String companyCd) {
        this.companyCd = companyCd;
    }

    public void setEmpList(final List<EmpMasterVO> empList) {
        this.empList = empList;
    }

}
