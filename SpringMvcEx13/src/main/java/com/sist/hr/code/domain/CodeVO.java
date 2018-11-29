package com.sist.hr.code.domain;

import com.sist.hr.common.DTO;

public class CodeVO extends DTO {

	private String cd_id   ;    //master code id
	private String d_id    ;    //detail id
	private String d_nm    ;    //detail name
	private String use_yn  ;    //사용여부
	private String ord_num ;    //정열순서
	private String reg_id  ;    //등록자id
	private String reg_dt  ;    //등록일
	private String mod_id  ;    //수정장id
	private String mod_dt  ;    //수정일
	private String cd_nm   ;	//마스터 코드명
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CodeVO [cd_id=" + cd_id + ", d_id=" + d_id + ", d_nm=" + d_nm + ", use_yn=" + use_yn + ", ord_num="
				+ ord_num + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", mod_id=" + mod_id + ", mod_dt=" + mod_dt
				+ ", cd_nm=" + cd_nm + "]";
	}
	
	/**
	 * @return the cd_id
	 */
	public String getCd_id() {
		return cd_id;
	}
	/**
	 * @param cd_id the cd_id to set
	 */
	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}
	/**
	 * @return the d_id
	 */
	public String getD_id() {
		return d_id;
	}
	/**
	 * @param d_id the d_id to set
	 */
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	/**
	 * @return the d_nm
	 */
	public String getD_nm() {
		return d_nm;
	}
	/**
	 * @param d_nm the d_nm to set
	 */
	public void setD_nm(String d_nm) {
		this.d_nm = d_nm;
	}
	/**
	 * @return the use_yn
	 */
	public String getUse_yn() {
		return use_yn;
	}
	/**
	 * @param use_yn the use_yn to set
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * @return the ord_num
	 */
	public String getOrd_num() {
		return ord_num;
	}
	/**
	 * @param ord_num the ord_num to set
	 */
	public void setOrd_num(String ord_num) {
		this.ord_num = ord_num;
	}
	/**
	 * @return the reg_id
	 */
	public String getReg_id() {
		return reg_id;
	}
	/**
	 * @param reg_id the reg_id to set
	 */
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	/**
	 * @return the reg_dt
	 */
	public String getReg_dt() {
		return reg_dt;
	}
	/**
	 * @param reg_dt the reg_dt to set
	 */
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	/**
	 * @return the mod_id
	 */
	public String getMod_id() {
		return mod_id;
	}
	/**
	 * @param mod_id the mod_id to set
	 */
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	/**
	 * @return the mod_dt
	 */
	public String getMod_dt() {
		return mod_dt;
	}
	/**
	 * @param mod_dt the mod_dt to set
	 */
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	/**
	 * @return the cd_nm
	 */
	public String getCd_nm() {
		return cd_nm;
	}
	/**
	 * @param cd_nm the cd_nm to set
	 */
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	
	
	
}
