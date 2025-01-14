package com.dbinc.cloudoffice.orgtree.mapper.code;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dbinc.cloudoffice.orgtree.vo.CodeVO;

@Repository
@Mapper
public interface CodeMapper {
	List<CodeVO> selectCodeDetail(Map<String, Object> paramsMap);
	CodeVO selectCodeByName(Map<String, Object> paramsMap);
}
