package com.dbinc.cloudoffice.orgtree.vo;

import java.util.List;

public class SyncDeptRequest {

    private String domainId;
    private String companyCd;
    private List<DeptMasterVO> deptList;

    public SyncDeptRequest() {
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

    public List<DeptMasterVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DeptMasterVO> deptList) {
        this.deptList = deptList;
    }
}
