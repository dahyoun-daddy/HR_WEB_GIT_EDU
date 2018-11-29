package com.sist.hr.file.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.hr.common.DTO;
import com.sist.hr.file.dao.FileDao;
import com.sist.hr.file.domain.FileVO;

@Service
public class FileSvcImple implements FileSvc {
	private final Logger  LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileDao fileDao;

	@Override
	public int do_saveTx(List<FileVO> list) {
		int flag = 0;
		
		for(FileVO vo  : list) {
			flag+=fileDao.do_save(vo);
		}
		LOG.info("S=============================");
		LOG.info("flag="+flag);
		LOG.info("S=============================");
		return flag;
	}
	
	
	@Override
	public DTO do_selectOne(DTO dto) {
		return fileDao.do_selectOne(dto);
	}

	@Override
	public int do_save(DTO dto) {
		return 0;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return fileDao.do_retrieve(dto);
	}



}
