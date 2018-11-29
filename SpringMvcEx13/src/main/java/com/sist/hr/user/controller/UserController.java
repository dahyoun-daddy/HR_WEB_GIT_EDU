package com.sist.hr.user.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sist.hr.code.domain.CodeVO;
import com.sist.hr.code.service.CodeSvc;
import com.sist.hr.common.SearchVO;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.UserVO;
import com.sist.hr.user.service.UserSvc;

@Controller
public class UserController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserSvc userSvc;
    
	@Autowired
	private CodeSvc codeSvc;	
	
	
	private static final String VIEW_NAME="/user/user";
	
	
	@RequestMapping(value="/user/search.do")	
	public String do_search(@ModelAttribute SearchVO invo,Model model) throws EmptyResultDataAccessException, ClassNotFoundException, SQLException {	
		log.info("SearchVO: "+invo);
		//param -> view
		
		if(invo.getPage_size() == 0) {
			invo.setPage_size(10);
		}
		
		if(invo.getPage_num() == 0) {
			invo.setPage_num(1);
		}
		
		if(null == invo.getSearch_div()) {
			invo.setSearch_div("");
		}
		
		if(null == invo.getSearch_word()) {
			invo.setSearch_word("");
		}		
		
		
		model.addAttribute("param",invo);
		
		List<UserVO> list = userSvc.do_retrieve(invo);
		//총글수
		int total_cnt = 0;
		if(null != list && list.size()>0) {
			total_cnt = list.get(0).getTotal_cnt();
			log.info("total_cnt: "+total_cnt);
		}
		
		CodeVO codePage=new CodeVO();
		codePage.setCd_id("C_001");
		
		model.addAttribute("code_page",codeSvc.do_retrieve(codePage));
		model.addAttribute("total_cnt",total_cnt);
		model.addAttribute("list",list);
		return VIEW_NAME;  
	}
	
	@RequestMapping(value="/user/delete.do",method=RequestMethod.POST
			,consumes= {"text/plain", "application/*"}
			,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest req,Model model) throws RuntimeException, SQLException{
		String uIdList = req.getParameter("u_id_list");
		log.info("uIdList: "+uIdList);
		
		Gson gson=new Gson();
		List<String>  listParam = gson.fromJson(uIdList, List.class);
		log.info("uIdList: "+listParam);
		
		List<UserVO> paramList = new ArrayList<UserVO>();
		for(int i=0;i<listParam.size();i++) {
			UserVO vo =new UserVO();
			vo.setU_id(listParam.get(i));
			
			paramList.add(vo);
		}
		log.info("paramList: "+paramList);
		
		int flag = this.userSvc.do_deleteMulti(paramList);
		
		JSONObject object=new JSONObject();
		
		if(flag>0) {
			object.put("flag", flag);
			object.put("message", "삭제 되었습니다.\n("+flag+"건 삭제.)");
		}else {
			object.put("flag", flag);
			object.put("message", "삭제 실패^^.");			
		}		
		String jsonData = object.toJSONString();
		
		log.info("3========================");
		log.info("jsonData="+jsonData);
		log.info("3========================");			
		return jsonData;
	}
	
	
	@RequestMapping(value="/user/update.do",method=RequestMethod.POST
	        ,produces="application/json;charset=utf8"  
	)
	@ResponseBody
	public String update(@ModelAttribute UserVO invo,HttpServletRequest req,Model model) throws EmptyResultDataAccessException, ClassNotFoundException, SQLException {
		String upsert_div = req.getParameter("upsert_div");
		
		log.info("2========================");
		log.info("invo="+invo);
		log.info("upsert_div="+upsert_div);
		
		log.info("2========================");	
		
		int flag = 0;
		//수정
		
		//등록
		if("update".equals(upsert_div)) {
			flag = userSvc.update(invo);
			log.info("3update=================");	
		}else {
			flag = userSvc.add(invo);
			log.info("3add=================");	
		}
		
		 
		JSONObject object=new JSONObject();
		
		if(flag>0) {
			object.put("flag", flag);
			object.put("message", "등록 되었습니다.");
		}else {
			object.put("flag", flag);
			object.put("message", "등록 실패^^.");			
		}
		
		String jsonData = object.toJSONString();
		
		log.info("3========================");
		log.info("jsonData="+jsonData);
		log.info("3========================");			
		return jsonData;
	}	 
	
	@RequestMapping(value="/user/do_search_one.do",method=RequestMethod.POST
			        ,produces="application/json;charset=utf8"  
			)
	@ResponseBody
	public String get(HttpServletRequest req,Model model) throws EmptyResultDataAccessException, ClassNotFoundException, SQLException {
		String u_id = req.getParameter("u_id");
		log.info("2========================");
		log.info("get=");
		log.info("2========================");	
		UserVO userVO=new UserVO();
		userVO.setU_id(u_id);
		
		//JSON Convertor
		UserVO outVO = userSvc.get(userVO);
		JSONObject object=new JSONObject();
		object.put("u_id", outVO.getU_id());
		object.put("name", outVO.getName());
		object.put("password", outVO.getPassword());
		object.put("level", outVO.getLevel().intValue());
		object.put("login", outVO.getLogin());
		object.put("recommend", outVO.getRecommend());
		object.put("email", outVO.getEmail());
		object.put("regDt", outVO.getRegDt());
		
		String jsonData = object.toJSONString();
		
		log.info("3========================");
		log.info("jsonData="+jsonData);
		log.info("3========================");			
		model.addAttribute("vo", userSvc.get(userVO));
		return jsonData;
	}
	
	
	

}
