package com.dbinc.cloudoffice.orgtree.service.user.emp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbinc.cloudoffice.orgtree.mapper.company.CompanyMapper;
import com.dbinc.cloudoffice.orgtree.mapper.dept.DeptMasterMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;
import com.dbinc.cloudoffice.orgtree.util.Util;
import com.dbinc.cloudoffice.orgtree.vo.CompanyMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.DeptMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpListVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserEmpService {
	private final CompanyMapper companyMapper;
	private final EmpMapper empMapper;
	private final DeptMasterMapper deptMapper;

	/**
	 * @author minwest61
	 * @apiNote 법인 목록 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectCompanySelBox(Map<String, Object> paramsMap) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		// 로그인한 사용자의 법인 list 조회
		List<CompanyMasterVO> companyMasterVO = companyMapper.selectCompanySelBox(paramsMap);
		rrVO.setData(companyMasterVO);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 조회 조건에 맞는 사용자 목록 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpList(Map<String, Object> paramsMap) throws Exception {
	    RestResultVO rrVO = new RestResultVO();
	    List<EmpListVO> empList = new ArrayList<>();
	    int page = Integer.parseInt(paramsMap.get("page").toString());
	    int rows = Integer.parseInt(paramsMap.get("rows").toString());
	    int total = 1; // 총 페이지 수
	    
	    // 최상위부서를 하위부서포함으로 조회하는 경우 = 전체 사용자 조회 이므로
	    // 전체 사용자 조회에 맞는 조건으로 변경해 Set 해준다.
	    if("Y".equals(paramsMap.get("includeSub").toString()) && "dept_300".equals(paramsMap.get("deptCd")) ) {
    		paramsMap.put("includeSub", "N");
    		paramsMap.put("deptCd", "");
    	}
	    
	    int records = empMapper.selectEmpListCnt(paramsMap);
	    // 페이징 처리
        paramsMap.putAll(Util.getPagingForSQL(page, rows, total, records));
        if (records < 1)
        {
        	page = 0;
        	total = 0;
        }
        else 
        {
        	page = (int) paramsMap.remove("page");
        	total = (int) paramsMap.remove("total");
            empList = empMapper.selectEmpList(paramsMap);
        }
	    
	    rrVO.setPage(String.valueOf(page));
	    rrVO.setTotal(String.valueOf(total));
	    rrVO.setRecords(String.valueOf(records));
	    rrVO.setData(empList);
	    
	    return rrVO;	
	}
	
	/**
	 * @author minwest61
	 * @apiNote 부서 목록 조회(조직도 트리)
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public List<DeptMasterVO> selectDeptList(List<DeptMasterVO> deptList, Map<String, Object> paramsMap) throws Exception {

		List<DeptMasterVO> childDeptList = new ArrayList<>();
		childDeptList = deptMapper.selectDeptList(paramsMap);
		
		// 계층형 조회
		if(childDeptList.size() > 0) {
			for(DeptMasterVO childDept : childDeptList) {
				String childDeptCd = childDept.getDeptCd();
				// 부서수정 메뉴에서 상위부서 변경시 팝업에서 자기 자신 제외
				if(childDeptCd.equals(paramsMap.get("deptCd").toString())) {
					continue;
				}
				paramsMap.put("parentDeptCd", childDeptCd);
				deptList.add(childDept);
				selectDeptList(deptList, paramsMap); // 부서id를 부모id로 재조회(재귀)
			}
		}
		
		return deptList;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 부서코드에 해당하는 전체 사용자 조회
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectDeptEmpList(Map<String, Object> paramsMap) throws Exception {
		RestResultVO rrVO = new RestResultVO();
        paramsMap.put("offset", 0);
        paramsMap.put("fetchNext", 10000); // max값
        paramsMap.put("includeHidden", "N");
        
		List<EmpListVO>deptEmpList = empMapper.selectEmpList(paramsMap);

		rrVO.setData(deptEmpList);
		return rrVO;
	}
}
