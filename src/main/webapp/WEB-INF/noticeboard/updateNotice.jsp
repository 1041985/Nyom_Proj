<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/foodstore/top.jsp"  %>
		
		
		<br><br><br><br>
	
<body>
            <!-- Reservation Start -->
        <div class="container-xxl py-5 px-0 wow fadeInUp" data-wow-delay="0.1s">
            <div class="row g-0">
                <div class="col-md-1">

                </div>
                <div class="col-md-10 bg-dark d-flex align-items-center">
                    <div class="p-5 wow fadeInUp" data-wow-delay="0.2s">
                        <h5 class="section-title ff-secondary text-start text-primary fw-normal">Notice update</h5>
                        <h1 class="text-white mb-4">공지사항 수정</h1>
                        <form method="post" action="update.not" enctype="multipart/form-data">
                              	<input type="hidden" name="no" value=${notice.no }>
     						 	<input type="hidden" name="pageNumber" value=${pageNumber }>
      							<input type="hidden" name="originalImg" value=${notice.image }> <!-- 예전이미지담김 -->
                           <div class="row g-3">                                
                            <div class="col-md-12">
                               	<div class="form-floating">
                                        <input type="text" class="form-control" id="subject" name="subject" placeholder="제목" value="${notice.subject}">
                                        <label for="subject">제목</label>
                                    </div>
                                </div>
                            
                                <div class="col-md-12">
                                    <div class="form-floating">
                                        <input type="text" class="form-control" id="writer" name="writer" placeholder="작성자" value="${notice.writer}" readonly="readonly">
                                        <label for="writer">작성자</label>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-floating date" id="date3" data-target-input="nearest">
                                        <input type="text" class="form-control datetimepicker-input" id="reg_date" name="reg_date" placeholder="Date & Time"
                                        	value="${notice.reg_date}" data-target="#date3" data-toggle="datetimepicker" />
                                        <label for="reg_date">작성일</label>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-floating">
                                        <select class="form-select" id="select1" name="open">
                                          <option value="전체공개" <c:if test="${notice.open eq '전체공개'}"> selected </c:if> >전체공개</option>
                                          <option value="비공개" <c:if test="${notice.open eq '비공개'}"> selected </c:if> >비공개</option>
                                        </select>
                                        <label for="select1">공개여부</label>
                                      </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-floating">
                                    	<img src="" >
                                        <input type="file" class="form-control" name="upload" id="image" placeholder="이미지" value="${notice.image}">
                                        <label for="upload">이미지파일</label>
                                    </div>
                                </div>                                
                                <div class="col-12">
                                    <div class="form-floating">
                                        <textarea class="form-control" placeholder="content" id="content" name="content" value="${notice.content}" style="height: 200px"></textarea>
                                        <label for="content">${notice.content}</label>
                                    </div>
                                </div>

                                
                                <div class="col-12">
                                    <button class="btn btn-primary w-100 py-3" type="submit">수정하기</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <!-- Reservation Start -->
</body>        
        
<%@ include file="/WEB-INF/foodstore/bottom.jsp"  %>