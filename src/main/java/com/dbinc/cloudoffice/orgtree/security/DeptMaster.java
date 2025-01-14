package com.dbinc.cloudoffice.orgtree.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dept_master")
@IdClass(DeptMasterKey.class)
public class DeptMaster extends BaseTimeEntity
{
  @Id
  @Column(name = "domain_id")
  private String domainId;

  @Id
  @Column(name = "company_cd")
  private String companyCd;

  @Id
  @Column(name = "dept_cd")
  private String deptCd;

  @Column(nullable = false, name = "dept_nm")
  private String deptNm;

  @Column(name = "dept_eng_nm")
  private String deptEngNm;

  @Column(nullable = false, name = "parent_dept_cd")
  private String parentDeptCd;

  @Column(name = "dept_ord")
  private int deptOrd;
  
  @Column(name = "manual_mng_yn")
  private String manualMngYn;

  @Column(name = "update_usr")
  private String updateUsr;

  @Column(name = "create_usr")
  private String createUsr;

  @Builder
  public DeptMaster(String domain_id, String company_cd, String dept_cd, String dept_nm, String dept_eng_nm,
      String parent_dept_cd, int dept_ord, String manual_mng_yn, String update_usr, String create_usr)
  {
    this.domainId     = domain_id;
    this.companyCd    = company_cd;
    this.deptCd       = dept_cd;
    this.deptNm       = dept_nm;
    this.deptEngNm    = dept_eng_nm;
    this.parentDeptCd = parent_dept_cd;
    this.deptOrd      = dept_ord;
    this.manualMngYn  = manual_mng_yn;
    this.updateUsr    = update_usr;
    this.createUsr    = create_usr;
  }
}