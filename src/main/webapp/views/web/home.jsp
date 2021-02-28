<%--
  Created by IntelliJ IDEA.
  User: nttuan
  Date: 10/09/2020
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Danh sách bài viết</title>
</head>
<body>
    <!-- Page Content -->
    <div class="container">
        <h1 class="mt-4 mb-3">Danh sách bài viết</h1>
        <div class="row">
            <c:forEach var="item" items="${listNews}">
                <div class="col-lg-4 portfolio-item">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="/news/${item.title}-${item.id}">${item.title}</a>
                            </h4>
                            <p class="card-text">${item.shortDescription}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- /.row -->

        <!-- Pagination -->
<%--        <ul class="pagination justify-content-center">--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Previous">--%>
<%--                    <span aria-hidden="true">&laquo;</span>--%>
<%--                    <span class="sr-only">Previous</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#">1</a>--%>
<%--            </li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#">2</a>--%>
<%--            </li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#">3</a>--%>
<%--            </li>--%>
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#" aria-label="Next">--%>
<%--                    <span aria-hidden="true">&raquo;</span>--%>
<%--                    <span class="sr-only">Next</span>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>

    </div>
    <!-- /.container -->
</body>
</html>
