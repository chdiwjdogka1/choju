<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.choju.fpro.vo.BoardVO"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.choju.fpro.vo.PageMaker"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SZ</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="author" content="" />

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700"
	rel="stylesheet">
<!-- ----------------------------------절대 수정 하지 말것. -->
<!-- Animate.css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

<!-- Magnific Popup -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">

<!-- Flexslider  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/flexslider.css">

<!-- Owl Carousel -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">

<!-- Date Picker -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
<!-- Flaticons  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/flaticon/font/flaticon.css">

<!-- Theme style  -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<!-- Modernizr JS -->
<script src="<c:url value="/resources/js/modernizr-2.6.2.min.js" />"></script>
<!-- -------------------------------------절대 수정 하지 말것.---------------------------------------------------  -->
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->





</head>
<body>

	<div class="colorlib-loader"></div>

	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-xs-2">
							<div id="colorlib-logo">
								<a href="/fpro/">HOME</a>
							</div>
						</div>
						<div class="col-xs-10 text-right menu-1">
							<ul>
								<!-- li class="active" -->
								<li><a href="index.html">Concert & Busking</a></li>
								<!-- <li class="has-dropdown">
									<a href="shop.html">Shop</a>
									<ul class="dropdown">
										<li><a href="product-detail.html">Product Detail</a></li>
										<li><a href="cart.html">Shipping Cart</a></li>
										<li><a href="checkout.html">Checkout</a></li>
										<li><a href="order-complete.html">Order Complete</a></li>
										<li><a href="addtowishlist">Wishlist</a></li>
									</ul>
								</li> -->
								<li><a href="freeboardForm">Free Board</a></li>
								<li><a href="about">Public Board</a></li>
								<li><a href="contact.html">Customer</a></li>
								<%
									if ((String) session.getAttribute("session_id") == null) {
								%>
								<li><a href="login"> <!-- <i class="icon-shopping-cart"></i> -->
										Login
								</a></li>
								<%
									} else {
								%>
								<li><a
									href="Mypage.go?id=<%=session.getAttribute("session_id")%>">MyPage</a></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
		<aside id="colorlib-hero">
			<div class="flexslider">
				<ul class="slides">
					<li style="background-image: url(./resources/images/SZ.jpg); background-size: 30%;"/>
				</ul>
			</div>
		</aside>
	
	 <!--<form name="frm">
    <input type="hidden" name="pageNo" /> --><!-- //페이지 번호 --> 

	
		
		<div id="colorlib-featured-product" style="padding-bottom:30px;">
			<div align="center">
				<!-- class="container" -->
				<div>
					<table id=freeboard border=0
						style="cellpadding: 0; cellspacing: 0; border-top: 2px solid red; border-bottom: 2px solid red; border-left: none; border-right: none;">
						<!-- width="1000px" -->
						<tr style="height: 30px; text-align: center;" bgcolor="#d6d6d6">
							<td style="width: 75px">글번호</td>
							<td style="width: 75px">글유형</td>
							<td style="width: 600px">글제목</td>
							<td style="width: 50px">조회수</td>
							<td style="width: 100px">작성일자</td>
						</tr>
						 
						 <!-- 
							ArrayList <BoardVO> boardList = (ArrayList<BoardVO>)request.getAttribute("boardList");
						
							PageMaker pageMaker = (PageMaker)request.getAttribute("pageMaker");
							int page1 = pageMaker.getPage();
							int end1 = pageMaker.getEnd();
							int start1 = pageMaker.getStart();
							int count1 = pageMaker.getCount();
							boolean prev = pageMaker.isPrev();
							boolean next = pageMaker.isNext();
						 -->
						<c:forEach items="${FreeBoardlist}" var="board">
							<!-- for(int i=0;i<boardList.size();i++){  -->
							<tr style="height: 30px; text-align: center;">
								<td>${board.board_Num}</td> <!-- boardList.get(i).getBoard_Num() -->
								<td>${board.board_Type}</td> <!-- boardList.get(i).getBoard_Type() -->
								<td style="text-align:left;">
								<a href="boardview?board_Num=${board.board_Num}">&nbsp;&nbsp;&nbsp;${board.board_Title}</a></td> <!-- boardList.get(i).getBoard_Title()-->
								<td>${board.board_Hit}</td> <!-- boardList.get(i).getBoard_Hit()  --> 
								<td>${board.board_Date}</td> <!-- boardList.get(i).getBoard_Date() --> 
							</tr>
							<!-- 
							}   -->
						</c:forEach>
						<tr>
							<td colspan="8" style="text-align: right; height: 30px;"><a
								href="boardwriteForm">글쓰기&nbsp;&nbsp;&nbsp;</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		
		
		<!-- <div id="page">
       		<c:if test="${pageMaker.page != 1}">
           		<a href="javascript:fn_movePage(${pageMaker.prev})" >[이전]</a>
        	</c:if>
        	
       		<span>
            <c:forEach var="i" begin="${pageMaker.start}" end="${pageMaker.end}" step="1">
                <c:choose>
                    <c:when test="${i eq pageMaker.page}">
                        <a href="javascript:fn_movePage(${i})">${i}
                        </a>
                    </c:when>
                </c:choose>
            </c:forEach>
        	</span>
        	
        	<c:if test="${pageMaker.page != pageMaker.end +1 }">
            	<a href="javascript:fn_movePage(${pageMaker.next})">[다음]</a>
       		</c:if>
    </div>
    
    </form> -->
		

		<!-- 페이지 정보 출력및 페이지 링크 -->
		<div style="padding:-50px;">
		<ul class="pageUL" style="text-align:center;list-style:none;">
			<c:if test="${pageMaker.prev }">
				<li><a href='freeboardForm?page=${pageMaker.start -1}'>이전</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.start }" end="${pageMaker.end}" var="idx">
				<li class='<c:out value="${idx == pageMaker.page?'current':''}"/>' style="display:inline;font-size:15px;">
					<a href='freeboardForm?page=${idx}'>${idx}&nbsp;&nbsp;&nbsp;</a>
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next }">
				<li><a href='freeboardForm?page=${pageMaker.end +1}'>다음</a></li>
			</c:if>
		</ul>
		</div>
		
		
		<footer id="colorlib-footer" role="contentinfo">
			<div class="container">
				<div class="row row-pb-md">
					<div class="col-md-3 colorlib-widget">
						<h4>About Store</h4>
						<p>Facilis ipsum reprehenderit nemo molestias. Aut cum
							mollitia reprehenderit. Eos cumque dicta adipisci architecto
							culpa amet.</p>
						<p>
						<ul class="colorlib-social-icons">
							<li><a href="#"><i class="icon-twitter"></i></a></li>
							<li><a href="#"><i class="icon-facebook"></i></a></li>
							<li><a href="#"><i class="icon-linkedin"></i></a></li>
							<li><a href="#"><i class="icon-dribbble"></i></a></li>
						</ul>
						</p>
					</div>
					<div class="col-md-2 colorlib-widget">
						<h4>Customer Care</h4>
						<p>
						<ul class="colorlib-footer-links">
							<li><a href="#">Contact</a></li>
							<li><a href="#">Returns/Exchange</a></li>
							<li><a href="#">Gift Voucher</a></li>
							<li><a href="#">Wishlist</a></li>
							<li><a href="#">Special</a></li>
							<li><a href="#">Customer Services</a></li>
							<li><a href="#">Site maps</a></li>
						</ul>
						</p>
					</div>
					<div class="col-md-2 colorlib-widget">
						<h4>Information</h4>
						<p>
						<ul class="colorlib-footer-links">
							<li><a href="#">About us</a></li>
							<li><a href="#">Delivery Information</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Support</a></li>
							<li><a href="#">Order Tracking</a></li>
						</ul>
						</p>
					</div>

					<div class="col-md-2">
						<h4>News</h4>
						<ul class="colorlib-footer-links">
							<li><a href="blog.html">Blog</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Exhibitions</a></li>
						</ul>
					</div>

					<div class="col-md-3">
						<h4>Contact Information</h4>
						<ul class="colorlib-footer-links">
							<li>291 South 21th Street, <br> Suite 721 New York NY
								10016
							</li>
							<li><a href="tel://1234567920">+ 1235 2355 98</a></li>
							<li><a href="mailto:info@yoursite.com">info@yoursite.com</a></li>
							<li><a href="#">yoursite.com</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="copy">
				<div class="row">
					<div class="col-md-12 text-center">
						<p>

							<span class="block"> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>
									document.write(new Date().getFullYear());
								</script> All rights reserved | This template is made with <i
								class="icon-heart2" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span> <span class="block">Demo Images: <a
								href="http://unsplash.co/" target="_blank">Unsplash</a> , <a
								href="http://pexels.com/" target="_blank">Pexels.com</a></span>
						</p>
					</div>
				</div>
			</div>
		</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up2"></i></a>
	</div>
	<!-- --------------------------------절대 수정하지 말것.  -->
	<!-- jQuery -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<!-- jQuery Easing -->
	<script src="<c:url value="/resources/js/jquery.easing.1.3.js" />"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/js/jquery.easing.1.3.js" />"></script>
	<!-- Waypoints -->
	<script src="<c:url value="/resources/js/jquery.waypoints.min.js" />"></script>
	<!-- Flexslider -->
	<script src="<c:url value="/resources/js/jquery.flexslider-min.js" />"></script>
	<!-- Owl carousel -->
	<script src="<c:url value="/resources/js/owl.carousel.min.js" />"></script>
	<!-- Magnific Popup -->
	<script
		src="<c:url value="/resources/js/jquery.magnific-popup.min.js" />"></script>
	<script src="<c:url value="/resources/js/magnific-popup-options.js" />"></script>
	<!-- Date Picker -->
	<script src="<c:url value="/resources/js/bootstrap-datepicker.js" />"></script>
	<!-- Stellar Parallax -->
	<script src="<c:url value="/resources/js/jquery.stellar.min.js" />"></script>
	<!-- Main -->
	<script src="<c:url value="/resources/js/main.js" />"></script>
	<!-- ----------------------------------절대 수정 하지 말것----------------------------------------------------------------------- -->
</body>
</html>