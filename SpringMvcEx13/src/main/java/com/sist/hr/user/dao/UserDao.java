package com.sist.hr.user.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.sist.hr.common.DuplicateUserIdException;
import com.sist.hr.common.SearchVO;
import com.sist.hr.user.domain.UserVO;

public interface UserDao {

	/**
	 * 단건삭제
	 */
	int delete(UserVO userVO) throws SQLException;
	
	/**
	 * 수정
	 */
	int update(UserVO userVO) throws SQLException;
	
	/**
	 * 조회 COUNT
	 * @throws SQLException 
	 */
	int getCount(String userId) throws SQLException;

	void deleteAll() throws SQLException;

	/**
	 * 추가:115
	 * @param userVO
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */

	int add(UserVO userVO) throws DuplicateUserIdException;

	List<UserVO> getAll() throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;
	List<UserVO> do_retrieve(SearchVO searchVO) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;
	

	/**
	 * 단건조회
	 * @param userVO
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	UserVO get(UserVO userVO) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;

}