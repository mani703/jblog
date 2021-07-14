<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>JBlog</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

var listEJS = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});

var categoryEJS = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/category-template.ejs"
});

var fetch = function(){
	$.ajax({
		url: "${pageContext.request.contextPath }/${authUser.id}/category/api", 
		dataType: "json",
		type: "get",
		success: function(response){
			var html = listEJS.render(response);
			$(".admin-cat tr:first-child").after(html);
		}
	});
}

$(function(){
	$("#add").submit(function(event){
		event.preventDefault();
		
		vo = {
			name: $("#name").val(),
			desc: $("#desc").val()
		};
		
		if(vo.name == ""){
			$("#dialog-message")
				.html("<p>이름이 비어 있습니다.</p>")
				.dialog({
					modal: true,
					buttons: {
						"확인": function(){
							$(this).dialog("close");
						}
					}
				});
			return;
		} else if(vo.desc == ""){
			$("#dialog-message")
				.html("<p>설명이 비어 있습니다.</p>")
				.dialog({
					modal: true,
					buttons: {
						"확인": function(){
							$(this).dialog("close");
						}
					}
				});
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/${authUser.id}/category/api",
			dataType: "json",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(vo),
			success: function(response){
				var html = categoryEJS.render(response.data);
				$(".admin-cat tr:last-child").after(html);
			}
		})
	});
});


$(function(){
	fetch();
	
	$(document).on("click", ".admin-cat tr a", function(event){
		event.preventDefault();
		let no = $(this).data("no");
		
		$.ajax({
			url: "${pageContext.request.contextPath }/${authUser.id}/category/api/" + no,
			dataType: "json",
			type: "delete",
			success: function(response){
				$(".admin-cat tr[data-no=" + response.data + "]").remove();
				return;
			}
		});
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/admincommon.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="" method="post" id="add">
			      	<table id="admin-cat-add" >
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" id="name" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" id="desc" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input id="submit" type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table>
			      </form> 
			      
			      <div id="dialog-message" title="" style="display: none"></div>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>