package com.dbinc.cloudoffice.orgtree.service.admin.dept;

import java.io.IOException;
import java.util.Map;

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

import com.dbinc.cloudoffice.orgtree.mapper.dept.DeptMasterMapper;
import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.DeptMasterVO;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional 
@RequiredArgsConstructor
public class AdminDeptService {

	private final DeptMasterMapper deptMasterMapper;
	
	/**
	 * @author minwest61
	 * @apiNote 부서 상세 정보 조회(DEPT_MASTER 조회)
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO selectDeptInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		String parentDeptCd = deptMasterMapper.selectParentDeptCd(paramsMap); // 최상위부서 여부 판단하기위해 조회
		paramsMap.put("parentDeptCd", parentDeptCd); 
		DeptMasterVO deptMasterVO = deptMasterMapper.selectDeptInfo(paramsMap); // 최상위부서라면 상위부서 정보 안가져옴
		rrVO.setDataOne(deptMasterVO);
		return rrVO;
	}
	
	/**
	 * @author minwest61
	 * @apiNote DEPT_MASTER에 부서 INSERT
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO insertDeptInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int result = deptMasterMapper.insertDeptInfo(paramsMap);
		if (result > 0) 
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
	 * @apiNote DEPT_MASTER에 부서 정보 UPDATE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO updateDeptInfo(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int result = deptMasterMapper.updateDeptInfo(paramsMap);
		if (result > 0) 
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
	 * @apiNote DEPT_MASTER에 상위부서 정보 UPDATE
	 * @param paramsMap
	 * @return RestResultVO
	 * @throws Exception
	 */
	public RestResultVO updateDeptParent(Map<String, Object> paramsMap) throws Exception 
	{
		RestResultVO rrVO = new RestResultVO();
		int result = deptMasterMapper.updateDeptParent(paramsMap);
		if (result > 0) 
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
	 * @apiNote 엑셀 업로드양식 파일 다운로드
	 * @param 
	 * @return Workbook
	 * @throws Exception
	 * @throws IOException
	 */
	public Workbook deptExcelFormDown() throws Exception, IOException  {
		Workbook wb = new XSSFWorkbook();
	    Sheet sheet = wb.createSheet("부서목록");
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
	    cell.setCellValue("부서코드");
	    cell = row.createCell(3);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("부서명");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("부서영문명");
	    cell = row.createCell(5);
	    cell.setCellStyle(headReqStyle);
	    cell.setCellValue("상위부서코드");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("부서순서");
	    cell = row.createCell(7);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("수동관리여부(Y/N)");
	    
		return wb;
	}

	/**
	 * @author minwest61
	 * @apiNote 엑셀업로드 파일 DEPT_MASTER에 INSERT
	 * @param 
	 * @return Workbook
	 * @throws Exception
	 */
	public RestResultVO deptListExcelUp(Map<String, Object> paramsMap) throws Exception {
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
				paramsMap.put("domainId", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(1);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("companyCd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(2);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("deptCd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(3);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("deptNm", cell.getStringCellValue());
			}
			cell = row.getCell(4);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("deptEngNm", cell.getStringCellValue());
			}
			cell = row.getCell(5);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("parentDeptCd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(6);
			if(cell != null) {
				cell.setCellType(CellType.STRING);
				paramsMap.put("deptOrd", cell.getStringCellValue().replace(" ", ""));
			}
			cell = row.getCell(7);
			if(cell != null) {
				cell.setCellType(CellType.STRING); 
				paramsMap.put("manualMngYn", cell.getStringCellValue().replace(" ", "").toUpperCase());
			}
			else {
				paramsMap.put("manualMngYn", "N");
			}
			paramsMap.put("deptUseYn", "Y");

			int resultInfo = deptMasterMapper.insertUpdateDeptInfo(paramsMap);
			if (resultInfo > 0) 
		    {
				result++;
		    }
		    else
		    {
		      throw new Exception();
		    }
			
		}
		
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
}
