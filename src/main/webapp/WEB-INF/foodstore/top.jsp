<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>


	<!-- Navbar & Hero Start -->
        <div class="container-xxl position-relative p-0">
        <img class="img-fluid" src="./resources/img/검정.PNG" width="100%" height="1%">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4 px-lg-5 py-3 py-lg-0">
                <a href="" class="navbar-brand p-0">
                    <h1 class="text-primary m-0"><i class="fa fa-utensils me-3"></i>NYOM</h1>
                    <!-- <img src="img/logo.png" alt="Logo"> -->
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0 pe-4">
                        <a href="index.html" class="nav-item nav-link active">Home</a>
                        <a href="list.not" class="nav-item nav-link">공지사항</a>
                        <a href="qna.qna" class="nav-item nav-link">Q&A</a>
                        
                        <%-- <c:if test="${loginInfo.id eq 'admin'}"> --%>
                        <div class="nav-item dropdown">
                            <a href="admin.fs" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Admin</a>
                            <div class="dropdown-menu m-0">
                                <a href="adminInsert.fs" class="dropdown-item">업체 등록</a>
                                <a href="admin.fs" class="dropdown-item">업체 리스트</a>
                        </div>
                      </div>  
                        <%-- </c:if> --%>
                        
<!--                         <a href="contact.html" class="nav-item nav-link">Contact</a> -->
                    </div>
                    <c:if test="${loginInfo eq null}">
	                    <a href="login.mem" class="btn btn-primary py-2 px-4">Login</a> &nbsp;
	                    <a href="join.mem" class="btn btn-primary py-2 px-4">회원가입</a>
                    </c:if>
                    <c:if test="${loginInfo ne null}">
	                    <a href="logout.mem" class="btn btn-primary py-2 px-4">Logout</a> &nbsp;
	                    <a href="mypage.mem" class="btn btn-primary py-2 px-4">MyPage</a>
                    </c:if>
                </div>
            </nav>
    
    
    