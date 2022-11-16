<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Restoran - Bootstrap Restaurant Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="resources/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&family=Pacifico&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="resources/lib/animate/animate.min.css" rel="stylesheet">
    <link href="resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="resources/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="resources/css/style.css" rel="stylesheet">
</head>

<body>
    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
            
<%@ include file="/WEB-INF/foodstore/top.jsp"  %>  

<a href="update.not" class="nav-item nav-link">공지사항 수정</a>

<body>
       <!-- Menu Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Noitce list</h5>
                    <h1 class="mb-5">공지사항</h1>
                </div>
                 
                <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">
					<!-- 리스트 삭제 -->

                            <div class="row g-2">
                            
                            	<div class="col-lg-2"></div>
                                <div class="col-lg-8">
                              
                                <c:forEach var="notice"  items="${lists}">
                                    <div class="d-flex align-items-center">
									
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                          					<h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span>[공지] ${notice.subject}</span>
                                                <span class="text-primary"> ${notice.writer}</span>
                                            </h5>
                                            <small class="fst-italic">
                                        <!--    <span style="display:inline-block; width:45%; background-color:white">번호: ${notice.no}</span> 
                                            	<span style="display:inline-block; width:45%; background-color:white">조회수: ${notice.readcount}</span>  --> 
                                            	<span > 
                                            		<fmt:parseDate var="notice_date" value="${notice.reg_date}" pattern="yyyy-MM-dd"/>
                                            		<fmt:formatDate var="reg_date" value="${notice_date}" pattern="yyyy-MM-dd"/>
                                            	${reg_date}
                                            	</span>
                                            </small><br>
                                         </div>
                                        </div>                                   

                                     </c:forEach> 
                                    </div>                                         
                             	 <div class="col-lg-1"></div>
                                </div>
                            </div>
						<center>
							${pageInfo.pageNumber}	  
						</center>
						
					 <c:if test="${loginInfo.member_id eq admin}">	
						<div class="col-lg-3" style="float:right;">
        	            	<a href="insert.not" class="btn btn-primary py-2 px-4">등록</a> 
            	        </div>                          
					</c:if>	
                </div>
            </div>
        </div>
        <!-- Menu End -->
        
</body>          
        
<%@ include file="/WEB-INF/foodstore/bottom.jsp"  %>