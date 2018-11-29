<%@page import="com.sist.hr.common.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%
	String context = request.getContextPath();//context path
%>	
<%-- 
  /**
  * @Class Name  : user.jsp
  * @Description : 사용자관리 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.11.22             최초 생성
  *
  * author HR 개발팀
  * since 2018.11.22
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%> 
    
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>:::파일입력:::</title>

    <!-- 부트스트랩 -->
    <link href="<%=context%>/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    

  </head>
  <body>
	<h2>File</h2>
	<hr/>
	<!-- contents -------------------------------------------------------->
    <div class="container">
		<div class="col-lg-12"></div>
		<div class="col-lg-12"></div>
		<div class="panel panel-default"></div>
		<div class="panel-heading text-center">파일입력</div>
		
		<div class="form-group text-right">
			<button id="addBtn" class="btn btn-success btn-sm">추가</button>
        </div>	
        		
		<form name="fileForm" id="fileForm" method="post" 
		      enctype="multipart/form-data"
		      action="file.do" class="form-horizontal">
			  <input type="hidden"  name="work_div" id="work_div" value="com">
			  <div class="form-group" id="fileDiv">
			  		<input type="file" name="file1" id="file1"
			  		  class="form-control input-sm" placeholder="파일"/>  	
			  </div>
		</form>
        <button class="btn btn-success btn-sm" onclick="do_save_file();">업로드</button>

		<!-- File Download Form -->
		<form name="fileDown" id="fileDown" method="post"
			      action="download.do" class="form-horizontal">
			  <input type="hidden"  name="file_id" id="file_id" >
			  <input type="hidden"  name="seq"     id="seq" >
	    </form>		
	    <!--// File Download Form -->
	    
	    
		<div class="container">
			<table id="listTable" 
			          class="table table-striped table-hover table-bordered">
		    <thead>
		        <tr>    
		            <th class="text-center">ID</th>
		            <th class="text-center">순번</th>
		            <th class="text-center">원본파일명</th>
		            <th class="text-center">등록일</th>
		            <th class="text-center">등록자</th>
		            <th class="text-center">사이즈</th>
		            <th class="text-center">업무구분</th>
		        </tr>
		    </thead>
		    <tbody>
		    	<c:choose>
		    		<c:when test="${list.size()>0}">
		    			<c:forEach var="userVO" items="${list}">
		    				<tr>
		    					<td class="text-center"><c:out value="${userVO.fileId}" /></td>
		    					<td class="text-center"><c:out value="${userVO.seq}" /></td>
		    					<td class="text-center"><c:out value="${userVO.orgFileNm}" /></td>
		    					<td class="text-center"><c:out value="${userVO.regDt}" /></td>
		    					<td class="text-center"><c:out value="${userVO.regId}" /></td>
		    					<td class="text-center"><c:out value="${userVO.fileSize}" /></td>
		    					<td class="text-center"><c:out value="${userVO.workDiv}" /></td>
		    				</tr>
		    			</c:forEach>
		    		</c:when>
		    		<c:otherwise>
			    		<tr>
			    			<td class="text-center" colspan="99">등록된 게시물이 없습니다.</td>
			    		</tr>
		    		</c:otherwise>
		    	</c:choose>
		    </tbody>
		</div>
		
		
	</div>
	<!--// contents -------------------------------------------------------->
      
		      
		      	 
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    
    <script src="<%=context%>/resources/js/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="<%=context%>/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function do_save_file(){
        	//alert('do_save_file');
        	var frm = document.fileForm;
        	frm.submit();
        }
        var fileNumber = 2;
    	$(document).ready(function(){
    		$("#listTable>tbody").on("click","tr",function(){
    			//alert('listTable>tbody');
    			var tr = $(this);
    			var td = tr.children();
    			var fileId = td.eq(0).text();
    			var seq = td.eq(1).text();
    			
    			console.log("fileId:"+fileId);
    			console.log("seq:"+seq);
    			
    			if(""==fileId || ""==seq)return;
    			
    			var frm = document.fileDown;
    			frm.file_id.value = fileId;
    			frm.seq.value = seq;
    			frm.submit();
    			
    		});//--listTable>tbody
    		
    		

    		
    		
    		//alert('document');
    		$("#addBtn").on("click",function(){
    			//alert('addBtn');
    			var tmpHtml  = "";
    			    tmpHtml += "<input class='form-control input-sm' placeholder='파일' type='file' name='file"+fileNumber+"'" +" id='file"+fileNumber+"' />";
    			    tmpHtml += "<button  class='btnDel btn btn-danger btn-sm'>삭제</button>"
    			console.log("tmpHtml\n"+tmpHtml);  
    			$("#fileDiv").append(tmpHtml);
    			fileNumber++;
    			
    			$(".btnDel").on("click",function(){
    				$(this).prev().remove();
    				$(this).remove();
    			});
    			
    		});
    	}); //-- document 
    </script>	
</body>
</html>





