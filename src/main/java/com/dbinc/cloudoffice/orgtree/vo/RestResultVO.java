package com.dbinc.cloudoffice.orgtree.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResultVO
{
  private String resultMsg;
  private String resultCode;
  private List<?> data;
  private Object dataOne;
  private String page;
  private String total;
  private String records;
  private int queryResult;
  private String link;
}
