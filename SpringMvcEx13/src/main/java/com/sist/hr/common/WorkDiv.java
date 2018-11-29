package com.sist.hr.common;

import java.util.List;
  
/**
 * 거래표준 정의
 * @author sist1
 *
 */
public interface WorkDiv {
	public DTO do_selectOne(DTO dto);      /**단건조회 */
	
	public int do_save(DTO dto);           /**등록 */
	
	public int do_update(DTO dto);         /**수정 */
	
	public int do_delete(DTO dto);         /**삭제 */
	
	public List<?> do_retrieve(DTO dto);   /**목록조회 */
	
	public int do_excelDown(DTO dto);      /**엑셀다운 */
}
