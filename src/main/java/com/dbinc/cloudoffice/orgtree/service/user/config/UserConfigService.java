package com.dbinc.cloudoffice.orgtree.service.user.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbinc.cloudoffice.orgtree.mapper.auth.EmpAuthorityMapper;
import com.dbinc.cloudoffice.orgtree.mapper.config.EmpConfigMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.EmpConfigVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional 
@RequiredArgsConstructor
public class UserConfigService
{
  private final EmpConfigMapper empConfigMapper;
  
  /**
 * @author sunjeehun
 * @apiNote EMP_CONFIG 조회
 * @param paramsMap
 * @return RestResultVO
 * @throws Exception
 */
  public RestResultVO selectMyEmpConfig(Map<String, Object> paramsMap) throws Exception
  {
    RestResultVO rrVO = new RestResultVO();
    EmpConfigVO empConfigVO = empConfigMapper.selectEmpConfig(paramsMap);
    rrVO.setDataOne(empConfigVO);
    return rrVO;
  }
  
  /**
   * @author sunjeehun
   * @apiNote EMP_CONFIG DELETE -> INSERT
   * @param paramsMap
   * @return RestResultVO
   * @throws Exception
   */
  public RestResultVO insertMyEmpConfig(EmpConfigVO empConfigVO) throws Exception
  {
    RestResultVO rrVO = new RestResultVO();
    
    // EMP_CONFIG 테이블에 데이터 저장 : Delete -> insert
    empConfigMapper.deleteEmpConfig(empConfigVO);
    int r1 = empConfigMapper.insertEmpConfig(empConfigVO);
    
    if (r1 > 0) 
    {
      rrVO.setResultCode(C.SUCCESS);
      rrVO.setResultMsg(C.SUCCESS_MSG);
    }
    else 
    {
      throw new Exception();
    }
    
    rrVO.setQueryResult(r1);
    return rrVO;
  }
}
