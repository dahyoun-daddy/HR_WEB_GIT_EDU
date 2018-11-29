package com.sist.hr.file.domain;

import com.sist.hr.common.DTO;

public class FileVO extends DTO {

	private String fileId      ;//파일ID  
	private String seq         ;//파일순번
	private String orgFileNm   ;//원본파일명
	private String savePath    ;//저장경로
	private String saveFileNm  ;//저장파일명
	private String fileSize    ;//파일사이즈
	private String workDiv     ;//업무구분
	private String regId       ;//등록자
	private String regDt       ;//등록일
	private String modId       ;//수정자
	private String modDt       ;//수정일
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileVO [fileId=" + fileId + ", seq=" + seq + ", orgFileNm=" + orgFileNm + ", savePath=" + savePath
				+ ", saveFileNm=" + saveFileNm + ", fileSize=" + fileSize + ", workDiv=" + workDiv + ", regId=" + regId
				+ ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + "]";
	}
	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
	/**
	 * @return the orgFileNm
	 */
	public String getOrgFileNm() {
		return orgFileNm;
	}
	/**
	 * @param orgFileNm the orgFileNm to set
	 */
	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}
	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}
	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	/**
	 * @return the saveFileNm
	 */
	public String getSaveFileNm() {
		return saveFileNm;
	}
	/**
	 * @param saveFileNm the saveFileNm to set
	 */
	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}
	/**
	 * @return the fileSize
	 */
	public String getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the workDiv
	 */
	public String getWorkDiv() {
		return workDiv;
	}
	/**
	 * @param workDiv the workDiv to set
	 */
	public void setWorkDiv(String workDiv) {
		this.workDiv = workDiv;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
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
	/**
	 * @return the modId
	 */
	public String getModId() {
		return modId;
	}
	/**
	 * @param modId the modId to set
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}
	/**
	 * @return the modDt
	 */
	public String getModDt() {
		return modDt;
	}
	/**
	 * @param modDt the modDt to set
	 */
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	
	
}
