package com.dbinc.cloudoffice.orgtree.mapper.config;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.EmpConfigVO;

@Repository
@Mapper
public interface EmpConfigMapper
{
  EmpConfigVO selectEmpConfig(Map<String, Object> paramsMap);
  int insertEmpConfig(EmpConfigVO empConfigVO);
  int insertEmpConfigInfo(Map<String, Object> paramsMap);
  int deleteEmpConfig(EmpConfigVO empConfigVO);
  int deleteEmpConfigInfo(Map<String, Object> paramsMap);
  int updateEmpConfig(Map<String, Object> paramsMap);
  int insertUpdateEmpConfigInfo(Map<String, Object> paramsMap);
  int updateEmpConfigXref(Map<String, Object> paramsMap);
  int deleteEmpConfigFromSync(Map<String, Object> paramsMap);
}
