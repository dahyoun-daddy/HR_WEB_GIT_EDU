package com.sist.hr.file.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.hr.common.DTO;
import com.sist.hr.file.domain.FileVO;

@Repository
public class FileDaoImple implements FileDao {
    
	private final Logger  LOG=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	private RowMapper<FileVO> rowMapper =
			new RowMapper<FileVO>() {

				@Override
				public FileVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					FileVO fileVO=new FileVO();
					
					fileVO.setFileId(rs.getString("file_id"));
					fileVO.setSeq(rs.getString("seq"));
					fileVO.setOrgFileNm(rs.getString("org_file_nm"));
					fileVO.setSavePath(rs.getString("save_path"));
					fileVO.setSaveFileNm(rs.getString("save_file_nm"));
					fileVO.setFileSize(rs.getString("file_size"));
					fileVO.setWorkDiv(rs.getString("work_div"));
					fileVO.setRegId(rs.getString("reg_id"));
					fileVO.setRegDt(rs.getString("reg_dt"));
					fileVO.setModId(rs.getString("mod_id"));
					fileVO.setModDt(rs.getString("mod_dt"));					
					
					return fileVO;
				}
		
		
	};
	
	@Override
	public DTO do_selectOne(DTO dto) {
		StringBuilder sb=new StringBuilder();
		sb.append(" select  file_id,                                                             \n");
		sb.append("         seq,                                                                 \n");
		sb.append("         org_file_nm,                                                         \n");
		sb.append("         save_path,                                                           \n");
		sb.append("         save_file_nm,                                                        \n");
		sb.append("         file_size,                                                           \n");
		sb.append("         work_div,                                                            \n");
		sb.append("         reg_id,                                                              \n");
		sb.append("         case when to_char(reg_dt,'YYYY/MM/DD')=to_char(sysdate,'YYYY/MM/DD') \n");
		sb.append("              then to_char(reg_dt,'HH24:MI')                                  \n");
		sb.append("         else to_char(reg_dt,'YYYY/MM/DD') end  reg_dt ,                      \n");
		sb.append("         mod_id,                                                              \n");
		sb.append("         case when to_char(mod_dt,'YYYY/MM/DD')=to_char(sysdate,'YYYY/MM/DD') \n");
		sb.append("              then to_char(mod_dt,'HH24:MI')                                  \n");
		sb.append("         else to_char(mod_dt,'YYYY/MM/DD') end  mod_dt                        \n");
		sb.append("  from file_mng                                                               \n");
		sb.append(" where file_id = ?                                                            \n");
		sb.append("   and seq     = ?                                                            \n");
		
		FileVO inVO = (FileVO) dto;
		LOG.info("param :"+inVO);
		FileVO outVO=(FileVO) this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {inVO.getFileId(),inVO.getSeq()}
				, rowMapper);
		LOG.info("outVO :"+outVO);
		return outVO;
	}

	@Override
	public int do_save(DTO dto) {
		StringBuilder sb=new StringBuilder();
		sb.append(" INSERT INTO file_mng ( \n"); 
		sb.append("   file_id,             \n");
		sb.append("   seq,                 \n");
		sb.append("   org_file_nm,         \n");
		sb.append("   save_path,           \n");
		sb.append("   save_file_nm,        \n");
		sb.append("   file_size,           \n");
		sb.append("   work_div,            \n");
		sb.append("   reg_id,              \n");
		sb.append("   reg_dt,              \n");
		sb.append("   mod_id,              \n");
		sb.append("   mod_dt               \n");
		sb.append(" )VALUES (              \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" ?,                     \n");
		sb.append(" sysdate,               \n");
		sb.append(" ?,                     \n");
		sb.append(" sysdate                \n");
		sb.append(" )                      \n");
		
		FileVO inVO = (FileVO) dto;
		LOG.info("param :"+inVO);
		//LOG.info("sql :\n"+sb.toString());
		int flag = this.jdbcTemplate.update(sb.toString()
				, inVO.getFileId()
				, inVO.getSeq()
				, inVO.getOrgFileNm()
				, inVO.getSavePath()
				, inVO.getSaveFileNm()
				, inVO.getFileSize()
				, inVO.getWorkDiv()
				, inVO.getRegId()
				, inVO.getModId()				
				);
		LOG.info("flag :\n"+flag);
		return flag;
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
		StringBuilder sb=new StringBuilder();
		sb.append(" select  file_id,                                                             \n");
		sb.append("         seq,                                                                 \n");
		sb.append("         org_file_nm,                                                         \n");
		sb.append("         save_path,                                                           \n");
		sb.append("         save_file_nm,                                                        \n");
		sb.append("         file_size,                                                           \n");
		sb.append("         work_div,                                                            \n");
		sb.append("         reg_id,                                                              \n");
		sb.append("         case when to_char(reg_dt,'YYYY/MM/DD')=to_char(sysdate,'YYYY/MM/DD') \n");
		sb.append("              then to_char(reg_dt,'HH24:MI')                                  \n");
		sb.append("         else to_char(reg_dt,'YYYY/MM/DD') end  reg_dt ,                      \n");
		sb.append("         mod_id,                                                              \n");
		sb.append("         case when to_char(mod_dt,'YYYY/MM/DD')=to_char(sysdate,'YYYY/MM/DD') \n");
		sb.append("              then to_char(mod_dt,'HH24:MI')                                  \n");
		sb.append("         else to_char(mod_dt,'YYYY/MM/DD') end  mod_dt                        \n");
		sb.append("  from file_mng                                                               \n");
		sb.append(" where file_id = ?                                                            \n");
		
		
		FileVO inVO = (FileVO) dto;
		LOG.info("param :"+inVO);
		 
		return this.jdbcTemplate.query(sb.toString()
				, new Object[] {inVO.getFileId()}
				, rowMapper);
	}

	@Override
	public int do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
