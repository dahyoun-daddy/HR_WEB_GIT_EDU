package com.sist.hr.file.service;

import java.util.List;

import com.sist.hr.common.DTO;
import com.sist.hr.file.domain.FileVO;

public interface FileSvc {

	public int do_saveTx(List<FileVO> list);
	
	public DTO do_selectOne(DTO dto);
	public int do_save(DTO dto);
	public int do_update(DTO dto);
	public int do_delete(DTO dto);
	public List<?> do_retrieve(DTO dto);

}
