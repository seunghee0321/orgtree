package com.dbinc.cloudoffice.orgtree.security;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompanyMasterKey implements Serializable
{ 
  private static final long serialVersionUID = 1L;
  private String domainId;
  private String companyCd;
}
