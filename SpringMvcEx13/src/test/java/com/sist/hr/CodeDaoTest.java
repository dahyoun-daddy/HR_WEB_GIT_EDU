package com.sist.hr;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sist.hr.code.dao.CodeDao;
import com.sist.hr.code.domain.CodeVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration( locations = 
                          {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CodeDaoTest {
	private final Logger LOG= LoggerFactory.getLogger(CodeDaoTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private CodeDao codeDao;
	
	CodeVO codeVO01;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		LOG.info("mockMvc:"+mockMvc);
		
		codeVO01 = new CodeVO();
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+codeDao:"+codeDao);
		LOG.info("++++++++++++++++++++++++");
	}
	
	
	
	@Test
	public void web_do_selectOne() throws Exception {
		LOG.info("1==========================");
		LOG.info("=get=");
		LOG.info("1==========================");
		
		MockHttpServletRequestBuilder createMessage =
				MockMvcRequestBuilders.get("/code/select_one.do")
				.param("cd_id", "C_001")
				;
		
		mockMvc.perform(createMessage)
		.andDo(print())
		.andExpect(status().is2xxSuccessful());		
	}
	
	
	@Test
	@Ignore
	public void do_selectOne() {
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+do_selectOne");
		LOG.info("++++++++++++++++++++++++");		
		codeVO01.setCd_id("C_001");
		List<CodeVO> list= (List<CodeVO>) codeDao.do_retrieve(codeVO01);
		
		LOG.info("++++++++++++++++++++++++");
		LOG.info("+list:"+list);
		LOG.info("++++++++++++++++++++++++");
		assertThat(3, is(list.size()));
	}
	
}
