<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><dec:title default="Trang chủ" /></title>

    <!-- css -->
    <link href="<c:url value='/template/web/vendor/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="<c:url value='/template/web/css/modern-business.css' />" rel="stylesheet" type="text/css" media="all"/>
    
</head>
<body>
	<!-- header -->
    <%@ include file="/common/web/header.jsp" %>
    <!-- header -->

    <!-- Page Content -->
    <div class="container">
        <dec:body />
    </div>
    <!-- /.container -->

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp" %>
	<!-- footer -->

    <%--    jquery--%>
	<script type="text/javascript" src="<c:url value='/template/web/vendor/jquery/jquery.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/template/web/vendor/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	
</body>
</html>
