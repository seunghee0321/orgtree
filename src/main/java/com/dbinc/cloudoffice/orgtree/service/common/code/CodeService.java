package com.dbinc.cloudoffice.orgtree.service.common.code;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbinc.cloudoffice.orgtree.mapper.auth.EmpAuthorityMapper;
import com.dbinc.cloudoffice.orgtree.mapper.code.CodeMapper;
import com.dbinc.cloudoffice.orgtree.mapper.config.EmpConfigMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;
import com.dbinc.cloudoffice.orgtree.vo.CodeVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeService {
	private final CodeMapper codeMapper;

	/**
	 * @author minwest61
	 * @apiNote CODE_MASTER 조회(재직, 겸직여부 등)
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectCodeDetail(Map<String, Object> paramsMap) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		List<CodeVO> codeList = codeMapper.selectCodeDetail(paramsMap);
		rrVO.setData(codeList);
		return rrVO;
	}
}