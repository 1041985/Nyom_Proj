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


        <div class="container-xxl py-5 px-0 wow fadeInUp" data-wow-delay="0.1s">
            <div class="row g-0">

                <div class="col-md-1">
                </div>
                
               <div class="col-lg-2"></div>
               	<div class="col-lg-8">  
                   		
                <div class="col-md-10 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Notice insert</h5>
                        <h1 class="text-white mb-4">공지사항 등록</h1>
                                            
                        <form method="post" action="insert.not" enctype="multipart/form-data">
                           <div class="row g-3">                                
                            <div class="col-md-12">
                               	<div class="form-floating">
                                        <input type="text" class="form-control" id="subject" name="subject" placeholder="제목" value="${notice.subject}">
                                        <label for="subject">제목</label>
                                    </div>
                                </div>
                            
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="writer" name="writer" placeholder="작성자" value="${notice.writer}">
                                        <label for="writer">작성자</label>
                                    </div>
                                </div>

<%--                                 <div class="col-md-6">
                                    <div class="form-floating date" id="date3" data-target-input="nearest">
                                        <input type="text" class="form-control datetimepicker-input" id="reg_date" name="reg_date" placeholder="Date & Time"
                                        	value="${notice.reg_date}" data-target="#date3" data-toggle="datetimepicker" />
                                        <label for="reg_date">작성일</label>
                                    </div>
                                </div> --%>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <select class="form-select" id="select1" name="open">
                                          <option value="전체공개">전체공개</option>
                                          <option value="비공개">비공개</option>
                                        </select>
                                        <label for="select1">공개여부</label>
                                      </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="file" class="form-control" name="upload" id="image" placeholder="이미지" value="${notice.image}">
                                        <label for="upload">이미지파일</label>
                                    </div>
                                </div>                                
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" placeholder="content" id="content" name="content" value="${notice.content}" style="height: 200px"></textarea>
                                        <label for="content">글내용</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">등록하기</button>
                                </div>
                            </div>
                        </form>
                    </div>
                  </div>                                         
                 <div class="col-lg-1"></div>
                </div>
                <div class="col-md-2">
                </div>
            </div>
        </div>

</body>        
        
<%@ include file="/WEB-INF/foodstore/bottom.jsp"  %>