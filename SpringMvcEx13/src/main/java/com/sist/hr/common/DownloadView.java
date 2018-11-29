package com.sist.hr.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * AbstractView: 스프링 DispatcherServlet기능 requestURI에 따라
 *               컨트롤러를 분기하고 로직 처리후 Resolver를 사용하여 해당 jsp파일을
 *               찾아 응답하게 되는데 그 사이 시점에 View를 잡아 처리 
 *              
 * @author sist1
 *
 */
public class DownloadView extends AbstractView {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	/**
	 * 저장파일 -> 원본 파일명 치환
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void setDownloadFileName(String fileName
			, HttpServletRequest request
			, HttpServletResponse response) throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent");
		log.info("2.userAgent:"+userAgent);
		log.info("2.fileName:"+fileName);
		
		boolean isIe = (userAgent.indexOf("Trident") !=-1);
		log.info("isIe:"+isIe);
		if(isIe) {
			fileName = URLEncoder.encode(fileName, "utf-8");
		}else {
			String docName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			fileName = new String(docName.getBytes("utf-8"));
			
		}
		log.info("3.fileName:"+fileName);
		
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";" ); 
		response.setHeader("Content-Transfer-Encoding", "binary");
	}
	
	/**
	 * FileDownload Stream처리 
	 * @param downloadFile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void downloadFile(File downloadFile
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		OutputStream  out= response.getOutputStream();
		FileInputStream in =new FileInputStream(downloadFile);
		try {
			
			FileCopyUtils.copy(in, out);
			out.flush();
		}catch(Exception e) {
			throw e;
		}finally {
			try {
				if(null != in )in.close();
			}catch(IOException ioe) {
				throw ioe;
			}
			
			try {
				if(null != out )out.close();
			}catch(IOException ioe) {
				throw ioe;
			}			
		}
		
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			setResponseContentType(request, response);
			File downloadFile = (File) model.get("downloadFile");
			String orgFileNm  = (String) model.get("orgFileName");
			
			log.info("*renderMergedOutputModel*********************");
			log.info("1.orgFileNm:"+orgFileNm);
			log.info("1.downloadFile:"+downloadFile.getName());
			log.info("1.model:"+model);
			setDownloadFileName(orgFileNm,request,response);
			response.setContentLength((int)downloadFile.length());
			this.downloadFile(downloadFile, request, response);
			
			log.info("*//renderMergedOutputModel*********************");
		}catch(Exception e) {
			throw e;
		}

	}

}
