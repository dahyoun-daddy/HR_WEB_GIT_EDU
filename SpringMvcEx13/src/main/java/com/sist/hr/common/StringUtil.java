package com.sist.hr.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sist.hr.code.domain.CodeVO; 



public class StringUtil {


	private final static Logger LOG=LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * 32bit uuid
	 * @return
	 */
	public static String getUUID() {
		String uuid ="";
		uuid = UUID.randomUUID().toString().replaceAll("-", "");
		LOG.debug("===========================");
		LOG.debug("uuid="+uuid);
		LOG.debug("===========================");
		return uuid;
	}
	
	
	/**
	 * format에 해당하는 String
	 * @param format
	 * @return
	 */
	public static String currDate(String format) {
		
		if(null==format || format.equals(""))format="yyyyMMdd";
		Date date=new Date();
		
		SimpleDateFormat  sdf=new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		
		LOG.debug("===========================");
		LOG.debug("format="+format);
		LOG.debug("dateStr="+dateStr);
		LOG.debug("===========================");
		
		return dateStr;
	}
	
	 /** << < 1 2 3 ....10 > >>
	   * Paging처리 
	   * @param maxNum_i
	   * @param currPageNoIn_i
	   * @param rowsPerPage_i
	   * @param bottomCount_i
	   * @param url_i
	   * @param scriptName_i
	   * @return
	   */
	/**
	 * 페이징
	 * @param totalCnt(총글수)
	 * @param pageNum(현재페이지)
	 * @param pageSize(한페이지에 보여질 행수)
	 * @param bottomCnt(바닥에 보여줄  페이지수)
	 * @param url(호출 URL)
	 * @param scriptNm*(호출 자바 스크립트 이름)
	 * @return html
	 */
	public static String renderPaging(int totalCnt,int pageNum,int pageSize
			,int bottomCnt,String url_i,String scriptNm){
		int maxNum     = 0;
		int currPageNo = 1;
		int rowPerPage = 10;
		int bottomCount= 10;
		
		String url        = "";
		String scriptName = "";
		
		//param value set
		maxNum      = totalCnt;
		currPageNo  = pageNum;
		rowPerPage  = pageSize;
		bottomCount = bottomCnt;
		url         = url_i;
		scriptName  = scriptNm;
		
		
		/**
		 * << 
			< 
			1 2 3 4 5 6 7 8 9 10  
			> 
			>>
		 */
		
		int maxPageNo   = ((maxNum -1) / rowPerPage) +1;
		int startPageNo = ((currPageNo-1)/bottomCount)  * bottomCount + 1;
		int endPageNo   = ((currPageNo-1)/bottomCount+1)* bottomCount;
		int nowBlockNo  = ((currPageNo-1)/bottomCount)+1;
		int maxBlockNo  = ((maxNum-1)/bottomCount)+1;
		
		int inx = 0;
		if(currPageNo > maxPageNo)return "";
		
		StringBuilder html =new StringBuilder();
		
		   html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
		   html.append("<tr>                       \n");
		   html.append("<td align=\"center\">                                                                    \n");
		   //html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
		   html.append("<nav aria-label=\"Page navigation\">");
		   html.append("<ul class=\"pagination\">");
		   // <<
		   
		   if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
		    html.append("<li>");
		    html.append("<a href=\"javascript:" + scriptName + "( '" + url+ "', 1 );\">  \n");
		    html.append("&laquo;   \n");
		    html.append("</a>      \n");
		    html.append("</li>");
		   }
		   

		   // <
		   
		   if (startPageNo > bottomCount) {
		    html.append("<li>");
		    html.append("<a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - 1)+ ");\"> \n");
		    html.append("<        \n");
		    html.append("</a>     \n");
		    html.append("</li>");
		   }
		   


		   // 1 2 3 ... 10 (숫자보여주기)
		   for (inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
		    
		    if (inx == currPageNo) {
		     html.append("<li class=\"active\">");   
		     html.append("<a href=\"javascript:" + scriptName + "('" + url + "'," + inx+ ");\" >" + inx + "</a> &nbsp;&nbsp; \n");
		     html.append("</li>");
		    } else {
		     html.append("<li>");
		     html.append("<a href=\"javascript:" + scriptName + "('" + url + "'," + inx+ ");\" >" + inx + "</a> &nbsp;&nbsp; \n");
		     html.append("</li>");
		    }
		   }
		   
		   // >
		   if (maxPageNo >= inx) {
		    html.append("<li>"); 
		    html.append("<a href=\"javascript:" + scriptName + "('" + url + "',"+ ((nowBlockNo * bottomCount) + 1) + ");\"> \n");
		    html.append(">                       \n");
		    html.append("</a>              \n");
		    html.append("</li>");
		   }

		   // >>
		   if (maxPageNo >= inx) {
		    html.append("<li>");
		    html.append("<a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo+ ");\">      \n");
		    html.append("&raquo;     \n");
		    html.append("</a>    \n");
		    html.append("</li>");
		   }

		   html.append("</td>   \n");
		   html.append("</tr>   \n");
		   html.append("</ul>");
		   html.append("</nav>");
		   html.append("</table>   \n");      

		   return html.toString();
		  
	}
	  
	public static String makeSelectBox(List<CodeVO> list,String selectCode,String selectNm,boolean allYN){
		
//		<select name="page_size" id="page_size">
//			<option value="10" <%if(page_size.equals("10"))out.print("selected='selected'"); %> >10</option>
//			<option value="20" <%if(page_size.equals("20"))out.print("selected='selected'"); %> >20</option>
//			<option value="50" <%if(page_size.equals("50"))out.print("selected='selected'"); %> >50</option>
//		</select>
		StringBuilder sb=new StringBuilder();
		sb.append("<select name='"+selectNm+"' class='form-control input-sm'>\n");		
		//전체
		if(true == allYN){
			sb.append("<option value=''>::전체::</option> \n");
		}
		//내용
		for(DTO in:list){
			CodeVO dto = (CodeVO) in;
			sb.append("<option value='"+dto.getD_id()+"'"   );
			//selected
			if(selectCode.equals(dto.getD_id())){
				sb.append(" selected='selected' ");
			}
			sb.append(">");
			sb.append(dto.getD_nm());
			sb.append("</option> \n");
				
		}
		
		sb.append("</select> \n");
		return sb.toString();
	}
	
	/**
	 * String NVL(str,defVal)
	 * @param str
	 * @param defVal
	 * @return String
	 */
	public static String nvl(Object str,String defVal){
		String retStr = "";
		
		if(null == str){
			retStr = defVal;
		}else{
			retStr = str.toString().trim();
		}
		
		return retStr;
	}
}
