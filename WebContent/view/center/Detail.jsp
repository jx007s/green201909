<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
난 Detail다
<table border="" width="100%">
	<tr>
		<td>번호</td><td>${dto.id }</td>
	</tr><tr>
		<td>제목</td><td>${dto.title }</td>
	</tr><tr>
		<td>작성자</td><td>${dto.pname }</td>
	</tr><tr>
		<td>작성일</td><td><fmt:formatDate value="${dto.regdate }" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> </td>
	</tr><tr>
		<td>조회수</td><td>${dto.cnt }</td>
	</tr><tr>
<c:if test="${dto.upfile != null }">
	
	
		<td>파일</td><td>
		
		<c:choose>
			<c:when test="${dto.imgChk }">
				<img src="../../up/${dto.upfileEn }" alt="" />
			</c:when>
			<c:otherwise>
				<a href="FileDown?file=${dto.upfileEn }">${dto.upfile }</a>
			</c:otherwise>
		</c:choose>

		</td>
	</tr><tr>
	
</c:if>
		<td>내용</td><td>${dto.contentBr }</td>
	</tr><tr>
		<td colspan="2" align="right">
			<a href="DeleteForm?id=${dto.id }&page=${page}">삭제</a>
			<a href="ModifyForm?id=${dto.id }&page=${page}">수정</a>
			<a href="ReplyForm?id=${dto.id }&page=${page}">답변</a>
			<a href="List?page=${page }">목록으로</a>
		</td>
	</tr>
</table>
