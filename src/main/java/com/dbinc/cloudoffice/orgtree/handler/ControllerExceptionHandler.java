package com.dbinc.cloudoffice.orgtree.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dbinc.cloudoffice.orgtree.util.C;
import com.dbinc.cloudoffice.orgtree.vo.RestResultVO;

@RestControllerAdvice
public class ControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(DuplicateKeyException.class)
	public  ResponseEntity<RestResultVO> dupException(DuplicateKeyException e) {
		RestResultVO rrVO = new RestResultVO();
		logger.debug("Duplicate Key Exception  ====> " + e.toString());
		rrVO.setResultCode(C.FAIL);
		rrVO.setResultMsg(C.FAIL_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}
	
	@ExceptionHandler(IOException.class)
	public  ResponseEntity<RestResultVO> ioException(IOException e) {
		RestResultVO rrVO = new RestResultVO();
		logger.debug("IOException ====> " + e.toString());
		rrVO.setResultCode(C.FAIL);
		rrVO.setResultMsg(C.FAIL_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public  ResponseEntity<RestResultVO> exception(Exception e) {
		RestResultVO rrVO = new RestResultVO();
		logger.debug("Exception ====> " + e.toString());
		rrVO.setResultCode(C.FAIL);
		rrVO.setResultMsg(C.FAIL_MSG);
		return new ResponseEntity<>(rrVO, HttpStatus.OK);
	}
}
