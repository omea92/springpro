<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC02</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script type="text/javascript">
  	$(document).ready(function() {
  		loadList();
  	});
  	
  	function loadList() {
  		//서버와 통신 : 게시판 리스트 가져오기
  		$.ajax({
  			url : "boardList.do",
  			type : "get",
  			dataType : "json",
  			success : makeView,
  			error : function(){ alert("error"); }
  		});
  	}
  	
  	function makeView(data) {
  		var listHtml = "<table class='table table-bordered'>";
  		listHtml += "<tr>";
  		listHtml += "<td>번호</td>";
  		listHtml += "<td>제목</td>";
  		listHtml += "<td>작성자</td>";
  		listHtml += "<td>작성일</td>";
  		listHtml += "<td>조회수</td>";
  		listHtml += "</tr>";
  		
  		$.each(data, function(index, obj){
  			listHtml += "<tr>";
  	  		listHtml += "<td>" + obj.idx + "</td>";
  	  		listHtml += "<td><a href='javascript:goContent(" + obj.idx + ")'>" + obj.title + "</a></td>";
  	  		listHtml += "<td>" + obj.writer + "</td>";
  	  		listHtml += "<td>" + obj.indate + "</td>";
  	  		listHtml += "<td>" + obj.count + "</td>";
  			listHtml += "</tr>";
  			  			
  			//반복문인것을 활용하여 id값에 인덱스값을 파리미터화 해서 동적 처리
  			listHtml += "<tr id='c" + obj.idx + "' style='display:none'>";
  			listHtml += "<td>내용</td>";
  			listHtml += "<td colspan='4'>";
  			listHtml += "<textarea readonly rows='7' class='form-control'>" + obj.content + "</textarea>";
  			listHtml += "<br/>";
  			listHtml += "<button class='btn btn-success btn-sm'>수정화면</button>&nbsp;";
  			listHtml += "<button class='btn btn-warning btn-sm' onclick='goDelete(" + obj.idx + ")'>삭제</button>";
  			listHtml += "</td>";
  			listHtml += "</tr>";  			
  		});
  		
  		listHtml += "<tr>";
  		listHtml += "<td colspan='5'>";
  		listHtml += "<button class='btn btn-primary btn-sm' onclick='goForm()'>글쓰기</button>";
  		listHtml += "</td>";
  		listHtml += "</tr>";
  		listHtml += "</table>";
  		$("#view").html(listHtml);
  	}
  	
  	//글쓰기 눌러야 보이게 하기
  	function goForm() {
  		$("#view").css("display", "none"); //감추기
  		$("#wfrom").css("display", "block"); //보이기
  	}
  	
  	//목록으로 돌아가기
  	function goList() {
  		$("#view").css("display", "block"); //보이기
  		$("#wfrom").css("display", "none"); //감추기
  	}
  	
  	//form안에 양식을 통채로 서버전달하여 등록
  	function goInsert() {
  		//해당 id값 가져오기
  		/* var title = $("#title").val();
  		var content = $("#content").val();
  		var writer = $("#writer").val(); */
  		
  		//serialize() : 데이터를 & 구분자로 한줄로 가져오니 form데이터 한번에 가져온다.
  		var fData = $("#frm").serialize();
  		
  		$.ajax({
  			url: "boardInsert.do",
  			type: "post",
  			data: fData,
  			success: loadList, //boardInsert.do 수행
  			error : function() {
  				alert("error");
  			}
  		});
  		
  		//초기화
  		/* $("#title").val("");
  		$("#content").val("");
  		$("#writer").val(""); */
  		$("#fclear").trigger("click");
  		
  	}
  	
  	//제목 클릭시 해당 내용 보이고 안보이고 컨트롤
  	function goContent(idx) {  		
  		if($("#c" + idx).css("display")=="none") {  			
  			$("#c" + idx).css("display", "table-row"); //tr태그안에서는 block 대신 table-row	
  		} else {
  			$("#c" + idx).css("display", "none"); //감추게
  		}	
  	}
  	
  	function goDelete(idx) {
  		$.ajax({
  			url: "boardDelete.do", //컨트롤러 호출
  			type: "get",
  			data: {"idx":idx}, //파라미터 셋팅
  			success: loadList, //작업 성공시 조회 호출
  			error: function() {
  				alert("error");
  			}
  		});
  	}
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">Board</div>
    <div class="panel-body" id="view">Panel Content</div>
    <div class="panel-body" id="wfrom" style="display:none">
   		<form id="frm"> <!-- 서버받을 경로 지정 -->
    		<table class="table">
	    		<tr>
	    			<td>제목</td>
	    			<td><input type="text" name="title" id="title" class="form-control"/></td>
	    		</tr>
	    		<tr>
	    			<td>내용</td>
	    			<td><textarea rows="7" class="form-control" name="content" id="content"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>작성자</td>
	    			<td><input type="text" name="writer" id="writer" class="form-control"/></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
	    				<button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
	    				<button type="reset" class="btn btn-warning btn-sm" id="fclear">취소</button>
	    				<button type="button" class="btn btn-warning btn-sm" onclick="goList()">리스트</button>
	    			</td>
	    		</tr>
	    	</table>
    	</form>
   	</div>
	<div class="panel-footer">인프런_스프1탄_박매일</div>
  </div>
</div>

</body>
</html>
