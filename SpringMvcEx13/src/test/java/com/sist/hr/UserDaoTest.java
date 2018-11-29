package com.sist.hr;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sist.hr.common.SearchVO;
import com.sist.hr.user.dao.UserDao;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration  
//@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
//                                 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		                    
//})
@ContextConfiguration( locations = 
                          {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class UserDaoTest {

	private final Logger LOG= LoggerFactory.getLogger(UserDaoTest.class);
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserDao userDao;
	UserVO  inVO1 = null;
	UserVO  inVO2 = null;
	UserVO  inVO3 = null;
	SearchVO searchVO = null;
	
	
	@Before
	public void setUp() {
		inVO1 = new UserVO("j01_115","다현아빠","1224",Level.BASIC,1,0,"jamesol@paran.com","2018-11-15");
		inVO2 = new UserVO("j02_115","다현아빠","1224",Level.SILVER,51,10,"jamesol02@paran.com","2018-11-15");
		inVO3 = new UserVO("j03_115","다현아빠","1224",Level.GOLD,51,31,"jamesol03@paran.com","2018-11-15");		
	
		searchVO = new SearchVO(10,1,"","");
		  
		LOG.info("context:"+context);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		LOG.info("mockMvc:"+mockMvc);
		LOG.info("userDao:"+userDao);
	}
	
	
	@Test(timeout=2000)
	public void addAndGet() throws SQLException, ClassNotFoundException {

	
		//--------------------------------------------
		//0.모두삭제 
		//--------------------------------------------
		userDao.deleteAll();
		
		//--------------------------------------------
		//1. 단건 추가
		//--------------------------------------------
		userDao.add(inVO1);
		
		//--------------------------------------------
		//1.1 Count
		//--------------------------------------------
		assertThat(userDao.getCount("115"),is(1));
		
		//--------------------------------------------
		//2. 단건 조회
		//--------------------------------------------
		UserVO getVO = userDao.get(inVO1);
		
		//--------------------------------------------
		//3. 추가Data와 비교
		//--------------------------------------------		
		checkSameUser(inVO1, getVO);
	}
	
	private void checkSameUser(UserVO user01, UserVO user02) {
		assertThat(user01.getU_id(), is(user02.getU_id()));
		assertThat(user01.getName(), is(user02.getName()));
		assertThat(user01.getPassword(), is(user02.getPassword()));
		
		assertThat(user01.getLevel(), is(user02.getLevel()));
		assertThat(user01.getLogin(), is(user02.getLogin()));
		assertThat(user01.getRecommend(), is(user02.getRecommend()));
		assertThat(user01.getEmail(), is(user02.getEmail()));
		
	}
	
	@Test
	@Ignore
	public void get() throws Exception {
		LOG.info("1==========================");
		LOG.info("=get=");
		LOG.info("1==========================");
		
		MockHttpServletRequestBuilder createMessage =
				MockMvcRequestBuilders.get("/user/do_search_one.do")
				.param("u_id", "j01_115")
				;
		
		mockMvc.perform(createMessage)
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
		
	}
	
	
	@Test
	public void setUpTest() {
		LOG.info("setUpTest:");
	}
	
	
	
	
}
