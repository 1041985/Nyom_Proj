<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<style>
	.err{
		font-size: 9pt;
		color: #FF4848;
		font-weight: bold;
	}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>	
<script type ="text/javascript">

	$('#id').keyup(function(){
		let id = $('#id').val();
			
		$.ajax({
			url : "checkId.mem",
			type : "post",
			data : {id: id},
			dataType : 'json',
			success : function(idcheck){
				if(idcheck == 1){
					$("#id_feedback").html('이미 사용중인 아이디입니다.');
					$("#id_feedback").attr('color','#dc3545');
				} else{
					$("#id_feedback").html('사용할 수 있는 아이디입니다.');
					$("#id_feedback").attr('color','#2fb380');
				} 
			},
			error : function(){
				alert("서버요청실패");
			}
		})
			 
	})
</script>

<head>
    <meta charset="utf-8">
    <title>NYOM</title>
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

<%@ include file="../foodstore/top.jsp" %>

            <div class="container-xxl py-5 bg-dark hero-header mb-5">
            </div>
        </div>
        <!-- Navbar & Hero End -->


        <!-- Contact Start -->
        <div class="container-xxl py-5" style="width:500px;">
            <div class="container">
                <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                    <h5 class="section-title ff-secondary text-center text-primary fw-normal">Join</h5>
                    <h1 class="mb-5">회원가입</h1>
                </div>
                <div class="row g-4">
                    <div class="col-md-12">
                        <div class="wow fadeInUp" data-wow-delay="0.2s">
                            <form:form commandName="memberBean" action="join.mem" method="post">
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="text" class="form-control" name="member_id" id="id" placeholder="아이디" value="${memberBean.member_id}" required>
                                            <label for="id">아이디</label>
                                            <div><font id="id_feedback" size="2"></font></div>
                                            <form:errors cssClass="err" path="member_id"/>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="password" class="form-control" name="member_pw" id="password" placeholder="비밀번호" value="${memberBean.member_pw}" required>
                                            <label for="password">비밀번호</label>
                                            <form:errors cssClass="err" path="member_pw"/>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="text" class="form-control" name="member_name" id="name" placeholder="이름" value="${memberBean.member_name}" required>
                                            <label for="name">이름</label>
                                            <form:errors cssClass="err" path="member_name"/>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="email" class="form-control" name="member_email" id="email" placeholder="이메일" value="${memberBean.member_email}" required>
                                            <label for="email">이메일</label>
                                            <form:errors cssClass="err" path="member_email"/>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-floating">
                                            <input type="tel" class="form-control" name="member_hp" id="hp" placeholder="전화번호" value="${memberBean.member_hp}" required>
                                            <label for="hp">전화번호</label>
                                            <form:errors cssClass="err" path="member_hp"/>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <button class="btn btn-primary w-100 py-3" type="submit">회원가입</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Contact End -->

<%@ include file="../foodstore/bottom.jsp" %>