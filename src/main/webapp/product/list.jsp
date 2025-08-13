<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 태그 라이브러리 사용을 위한 설정 : 아래 uri는 톰캣 10,11버전 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TBL_Product 상품</title>
</head>
<body>
	<h2>상품 목록</h2>
	<h3>카테고리 검색</h3>
	<ul>
		<li><a href="Search?category=A1">A1</a></li>
		<li><a href="Search?category=A2">A2</a></li>
		<li><a href="Search?category=B1">B1</a></li>
		<li><a href="Search?category=B2">B2</a></li>
	</ul>
	<div >
	<h3 style="margin-bottom: 0px;">키워드 검색</h3>
	<!-- method="get" : 파라미터는 URO에 추가되어 전달된다.
		method="post" : 요청의 바디(본문)에 포함
						개발자 도구 네트워크 탭 - 페이로드에서 확인!!
	 -->
		<form action="Search" method="get">
			<input name="keyword" placeholder="상품 검색어 입력하세요." value="${param.keyword }">
			<button type="submit">검색</button>
		</form>
	</div>
	<div>
		<button onclick="location.href='Products'">전체 목록</button>
		<!-- core 태그 if : test에 조건식 사용합니다. empty는 문자열 메소드 중에 
		null과 빈문자열 검사합니다. -->
		<c:if test="${not empty param.category }">
		검색 카테고리 :  ${param.category }
		</c:if>
		<!--$ { } 는 EL 입니다. 수식/메소드 실행 결과값 출력
		param은 EL이 파라미터를 저장하는 객체입니다.
		  -->
	</div>
	<hr/>
	<table>
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>상품명</th>
			<th>상품코드</th>
			<th>가격</th>
		</tr>
		
		
		<!-- 컬렉션 데이터를 하나씩 가져와서 tr태그에 출력하는 반복 
		c로 시작하는 태그는 반복, 조건 등의 핵심(core) 구문을 지원.-->
		<c:forEach var="dto" items="${list }" varStatus="status">
		<!-- items의 $ {    } 기호는 list 애트리뷰트값 가져옵니다. -->
		<tr>
			<td>${status.count }</td>
			<td>${dto.pcode }</td>
			<td>${dto.category }</td>
			<td>${dto.pname }</td>
			<%-- <td>${dto.price }</td> --%>
			<td>
			<!-- fmt로 시작하는 태그는 출력 형식(format) 지정. -->
				<fmt:formatNumber value="${dto.price }" type="number"/>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>