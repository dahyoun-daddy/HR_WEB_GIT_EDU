package com.sist.hr.user.domain;

import com.sist.hr.common.DTO;

public class UserVO extends DTO{


	
//-------------------------------	
	
	/**
	 * @return the level
	 */
	public Level getLevel() {
		return level;
	}



	/**
	 * @param level the level to set
	 */
	public void setLevel(Level level) {
		this.level = level;
	}



	/**
	 * @return the login
	 */
	public int getLogin() {
		return login;
	}



	/**
	 * @param login the login to set
	 */
	public void setLogin(int login) {
		this.login = login;
	}



	/**
	 * @return the recommend
	 */
	public int getRecommend() {
		return recommend;
	}



	/**
	 * @param recommend the recommend to set
	 */
	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}



	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**사용자ID */
	private String u_id;
	
	/**이름 */
	
	private String name;
	
	/**비번 */
	private String password;
	
	/**등급 */
	private Level level;

	/**로그인 횟수 */
	private int login;

	/**추천 횟수 */
	private int recommend;

	/**이메일 */
	private String email;

	/**등록일 */
	private String regDt;	
	
	
	/**
	 * @return the u_id
	 */
	public String getU_id() {
		return u_id;
	}

	
	  
	public UserVO() {}
	






	public UserVO(String u_id, String name, String password, Level level, int login, int recommend, String email,
			String regDt) {
		super();
		this.u_id = u_id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.recommend = recommend;
		this.email = email;
		this.regDt = regDt;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserVO [u_id=" + u_id + ", name=" + name + ", password=" + password + ", level=" + level + ", login="
				+ login + ", recommend=" + recommend + ", email=" + email + ", regDt=" + regDt + "]";
	}



	/**
	 * @param u_id the u_id to set
	 */
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void upgradeLevel() {
		Level nextLevel = this.level.nextLevel();
		if(null == nextLevel) {
			throw new IllegalStateException(this.level+" 은 업그레이드가 불가능 합니다.");
		}
		
		this.level = nextLevel;
		
	}
	
	
}
