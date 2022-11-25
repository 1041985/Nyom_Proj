<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Restoran - Bootstrap Restaurant Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="resourcesimg/favicon.ico" rel="icon">

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
.col-md-6 {
	    margin: 0 auto;
	}

</style>

<script type="text/javascript">

</script>
<body>
	<div class="container-xxl bg-white p-0">
		<!-- Spinner Start -->
		<div id="spinner"
			class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
			<div class="spinner-border text-primary"
				style="width: 3rem; height: 3rem;" role="status">
				<span class="sr-only">Loading...</span>
			</div>
		</div>
		<!-- Spinner End -->

		<%@ include file="top.jsp"%>

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

	<!-- Contact Start -->
	<div class="container-xxl py-5">
		<div class="container">
			<div class="text-center wow fadeInUp" data-wow-delay="0.1s">
				<h5
					class="section-title ff-secondary text-center text-primary fw-normal">Admin</h5>
				<h2 class="mb-5">업체 리스트</h2>

				<div class="container">
					<div align="right">

						<table class="table table-hover">
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">상호명</th>
									<th scope="col">이미지</th>
									<th scope="col">업체 관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="fs" items="${lists }">
								<tr>
									<th scope="row">${fs.store_no }</th>
									<td><a href="adminDetail.fs?store_no=${fs.store_no }">${fs.store_name }</a></td>
									<td><a href="adminDetail.fs?store_no=${fs.store_no }"><img src="${fullpath}/${fs.store_img }" width="80" height="80"></a></td>
									<td>
									<div class="dropdown">
                          				<button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                            			<i class="bx bx-dots-vertical-rounded"></i>
                          				</button>
                          			<div class="dropdown-menu">
                            		<a class="dropdown-item" href="adminUpdate.fs?store_no=${fs.store_no }">
                            		<i class="bx bx-edit-alt me-1"></i> 수정
                           			</a>
                            		<a class="dropdown-item" href="adminDelete.fs?store_no=${fs.store_no }"> 
                            		<i class="bx bx-trash me-1"></i> 삭제
                           			 </a>
                         			 </div>
                       				</div>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Contact End -->


	<%@ include file="bottom.jsp" %>