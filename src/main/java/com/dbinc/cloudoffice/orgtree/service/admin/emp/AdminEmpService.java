package com.dbinc.cloudoffice.orgtree.service.admin.emp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dbinc.cloudoffice.orgtree.mapper.auth.EmpAuthorityMapper;
import com.dbinc.cloudoffice.orgtree.mapper.code.CodeMapper;
import com.dbinc.cloudoffice.orgtree.mapper.config.EmpConfigMapper;
import com.dbinc.cloudoffice.orgtree.mapper.dept.DeptMasterMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpDeptXrefMapper;
import com.dbinc.cloudoffice.orgtree.mapper.emp.EmpMapper;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.CodeVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpDeptXrefListVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpDetailVO;
import com.dbinc.cloudoffice.orgtree.vo.EmpListVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminEmpService {
	private final EmpMapper empMapper;
	private final EmpDeptXrefMapper empDeptXrefMapper;
	private final EmpConfigMapper empConfigMapper;
	private final EmpAuthorityMapper empAuthorityMapper;
	private final DeptMasterMapper deptMasterMapper;
	private final CodeMapper codeMapper;
	
	/**
	 * @author minwest61
	 * @apiNote 사용자 상세 정보 조회(EMP_MASTER 조회)
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpInfo(Map<String, Object> paramsMap) throws Exception {	
		RestResultVO rrVO = new RestResultVO();
		EmpDetailVO empDetail = empMapper.selectEmpInfo(paramsMap);
	    rrVO.setDataOne(empDetail);
	    
	    return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 사용자 상세 정보 UPDATE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO updateEmpInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		// EMP 관련 테이블 변경 정보 UPDATE
		int resultInfo = empMapper.updateEmpMaster(paramsMap); // EMP_MASTER Update
		int resultConfig = empConfigMapper.updateEmpConfig(paramsMap); // EMP_CONFIG Update
		int resultXref = empDeptXrefMapper.updateEmpDeptXref(paramsMap); // EMP_DEPT_XREF Update
		if (resultInfo == 1 && resultConfig == 1 && resultXref == 1) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(resultInfo);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 사용자 INSERT
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO insertEmpInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		
		int resultInfo = empMapper.insertEmpMaster(paramsMap); // EMP_MASTER Insert
		int resultConfig = empConfigMapper.insertEmpConfigInfo(paramsMap); // EMP_CONFIG Insert
		int resultXref = empDeptXrefMapper.insertEmpDeptXref(paramsMap); // EMP_DEPT_XREF Insert
		int resultAuth = empAuthorityMapper.insertEmpAuthority(paramsMap); // EMP_AUTHORITY Insert
		
		if (resultInfo == 1 && resultConfig == 1 && resultXref == 1 && resultAuth == 1) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(resultInfo);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 사용자 DELETE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO deleteEmpInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int resultInfo = empMapper.deleteEmpMaster(paramsMap); // EMP_MASTER Insert
		int resultConfig = empConfigMapper.deleteEmpConfigInfo(paramsMap); // EMP_CONFIG Insert
		int resultXref = empDeptXrefMapper.deleteEmpDeptXref(paramsMap); // EMP_DEPT_XREF Insert
		int resultAuth = empAuthorityMapper.deleteEmpAuthority(paramsMap); // EMP_AUTHORITY Insert
		
		if (resultInfo > 0 && resultConfig > 0 && resultXref > 0 && resultAuth > 0) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(resultInfo);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 겸직 목록 조회(EMP_DEPT_XREF 조회)
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectEmpDeptXrefList(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
	    List<EmpDeptXrefListVO> empDeptXrefList = empDeptXrefMapper.selectEmpDeptXrefList(paramsMap);
	    rrVO.setData(empDeptXrefList);
	    
	    return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 겸직 정보 UPDATE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO updateEmpDeptXrefDetail(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int result = empDeptXrefMapper.updateEmpDeptXrefDetail(paramsMap); // EMP_DEPT_XREF Update
		int resultEmp = 0, resultConfig = 0, resultAuth = 0;
		
		// 겸직이 Y가 아닌,
		// 즉, 사용자의 메인 정보 변경시 EMP 관련 모든 테이블 정보 UPDATE
		if("N".equals(paramsMap.get("addYn").toString()))
		{
			resultEmp = empMapper.updateEmpMasterXrefInfo(paramsMap); // EMP_MASTER Update
			resultConfig = empConfigMapper.updateEmpConfigXref(paramsMap); // EMP_CONFIG Update
			resultAuth = empAuthorityMapper.updateEmpAuthorityXref(paramsMap); // EMP_AUTHORITY Update
			if(resultEmp < 1 || resultConfig < 1 || resultAuth < 1) result = 0; // Update 안되면 에러처리
		}
		
		if (result == 1) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(result);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote EMP_DEPT_XREF에서 겸직 정보 DELETE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO deleteEmpDeptXrefDetail(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int result = empDeptXrefMapper.deleteEmpDeptXrefDetail(paramsMap); // EMP_DEPT_XREF Delete
		
		if (result == 1) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(result);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote EMP_DEPT_XREF에 겸직 정보 INSERT
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO insertEmpDeptXrefDetail(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		
		int result = empDeptXrefMapper.insertEmpDeptXref(paramsMap); // EMP_DEPT_XREF Insert
		
		if (result == 1) 
	    {
	      rrVO.setResultCode(C.SUCCESS);
	      rrVO.setResultMsg(C.SUCCESS_MSG);
	    }
	    else 
	    {
	      throw new Exception();
	    }
		
	    rrVO.setQueryResult(result);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 사용자 목록 엑셀 파일 다운로드
	 * @param paramsMap
	 * @return Workbook
	 * @throws Exception
	 * @throws IOException
	 */
	public  Workbook empListExcelDown(Map<String, Object> paramsMap) throws Exception, IOException  {
		List<EmpListVO> empList = new ArrayList<>();
		
		paramsMap.put("offset", 0);
	    paramsMap.put("fetchNext", 10000);
		empList =  empMapper.selectEmpList(paramsMap); // 사용자 목록 조회

		Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("사용자목록");
	    Row row = null;
	    Cell cell = null;
	    int rowNum = 0;
	    
	    // 헤더 스타일 지정
	    CellStyle headStyle = wb.createCellStyle();
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);
	    
	    // Header
	    row = sheet.createRow(rowNum++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("법인");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("성명");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("부서");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("직위");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("이메일");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("겸직여부");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("사번");
	    cell = row.createCell(7);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("사내전화");
	    cell = row.createCell(8);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("휴대폰");
	    cell = row.createCell(9);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("재직");
	    cell = row.createCell(10);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("수동관리");
	    
	    // Data Set
	    for(EmpListVO obj : empList) {
	    	row = sheet.createRow(rowNum++);
	        cell = row.createCell(0);
	        cell.setCellValue(obj.getCompanyNm());
	        cell = row.createCell(1);
	        cell.setCellValue(obj.getEmpNm());
	        cell = row.createCell(2);
	        cell.setCellValue(obj.getDeptNm());
	        cell = row.createCell(3);
	        cell.setCellValue(obj.getPosNm());
	        cell = row.createCell(4);
	        cell.setCellValue(obj.getEmail());
	        cell = row.createCell(5);
	        cell.setCellValue(obj.getAddYn());
	        cell = row.createCell(6);
	        cell.setCellValue(obj.getEmpNo());
	        cell = row.createCell(7);
	        cell.setCellValue(obj.getJobTelNo());
	        cell = row.createCell(8);
	        cell.setCellValue(obj.getMobileTelNo());
	        cell = row.createCell(9);
	        cell.setCellValue(obj.getEmpStatusNm());
	        cell = row.createCell(10);
	        cell.setCellValue(obj.getManualMngYn());
	    }
		return wb;
	}
	
	/**
	 * @author minwest61
	 * @apiNote 엑셀 업로드양식 파일 다운로드
	 * @param 
	 * @return Workbook
	 * @throws Exception
	 * @throws IOException
	 */
	public  Workbook empExcelFormDown() throws Exception, IOException  {
		Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("사용자목록");
	    Row row = null;
	    Cell cell = null;
	    int rowNum = 0;
	    
	    // 헤더 스타일 지정
	    CellStyle headStyle = wb.createCellStyle();
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);
	    
	    // 필수 값 헤더 스타일 지정
	    CellStyle headReqStyle = wb.createCellStyle();
	    headReqStyle.setBorderTop(BorderStyle.THIN);
	    headReqStyle.setBorderBottom(BorderStyle.THIN);
	    headReqStyle.setBorderLeft(BorderStyle.THIN);
	    headReqStyle.setBorderRight(BorderStyle.THIN);
	    headReqStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
	    headReqStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // Header
	    row = sheet.createRow(rowNum++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("도메인");
	    cell = row.createCell(1);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("법인코드");
	    cell = row.createCell(2);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("이메일");
	    cell = row.createCell(3);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("성명");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("영문성명");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("사번");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("직위코드");
	    cell = row.createCell(7);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("부서코드");
	    cell = row.createCell(8);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("재직상태코드");
	    cell = row.createCell(9);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("입사일(YYYYMMDD)");
	    cell = row.createCell(10);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("퇴사일(YYYYMMDD)");
	    cell = row.createCell(11);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("숨김여부(Y/N)");
	    cell = row.createCell(12);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("회사전화번호");
	    cell = row.createCell(13);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("휴대전화번호");
	    cell = row.createCell(14);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("수동관리여부(Y/N)");
	    cell = row.createCell(15);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("부서장여부(Y/N)");
	    
		return wb;
	}

	/**
	 * @author minwest61
	 * @apiNote 엑셀 업로드 파일 EMP 관련 테이블에 INSERT
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO empListExcelUp(Map<String, Object> paramsMap) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		MultipartFile file = (MultipartFile) paramsMap.get("file");
		OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
		XSSFWorkbook workbook =  new XSSFWorkbook(opcPackage);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		int result = 0;
		
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell = null;
			
			if(row == null) continue;
			
			cell = row.getCell(0);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("domainId", cell.getStringCellValue().replace(" ", "")); // 공백 제거
			}
			cell = row.getCell(1);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("companyCd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(2);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("email", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(3);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("empNm", cell.getStringCellValue());
			}
			cell = row.getCell(4);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("empEngNm", cell.getStringCellValue());
			}
			cell = row.getCell(5);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("empNo", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(6);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("posCd", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			cell = row.getCell(7);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("deptCd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(8);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("empStatusCd", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			cell = row.getCell(9);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("enterDt", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(10);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("quitDt", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(11);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("hiddenYn", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			else {
				paramsMap.put("hiddenYn", "N");
			}
			cell = row.getCell(12);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("jobTelNo", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(13);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("mobileTelNo", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(14);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("manualMngYn", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			else {
				paramsMap.put("manualMngYn", "N");
			}
			cell = row.getCell(15);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("headYn", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			paramsMap.put("languageCd", "ko-KR");
			paramsMap.put("authorityName", "ROLE_USER");
			paramsMap.put("addYn", "N");
			
			empDeptXrefMapper.deleteAllEmpDeptXref(paramsMap); // 기존 겸직 정보 전부 삭제(ADD_YN : 'N'인 사용자 중복 발생을 방지하기 위해)
			
			int resultInfo = empMapper.insertUpdateEmpMaster(paramsMap); // EMP_MASTER Insert
			int resultConfig = empConfigMapper.insertUpdateEmpConfigInfo(paramsMap); // EMP_CONFIG Insert
			int resultXref = empDeptXrefMapper.insertUpdateEmpDeptXref(paramsMap); // EMP_DEPT_XREF Insert
			int resultAuth = empAuthorityMapper.insertUpdateEmpAuthority(paramsMap); // EMP_AUTHORITY Insert
			
			// 각 테이블이 모두 반영되었는지 체크
			if ( (resultInfo == 1 || resultInfo == 2) && (resultConfig ==1 || resultConfig == 2) && (resultXref ==1 || resultXref == 2 )&& (resultAuth == 1 || resultAuth == 2)) 
		    {
		      result++;
		    }
		    else 
		    {
		      throw new Exception();
		    }
			
		}
		
		// 모든 Row가 반영되었는지 체크
		if(result == sheet.getLastRowNum()) {
			rrVO.setResultCode(C.SUCCESS);
		    rrVO.setResultMsg(C.SUCCESS_MSG);
		    rrVO.setQueryResult(result);
		}
		else {
			rrVO.setResultCode(C.FAIL);
			rrVO.setResultMsg(C.FAIL_MSG);
		}
		
		return rrVO;
	}
	
	
	/**
	 * @author minwest61
	 * @apiNote Google 관리콘솔 데이터 업로드
	 * @param 
	 * @return Workbook
	 * @throws Exception
	 */
	public RestResultVO googleUploadEmpAndDept(Map<String, Object> paramsMap) throws Exception {
		RestResultVO rrVO = new RestResultVO();
		MultipartFile file = (MultipartFile) paramsMap.get("file");
		OPCPackage opcPackage = OPCPackage.open(file.getInputStream());
		XSSFWorkbook workbook =  new XSSFWorkbook(opcPackage);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell cell = null;
			
			if(row == null) continue;
			
			// DOMAIN_ID, EMAIL
			String email = "";
			cell = row.getCell(2);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				email =  cell.getStringCellValue().replace(" ", ""); // 공백 제거
			}
			
			/* 부서 테이블 INSERT */
			paramsMap.put("deptEngNm", "");
			paramsMap.put("manualMngYn", "N");
			paramsMap.put("deptUseYn", "Y");
			
			// DEPT_CD, DEPT_NM, PARENT_DEPT_CD, DEPT_ORD
			cell = row.getCell(5);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				String deptStr =  cell.getStringCellValue();
				String[] deptArr = deptStr.split("/"); // '/' 기준으로 DEPT 배열 만듦. 이 때 deptStr이 '/'로 시작하므로 deptArr[0]은 비어있게 됨.
				
				// 최상위부서의 부서코드 조회
				paramsMap.put("parentDeptCd", "0");
				String parentDeptCd = deptMasterMapper.selectDeptCdByParent(paramsMap).getDeptCd();
				paramsMap.put("parentDeptCd", parentDeptCd);
				
				// deptArr 배열의 부서들을 INSERT(이미 존재하는 부서면 pass)
				for(int j = 1; j < deptArr.length; j++) {
					paramsMap.put("deptNm", deptArr[j]);
					// 부서명(DEPT_NM)을 기준으로 이미 존재하는 부서인지 Check
					int deptExistYn = deptMasterMapper.selectDeptCdByDeptNmCnt(paramsMap);
					// 존재하면 pass
					if(deptExistYn > 0) 
					{
						paramsMap.put("deptCd", deptMasterMapper.selectDeptCdByDeptNm(paramsMap).getDeptCd()); // 다음 부서의 PARENT_DEPT_CD를 입력하기 위해 DEPT_CD 저장해둠.
						continue;
					}
					// 존재하지 않는다면 INSERT
					else
					{
						// i가 1이 아니면 PARENT_DEPT_CD를 이전 부서의 DEPT_CD로 지정
						// 1이면 최상위부서가 PARENT_DEPT_CD이므로 위에서 이미 지정해주어 pass함
						if(j != 1)
						{
							paramsMap.put("parentDeptCd", paramsMap.get("deptCd")); // deptArr의 전 index의 부서코드가 PARENT_DEPT_CD
						}
						// 새로운 부서를 INSERT하기 위해 랜덤 String 값으로 DEPT_CD 지정
						Random random = new Random();
						String randomDeptCd = random.ints(48, 123)
												.filter(k -> (k <= 57 || k >= 65) && (k <= 90 || k >= 97))
						                        .limit(10)
												.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
												.toString();
						paramsMap.put("deptCd",randomDeptCd);
						
						paramsMap.put("deptOrd", j+1); // 부서 순서는 '/'기준으로 나눈 index 값 + 1 (최상위부서는 1)
						
						int deptCnt = deptMasterMapper.insertUpdateDeptInfo(paramsMap);
						if(deptCnt <= 0) {
							throw new Exception();
						}
					}
				}
			}
			
			/* EMP 테이블 INSERT */
			paramsMap.put("email", email);
			paramsMap.put("manualMngYn", "N");
			paramsMap.put("hiddenYn", "N");
			paramsMap.put("languageCd", "ko-KR");
			paramsMap.put("authorityName", "ROLE_USER");
			paramsMap.put("addYn", "N");
			paramsMap.put("headYn", "N");
			
			// 재직상태
			cell = row.getCell(7);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				String empStatusCd = cell.getStringCellValue().replace(" ", "");
				if(!"Active".equals(empStatusCd)) continue;
				paramsMap.put("codeNm", "재직");
				paramsMap.put("codeDiv", "CM001");
				CodeVO codeVO = codeMapper.selectCodeByName(paramsMap);
				paramsMap.put("empStatusCd", codeVO.code);
			}
			else {
				continue;
			}
			
			// EMP_NM
			String empNm = "";
			cell = row.getCell(1);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				empNm += cell.getStringCellValue();
			}
			cell = row.getCell(0);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				empNm += " " + cell.getStringCellValue();
			}
			paramsMap.put("empNm", empNm);
			
			cell = row.getCell(12);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("mobileTelNo", cell.getStringCellValue().replace(" ", ""));
			}
			else {
				paramsMap.put("mobileTelNo", "");
			}
			
			cell = row.getCell(13);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("jobTelNo", cell.getStringCellValue().replace(" ", ""));
			}
			else {
				paramsMap.put("jobTelNo", "");
			}
			
			// 직위
			cell = row.getCell(20);
			if(cell != null && !cell.getStringCellValue().equals("없음")) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("codeNm", cell.getStringCellValue());
				paramsMap.put("codeDiv", "CM002");
				CodeVO codeVO = codeMapper.selectCodeByName(paramsMap);
				paramsMap.put("posCd", codeVO.code);
			}
			
			empDeptXrefMapper.deleteAllEmpDeptXref(paramsMap); // 기존 겸직 정보 전부 삭제(ADD_YN : 'N'인 사용자 중복 발생을 방지하기 위해)
			
			int empCnt = empMapper.insertUpdateEmpMaster(paramsMap);
			int empConfigCnt = empConfigMapper.insertUpdateEmpConfigInfo(paramsMap); // EMP_CONFIG Insert
			int empDeptCnt = empDeptXrefMapper.insertUpdateEmpDeptXref(paramsMap); // EMP_DEPT_XREF Insert
			int empAuthCnt = empAuthorityMapper.insertUpdateEmpAuthority(paramsMap); // EMP_AUTHORITY Insert
			
			if(empCnt < 1 || empConfigCnt < 1 || empDeptCnt < 1 || empAuthCnt < 1) {
				throw new Exception();
			}
		}

		return rrVO;
	}
}
