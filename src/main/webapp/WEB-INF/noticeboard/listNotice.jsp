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

		<div class="container-xxl py-5 bg-dark hero-header mb-5">
			<div class="container text-center my-5 pt-5 pb-4">
				<h1 class="display-3 text-white mb-3 animated slideInDown">FoodStore</h1>
				<!--  <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center text-uppercase">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Pages</a></li>
                            <li class="breadcrumb-item text-white active" aria-current="page">Contact</li>
                        </ol>
                    </nav>-->
			</div>
		</div>
	</div>
	<!-- Navbar & Hero End -->
	
	<a href="wishList.fs" class="dropdown-item">위시 리스트</a>
	
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Noitce list</h5>
                    <h1 class="mb-5">공지사항</h1>
                </div>
                 
                <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">

                	<div class="row g-2">
                            
                    	<div class="col-lg-2"></div>
                   		<div class="col-lg-8">
                        
							<c:if test="${ fn:length(lists) eq 0 }">
								<h2 align="center">작성된 게시글이 없습니다.</h2>
							</c:if>
							<c:if test="${fn:length(lists) > 0 }">      
                                <c:forEach var="notice"  items="${lists}">            					

 				<c:choose>
					<c:when test="${fn:contains(loginInfo.member_id, 'admin')}">                                               
                                    <div class="d-flex align-items-center">
                                        <div class="w-100 d-flex flex-column text-start ps-4">                                                
                                           <h5 class="d-flex justify-content-between border-bottom pb-2">     
                                                <span>
                                                	<a style="color: black" href="detail.not?no=${notice.no}&pageNumber=${pageInfo.pageNumber}">
                                                		[공지] ${notice.subject}
                                                	</a>
                                                </span>
                                                <span class="text-primary"> ${notice.writer}</span>
                                            </h5>
                                            <small class="fst-italic"> 
                                            	<span style="display:inline-block; width:91%; background-color:white"> 
                                            		<fmt:parseDate var="notice_date" value="${notice.reg_date}" pattern="yyyy-MM-dd"/>
                                            		<fmt:formatDate var="reg_date" value="${notice_date}" pattern="yyyy-MM-dd"/>
                                            	${reg_date}
                                            	</span>

                                            	<span >${notice.open}</span>

                                            </small><br>
                                         </div>
                                        </div>                                   
					</c:when>
					<c:when test="${not fn:contains(loginInfo.member_id, 'admin')}">
						<c:if test="${notice.open eq '전체공개'}">
                                    <div class="d-flex align-items-center">
                                        <div class="w-100 d-flex flex-column text-start ps-4">
											<h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span>
                                                	<a style="color: black" href="detail.not?no=${notice.no}&pageNumber=${pageInfo.pageNumber}">
                                                		[공지] ${notice.subject}
                                                		
                                                		
                                                	</a>
                                                </span>
                                                <span class="text-primary"> ${notice.writer}</span>
                                            </h5>
                                            <small class="fst-italic">
                                            	<span style="display:inline-block; width:91%; background-color:white"> 
                                            		<fmt:parseDate var="notice_date" value="${notice.reg_date}" pattern="yyyy-MM-dd"/>
                                            		<fmt:formatDate var="reg_date" value="${notice_date}" pattern="yyyy-MM-dd"/>
                                            	${reg_date}
                                            	</span>

                                            </small><br>
                                         </div>
                                        </div> 	
                                        
						</c:if>
					</c:when>
				</c:choose>                                        					
                                     </c:forEach> 
                                     </c:if>
                                    </div>                                         
                             	 <div class="col-lg-1"></div>
                                </div>
                            </div>
                            
<div style="text-align:center">
    ${pageInfo.pagingHtml }
</div>
						
					 <c:if test="${loginInfo.member_id eq 'admin'}">	
						<div class="col-lg-3" style="float:right;">
        	            	<a href="insert.not" class="btn btn-primary py-2 px-4">등록</a> 
            	        </div>                          
					</c:if>	
                </div>
            </div>
        </div>
        
</body>          
        
<%@ include file="/WEB-INF/foodstore/bottom.jsp"  %>