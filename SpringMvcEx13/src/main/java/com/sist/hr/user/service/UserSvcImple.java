package com.sist.hr.user.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import com.sist.hr.common.DuplicateUserIdException;
import com.sist.hr.common.SearchVO;
import com.sist.hr.user.dao.UserDao;
import com.sist.hr.user.domain.Level;
import com.sist.hr.user.domain.UserVO;

@Service
public class UserSvcImple implements UserSvc {
    private Logger log = LoggerFactory.getLogger(UserSvcImple.class);
	public static final int MIN_SILVER_LOGIN_COUNT   = 50;//BASIC->SILVER
	public static final int MIN_GOLD_RECOMMAND_COUNT = 30;//SILVER->GOLD
	
	
	@Autowired
	private UserDao userDao;
	
	
	//업그레이드 가능 확인 
	private boolean isCanUpgradeLevel(UserVO user) {
		//현제 Level
		Level curLevel = user.getLevel();
		switch(curLevel) {
			case BASIC : return (user.getLogin()     >= MIN_SILVER_LOGIN_COUNT);
			case SILVER: return (user.getRecommend() >= MIN_GOLD_RECOMMAND_COUNT);
			case GOLD: return false;
			default: throw new IllegalAccessError("Unknown Level:"+curLevel);
		}
	}
	
	//레벨 업그래이드 실행
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#upgradeLevel(com.sist.hr.user.domain.UserVO)
	 */
	@Override
	public void upgradeLevel(UserVO user) throws SQLException,RuntimeException {

		user.upgradeLevel();
		userDao.update(user);
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#upgradeLevels()
	 */
	@Override
	public void upgradeLevels() throws EmptyResultDataAccessException, ClassNotFoundException, SQLException,RuntimeException {
		//-------------------------------------------
		//1.전체 사용자를 조회 한다.
		//-------------------------------------------
		List<UserVO> list = userDao.getAll();

		int upLevelCnt = 0;
		try {
			for(UserVO user:list) {
				if("j04_115".equals(user.getU_id())) {		
					throw new RuntimeException("레벨 업그래이드오류");
				}
				  
				if(isCanUpgradeLevel(user) == true) {
					upLevelCnt++;
					upgradeLevel(user);
				}
			}//--for
			
		}catch(RuntimeException e) {
			log.debug("========================");
			log.debug("RuntimeException: "+e.getMessage());
			log.debug("========================");

			throw e;
		}finally {
			//TODO
			//-----------------------------------
			//자원반납
			//-----------------------------------
			
			
		}
		//-------------------------------------------
		//2.2. 트랜잭션처리:commit,rollback
		//-------------------------------------------
		
		log.debug("========================");
		log.debug("commit="+upLevelCnt);
		log.debug("========================");
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#delete(com.sist.hr.user.domain.UserVO)
	 */
	@Override
	public int delete(UserVO userVO) throws SQLException{
		return userDao.delete(userVO);
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#update(com.sist.hr.user.domain.UserVO)
	 */
	@Override
	public int update(UserVO userVO) throws SQLException{
		return userDao.update(userVO);
	}
	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String userId) throws SQLException{
		return userDao.getCount(userId);
	}



	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#add(com.sist.hr.user.domain.UserVO)
	 */
	@Override
	public int add(UserVO userVO) throws DuplicateUserIdException{
		//TODO: return 
		if(null == userVO.getLevel())userVO.setLevel(Level.BASIC);
		return userDao.add(userVO);
	}

	
	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#do_retrieve(com.sist.hr.common.SearchVO)
	 */
	@Override
	public List<UserVO> do_retrieve(SearchVO searchVO) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException{
		return userDao.do_retrieve(searchVO);
	}
	

	/* (non-Javadoc)
	 * @see com.sist.hr.user.service.UserSvc#get(com.sist.hr.user.domain.UserVO)
	 */
	@Override
	public UserVO get(UserVO userVO) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException{
		return  userDao.get(userVO);
	}

	@Override
	public int do_deleteMulti(List<UserVO> list) throws RuntimeException, SQLException {
		int flag = 0;
		try {
			for(UserVO vo :list) {
				flag+=userDao.delete(vo);
			}
			
		}catch(RuntimeException e) {
			log.debug("========================");
			log.debug("RuntimeException: "+e.getMessage());
			log.debug("========================");			
			throw e;
		}
		log.debug("========================");
		log.debug("=flag="+flag);
		log.debug("========================");
		return flag;
	}
}



