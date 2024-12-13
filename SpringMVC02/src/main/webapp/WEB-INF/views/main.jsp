<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
  		// 서버와 통신 : 게시판 리스트 가져오기
  		// 비동기 통신 : 한번에 2가지 이상 동시 처리
  		$.ajax({
  			url : "boardList.do",
  			type : "get",
  			dataType : "json",
  			success : makeView,
  			error : function() {
  				alert("error");
  			}
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
  		
  		//index, data 반복문 처리
  		$.each(data, function(index, obj) {
  			listHtml += "<tr>";
  			listHtml += "<td>" + obj.idx + "</td>";
  			listHtml += "<td>" + obj.title + "</td>";
  			listHtml += "<td>" + obj.writer + "</td>";
  			listHtml += "<td>" + obj.indate + "</td>";
  			listHtml += "<td>" + obj.count + "</td>";
  			listHtml += "</tr>";
  		});
  		
  		listHtml += "<tr>";
  		listHtml += "<td colspan='5'>";
  		listHtml += "<button class='btn btn-primary btn-sm' onclick='goForm()'>글쓰기</button>";
  		listHtml += "</td>";
  		listHtml += "</tr>";
  		listHtml += "</table>";
  		$("#view").html(listHtml);
  		
  		$("#view").css("display", "block"); //보이기
  		$("#wfrom").css("display", "none"); //감추기
  	}
  	
  	//화면 글쓰기 폼 보이기, 감추기
  	function goForm() {
  		$("#view").css("display", "none"); //감추기
  		$("#wfrom").css("display", "block"); //보이기
  	}
  	
  	function goList() {
  		$("#view").css("display", "block"); //보이기
  		$("#wfrom").css("display", "none"); //감추기
  	}
  	
  	//등록
  	function goInsert() {
  		/* var title = $("#title").val();
  		var content = $("#content").val();
  		var writer = $("#writer").val(); */
  		
  		var fData = $("#frm").serialize(); //frm안에 파라미터 직렬화
  		//alert(fData); ex) title=111&content=111&writer=11
  		
  		$.ajax({
  			url : "boardInsert.do",
  			type : "post",
  			data : fData,
  			success : loadList,
  			error : function() { alert("error"); }
  		});
  		
  		//입력 폼 초기화
  		$("#title").val("");
  		$("#content").val("");
  		$("#writer").val("");
  	}
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="view">Panel Content</div>
    <div class="panel-body" id="wfrom" style="display:none">
    	<form id="frm">
    		<table class="table">
	    		<tr>
	    			<td>제목</td>
	    			<td><input type="text" id="title" name="title" class="form-control"/></td>
	    		</tr>
	    		<tr>
	    			<td>내용</td>
	    			<td><textarea rows="7" class="from-control" id="content" name="content"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>작성자</td>
	    			<td><input type="text" id="writer" name="writer" class="form-control"/></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
	    				<button type="button" class="btn btn-success btn-sm" onclick="goInsert()">등록</button>
	    				<button type="reset" class="btn btn-warning btn-sm">취소</button>
	    				<button type="button" class="btn btn-info btn-sm" onclick="goList()">리스트</button>
	    			</td>
	    		</tr>
	    	</table>
    	</form>
    </div>
    <div class="panel-footer">인프런_스프_정원준</div>
  </div>
</div>

</body>
</html>