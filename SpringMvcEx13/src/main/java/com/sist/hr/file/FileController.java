package com.sist.hr.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.sist.hr.common.StringUtil;
import com.sist.hr.file.domain.FileVO;
import com.sist.hr.file.service.FileSvc;
import com.sist.hr.user.controller.UserController;

@Controller
public class FileController {

	Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileSvc fileSvc;
	
	private static final String VIEW_NAME="/file/file";
	private static final String UPLOAD_ROOT="D:\\hr_file";
	

	
	/**
	 * @Resource : name속성을 통해 자동 주입 실행
	 * 
	 */
	@Resource(name="downloadView")
	private View download;
	
	@RequestMapping(value="/file/download.do",method=RequestMethod.POST)
	public ModelAndView downloadSubmit(HttpServletRequest req) {
		ModelAndView  mav=new ModelAndView();
		
		String fileId = req.getParameter("file_id");
		String seq = req.getParameter("seq");
		
		FileVO inVO=new FileVO();
		inVO.setFileId(fileId);
		inVO.setSeq(seq);
		
		FileVO outVO = (FileVO) fileSvc.do_selectOne(inVO);
		
		String fullPath = outVO.getSavePath()+File.separator+outVO.getSaveFileNm();
		log.info("fullPath:"+fullPath);
		
		mav.setView(download);
		/*
		 * 	File downloadFile = (File) model.get("downloadFile");
			String orgFileNm  = (String) model.get("orgFileName");
		 */
		File downloadFile = new File(fullPath);
		mav.addObject("downloadFile", downloadFile);
		mav.addObject("orgFileName", outVO.getOrgFileNm());
		
		return mav;
	}
   

	   
	@RequestMapping(value="/file/file.do",method=RequestMethod.GET)
	public String uploadFile() {
		log.info("============================");
		log.info("=uploadFile=");
		log.info("============================");
		return VIEW_NAME;
	}
	
	/**
	 * D:\\hr_file
	 * 년도별/월별
	 *      2018/11
	 * @param mReq
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws DataAccessException
	 */
	@RequestMapping(value="/file/file.do",method=RequestMethod.POST)
	public String uploadSubmit(MultipartHttpServletRequest mReq,Model model)
	  throws IOException,DataAccessException{
		log.info("============================");
		log.info("=uploadSubmit=");
		log.info("============================");
		List<FileVO> list=new ArrayList<FileVO>();
		String uploadPath = "";
		
		String workDiv = StringUtil.nvl(mReq.getParameter("work_div"),"com");
		
		//D:\\hr_file
		File fileRootDir =new File(UPLOAD_ROOT);
		if(fileRootDir.isDirectory() ==false) {
			fileRootDir.mkdirs();
		}
		uploadPath=UPLOAD_ROOT;
		
		
		//년도
		String yyyy = StringUtil.currDate("yyyy");
		
		uploadPath = uploadPath+File.separator+yyyy;
		File yyyyDir =new File(uploadPath);
		if(yyyyDir.isDirectory() == false) {
			yyyyDir.mkdirs();
		}
		
		
		//월별
		String mm = StringUtil.currDate("MM");
		uploadPath = uploadPath+File.separator+mm;
		File mmDir =new File(uploadPath);
		if(mmDir.isDirectory() == false) {
			mmDir.mkdir();
		}
		
		Iterator<String> files = mReq.getFileNames();
		
		//FileID yyyyMMddhhmmss
		String fileId       = "";
		if("".equals(fileId)) {
			fileId = StringUtil.currDate("yyyyMMddhhmmss")
					+"_"+StringUtil.getUUID();
		}
		
		int fileNo =1;
		while(files.hasNext()) {
			FileVO fileVo=new FileVO();
			//file01,file02
			String uploadFileName = files.next();
			
			String orgFileName  = "";
			String saveFileName = "";
			String ext          = "";
			long   fileSize     = 0;
			
			log.info("uploadFileName:"+uploadFileName);
			MultipartFile mFile= mReq.getFile(uploadFileName);
			
			//원본파일명
			orgFileName = mFile.getOriginalFilename();
			//빈파일 처리
			if(null == orgFileName || orgFileName.equals(""))continue;
				
				
			log.info("1.orgFileName:"+orgFileName);
			//확장자
			if(orgFileName.lastIndexOf(".")>-1) {
				ext = orgFileName.substring(orgFileName.lastIndexOf("."));
			}
			log.info("2.ext:"+ext);
			//파일사이즈
			fileSize =  mFile.getSize();
			
			log.info("3.fileSize:"+fileSize);
			
			//FileID yyyyMMddhhmmss

			log.info("4.fileId:"+fileId);
			
			//저장파일명:yyyyMMddhhmmss+UUID+확장자
			saveFileName = fileId+""+ext;
			log.info("5.저장파일명:"+saveFileName);
			
			fileVo.setFileId(fileId);
			fileVo.setSeq(fileNo+"");
			fileVo.setOrgFileNm(orgFileName);
			fileVo.setSaveFileNm(saveFileName);
			fileVo.setWorkDiv(workDiv);
			fileVo.setFileSize(fileSize+"");
			fileVo.setSavePath(uploadPath);
			
			if(null != orgFileName && !uploadPath.equals("")) {
				//multipart -> uploadPath전송
				mFile.transferTo(new File(uploadPath+File.separator+saveFileName));
				list.add(fileVo);
			}
			
			
			fileNo++;
		}//--while
		
		log.info("6.list:"+list);
		
		int flag = this.fileSvc.do_saveTx(list);
		log.info("7.flag:"+flag);
		
		
		FileVO param=new FileVO();
		param.setFileId(fileId);
		model.addAttribute("list", fileSvc.do_retrieve(param));
		
		return VIEW_NAME;
	}
	
	
	
	
}
