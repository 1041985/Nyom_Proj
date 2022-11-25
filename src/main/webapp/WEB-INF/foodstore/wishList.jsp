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
            
<%@ include file="top.jsp"  %>  

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
	
    <style type="text/css">

     	.img-fluid {
			width: 150px;
			height: 150px;
		} 
		.wish {
			text-align:right;
		}
		.heart_img{
			position:relative;
			z-index:2;
			top:-120px;
			right: 15px;
			width:30px;
			height:30px;
		}
		.left {
			text-align:left;
		}
		.wishlist {
			padding: 24px;
		}

    </style>
    
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript">
    function clickFullHeart(no) {
		//alert(2);
		
		//꽉찬 하트 클릭
		$.ajax({
			url : "deleteHeart.fs",
			data : {
				storeNo : no
			},
			success : function(data) {
				//alert("wishList 다시 불러오기")
				location.href="wishList.fs"
			},
			error : function() {
				alert("하트 클릭 에러")
			}
		});
		
	}
    </script>
    
       <!-- 위시리스트 Start -->
        <div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
            <div class="container">
                <div class="text-center">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Wish List</h5>
                    <h1 class="mb-5">위시 리스트</h1>
                </div>
                <div class="owl-carousel testimonial-carousel">

							<c:if test="${fn:length(storeList) eq 0 }">
								<h2 align="center"> 위시리스트가 없습니다. </h2>
							</c:if>
							<c:if test="${fn:length(storeList) > 0 }">      
                                <c:forEach var="wish"  items="${storeList}">            					

                    <div class="testimonial-item bg-transparent border rounded p-4">
                        <i class="fa fa-quote-left fa-2x text-primary mb-3"></i>
                        <p><img src="${fullpath}/${wish.store_img }" alt="Image" class="img-fluid"/></p>
                        <div class="d-flex align-items-center">
                        	<div class="wish">
		           				<a href="javascript:clickFullHeart(${wish.store_no})" id="heart_click_${wish.store_no}">
                            		<img class="img-fluid flex-shrink-0 rounded-circle" src="resources/img/free-icon-love.png" style="width: 50px; height: 50px;">
                        		</a>
                        	</div>
                        		
                            <div class="ps-3">
                                <h5 class="mb-1"><span>${wish.store_name}</span></h5>
                                <small>
                                	<span class="d-block mb-2 text-black-50">${wish.store_addr }
	                    			</span>
	                    			<span class="d-block mb-2 text-black-50">${wish.store_tel }
	                    			</span>
	                    		</small>
                            </div>
                        </div>
                    </div>

			</c:forEach>
			</c:if>

                </div>     
			</div>
		</div>

<br><br>
   
</body>

<%@ include file="bottom.jsp"  %>  