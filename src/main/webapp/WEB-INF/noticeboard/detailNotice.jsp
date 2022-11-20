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

<style>
	#cen{
	  margin: auto;
	}
</style>
<script>
function delNotice(no,pageNumber) {
	 
    if (confirm("정말 삭제하시겠습니까?")==true) {
       
    	location.href="delete.not?no="+no+"&pageNumber="+pageNumber;
    } else {
    	location.href="detail.not?no="+no+"&pageNumber="+pageNumber;
    	return false ; 
    }
}
</script>

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
	
       <!-- 상세보기 Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Noitce ${notice.no}</h5>
                    <h1 class="mb-5">${notice.subject}</h1>
                </div>
                 
                <div class="tab-class text-center wow fadeInUp" data-wow-delay="0.1s">

                            <div class="row g-2">
                            
                            	<div class="col-lg-2"></div>
                                <div class="col-lg-8">
  
                                    <div class="d-flex align-items-center">
									
                                        <div class="w-100 d-flex flex-column text-start ps-4">
                                        	<small class="fst-italic">
                                            	<span style="display:inline-block; width:90%; background-color:white">글쓴이: ${notice.writer } </span>
                                            	<span>조회수: ${notice.readcount}</span>  
                                            </small><br>
                          					<h5 class="d-flex justify-content-between border-bottom pb-2">
                                                <span>
                                                	<c:if test="${notice.image !=  null }">
                                                		<img height=auto width=800 
                                                			src="<%=request.getContextPath()%>/resources/${notice.image}">
                                                	</c:if>	
                                                	<c:if test="${notice.image ==  null }">
                                                	</c:if>
                                                </span>
                                            </h5>
                                            <h5 class="d-flex justify-content-between border-bottom pb-2">
                                            	    <span class="fst-italic"> ${notice.content}</span>
                                            </h5> 
                                            <small class="fst-italic"> 
                                            	<span style="display:inline-block; width:91%; background-color:white"> 
                                            		<fmt:parseDate var="notice_date" value="${notice.reg_date}" pattern="yyyy-MM-dd"/>
                                            		<fmt:formatDate var="reg_date" value="${notice_date}" pattern="yyyy-MM-dd"/>
                                            	${reg_date}
                                            	</span>
                                            <c:if test ="${loginInfo.member_id eq 'admin'}">  
                                            	<span >${notice.open}</span>
                                            </c:if>
                                            </small><br>
                                         </div>
                                        </div>                                   

                                    </div>                                         
                             	 <div class="col-lg-1"></div>
                                </div>
                            </div>

							<c:if test="${loginInfo.member_id eq 'admin'}">		
								<div class="col-lg-3" id="cen">
									<a href="list.not?pageNumber=${pageNumber }" class="btn btn-success py-2 px-4">목록</a>
        		            		<a href="update.not?no=${notice.no}&pageNumber=${pageNumber}" class="btn btn-primary py-2 px-4">수정</a> 
        	    	        		<a  class="btn btn-danger py-2 px-4" onclick="delNotice(${notice.no},${pageNumber});" >삭제</a> 
							</c:if>	
							<c:if test="${loginInfo.member_id ne 'admin'}">	
								<div class="col-lg-1" id="cen">
									<a href="list.not?pageNumber=${pageNumber }" class="btn btn-success py-2 px-4">목록</a>
							</c:if>
            	      </div>                          
                </div>
        
</body>          
        
<%@ include file="/WEB-INF/foodstore/bottom.jsp"  %>