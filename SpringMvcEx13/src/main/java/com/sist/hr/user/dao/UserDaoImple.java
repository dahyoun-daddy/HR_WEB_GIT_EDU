package com.sist.hr.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.hr.common.DuplicateUserIdException;
import com.sist.hr.common.SearchVO;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.UserVO;

@Repository
public class UserDaoImple implements UserDao {

	Logger  log = LoggerFactory.getLogger(UserDaoImple.class);
    
	@Autowired
	private JdbcTemplate jdbcTemplate;


	private RowMapper<UserVO> userMapper = new RowMapper<UserVO>() {

		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO userVo=new UserVO();
			
			userVo.setU_id(rs.getString("u_id"));
			userVo.setName(rs.getString("name"));
			userVo.setPassword(rs.getString("password"));
			
			userVo.setLevel(Level.valueOf(rs.getInt("h_level")));
			userVo.setLogin(rs.getInt("login"));
			userVo.setRecommend(rs.getInt("recommend"));
			userVo.setEmail(rs.getString("email"));
			userVo.setRegDt(rs.getString("reg_dt"));
			
			userVo.setNo(rs.getInt("no"));
			userVo.setTotal_cnt(rs.getInt("total_cnt"));
			
			return userVo;
		}
	};
	



	public UserDaoImple() {
		
	}

	/* (non-Javadoc)
	 * @see com.sist.hr.UserDaoI#getCount(java.lang.String)
	 */
	public int getCount(String userId) throws SQLException {
		int cnt = 0;
		
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt    \n");
		sb.append("   FROM hr_users           \n");
		sb.append("  WHERE u_id LIKE ?     \n");
		
		log.info("1.sql: \n"+sb.toString());
		return this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {"%"+userId+"%"}, Integer.class);
		
	}
	


	/* (non-Javadoc)
	 * @see com.sist.hr.UserDaoI#deleteAll()
	 */
	public void deleteAll() throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM hr_users ");	
		log.info("1.sql:\n"+sb.toString());	
		this.jdbcTemplate.update(sb.toString());
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDaoI#add(com.sist.hr.UserVO)
	 */
	
	public int add(UserVO userVO) throws DuplicateUserIdException {
		//new 인터페이스 이름(){클래스본문}
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO hr_users ( \n");
		sb.append("     u_id,              \n");
		sb.append("     name,              \n");
		sb.append("     password,          \n");
		sb.append("     h_level,           \n");
		sb.append("     login,             \n");
		sb.append("     recommend,         \n");
		sb.append("     email,             \n");
		sb.append("     reg_dt             \n");
		sb.append(" ) VALUES (             \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     ?,                 \n");
		sb.append("     sysdate            \n");
		sb.append(" )                      \n");	
		int flag=0;
		
		try {
				Object[] args = {userVO.getU_id()
							,userVO.getName()
							,userVO.getPassword()
							,userVO.getLevel().intValue()
							,userVO.getLogin()
							,userVO.getRecommend()
							,userVO.getEmail()
			                };
			
				log.info("1.sql:\n"+sb.toString());		
				flag = this.jdbcTemplate.update(sb.toString()
						,args
			           );
		}catch(DuplicateKeyException e) {
			log.info("*****^*****:"+e.getMessage());
			throw new DuplicateUserIdException(e);
		}
		
		log.info("2.flag:\n"+flag);	
		
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.UserDaoI#getAll()
	 */
	public List<UserVO> getAll() throws ClassNotFoundException, SQLException,EmptyResultDataAccessException {
		
		
		StringBuilder sb=new StringBuilder();		
		sb.append(" SELECT hr.*          \n"); 
		sb.append("   FROM hr_users hr   \n");
		sb.append("  ORDER by u_id       \n");
		
		log.info("1.sql: \n"+sb.toString());

		return this.jdbcTemplate.query(sb.toString()
				, this.userMapper);				
	}
	

	public UserVO get(UserVO userVO) throws ClassNotFoundException, SQLException,EmptyResultDataAccessException {
		StringBuilder sb=new StringBuilder();
		sb.append(" SELECT                                    \n");
		sb.append("     u_id,                                 \n");
		sb.append("     name,                                 \n");
		sb.append("     password,                             \n");
		sb.append("     h_level,                              \n");
		sb.append("     login,                                \n");
		sb.append("     recommend,                            \n");
		sb.append("     email,                                \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY-MM-DD HH24:MI:SS') reg_dt,  \n");
		sb.append("     0 no,                                 \n");
		sb.append("     0 total_cnt                           \n");	
		sb.append(" FROM hr_users                             \n");
		sb.append(" WHERE u_id = ?                            \n");
		log.debug("1.sql: \n"+sb.toString());
		
		return this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {userVO.getU_id()}
		        , this.userMapper);
		
		
	}

	/**
	 * 
	 */
	public int update(UserVO userVO) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE hr_users            \n"); 
		sb.append("    SET name      =?        \n");
		sb.append("       ,password  =?        \n");
		sb.append("       ,h_level   =?        \n");
		sb.append("       ,login     =?        \n");
		sb.append("       ,recommend =?        \n");
		sb.append("       ,email     =?        \n");
		sb.append("       ,reg_dt    =sysdate  \n");
		sb.append(" WHERE u_id =?              \n");
		
		log.info("1.sql: \n"+sb.toString());
		log.info("2.userVO: "+userVO);
		Object[] args = {userVO.getName()
				,userVO.getPassword()
				,userVO.getLevel().intValue()
				,userVO.getLogin()
				,userVO.getRecommend()
				,userVO.getEmail()
				,userVO.getU_id()
                };
		
		int flag = jdbcTemplate.update(sb.toString(), args);
		log.info("3.flag: "+flag);
		return flag;
	}

	/**
	 * 단건 삭제!
	 */
	public int delete(UserVO userVO) throws SQLException {
		StringBuilder sb=new StringBuilder();
		sb.append(" DELETE FROM hr_users  \n");
		sb.append(" WHERE u_id = ?        \n");
		log.info("1.sql: \n"+sb.toString());
		log.info("2.userVO: "+userVO);
		Object[] args = {userVO.getU_id()};
		
		return jdbcTemplate.update(sb.toString(),args);
	}


	public List<UserVO> do_retrieve(SearchVO searchVO)
			throws ClassNotFoundException, SQLException, EmptyResultDataAccessException {
		//-------------------------------------
				//1. param read: 검색조건 분기,u_id(10),name(20),email(30)
				//-------------------------------------
				StringBuilder param=new StringBuilder();
				if(null !=searchVO && !"".equals(searchVO.getSearch_div()) ) {
					if("10".equals(searchVO.getSearch_div())){
						param.append(" where hr.u_id like  ?||'%' \n");
					}else if("20".equals(searchVO.getSearch_div())){
						param.append(" where hr.name like  ?||'%' \n");
					}else if("30".equals(searchVO.getSearch_div())){
						param.append(" where hr.email like ?||'%' \n");
					}
				}else {//전체
					
				}
				
				StringBuilder sb=new StringBuilder();		
				sb.append(" SELECT  T.u_id,                                  \n"); 
				sb.append(" 		T.name,                                  \n"); 
				sb.append(" 		T.password,                              \n"); 
				sb.append(" 		T.h_level,                               \n"); 
				sb.append(" 		T.login,                                 \n"); 
				sb.append(" 		T.recommend,                             \n"); 
				sb.append(" 		T.email,                                 \n"); 
				sb.append(" 		TO_CHAR(T.reg_dt,'YYYY-MM-DD') reg_dt,   \n"); 
				sb.append(" 		T.total_cnt,                             \n"); 
				sb.append(" 		T.rnum AS no                             \n"); 
				sb.append("   FROM(                                          \n"); 
				sb.append(" 	SELECT hr.*                                  \n"); 
				sb.append(" 		  ,ROW_NUMBER() OVER(ORDER BY u_id ) AS rnum  \n"); 
				sb.append(" 		  ,COUNT(*) OVER() AS total_cnt          \n"); 
				sb.append(" 	  FROM hr_users hr                           \n");
				//검색조건
				sb.append(param.toString());
				//--검색조건
				sb.append("   ) T                                            \n"); 
				sb.append("   WHERE rnum BETWEEN (? * (?-1)+1 )  \n"); 
				sb.append("                  AND (? * (?-1)+?  ) \n"); 
				//sb.append("   WHERE rnum BETWEEN (:PAGE_SIZE * (:PAGE_NUM-1)+1 ) \n"); 
				//sb.append("                  AND (:PAGE_SIZE * :PAGE_NUM-1)+:PAGE_SIZE) \n");
				
				
				log.info("1.sql: \n"+sb.toString());
				//-------------------------------------
				//2. param set: 검색조건 분기
				//-------------------------------------
				
				List<Object> listArgs = new ArrayList<Object>();
				
				if(null !=searchVO && !"".equals(searchVO.getSearch_div()) ) {
					listArgs.add(searchVO.getSearch_word());	
					listArgs.add(searchVO.getPage_size());	
					listArgs.add(searchVO.getPage_num());
					listArgs.add(searchVO.getPage_size());
					listArgs.add(searchVO.getPage_num());
					listArgs.add(searchVO.getPage_size());	
				}else {//전체
					listArgs.add(searchVO.getPage_size());	
					listArgs.add(searchVO.getPage_num());
					listArgs.add(searchVO.getPage_size());
					listArgs.add(searchVO.getPage_num());
					listArgs.add(searchVO.getPage_size());
				}
				return this.jdbcTemplate.query(sb.toString(),listArgs.toArray()
						, this.userMapper);
	}	
	
	

}





