<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nttuan
  Date: 12/09/2020
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">Home</a>
                </li>

                <li>
                    <a href="#">QL bài viết</a>
                </li>
                <li class="active">Danh sách bài viết</li>
            </ul><!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off">
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
                </form>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <form action="<c:url value='/admin/news/list'/>" id="pageSubmit" method="get">
                <div class="row">
                    <div class="col-xs-12">
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Bài viết</th>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Sửa đổi lần cuối</th>
                                <th>Người chỉnh sửa</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${model.listResult}">
                                <tr class="">
                                    <td>
                                        <a href="<c:url value="/news/${item.title}-${item.id}"/>">${item.title}</a>
                                    </td>
                                    <td>${item.createdDate}</td>
                                    <td>${item.createdBy}</td>
                                    <td>${item.modifiedDate}</td>
                                    <td>${item.modifiedBy}</td>
                                    <td>
                                        <div class="btn-group">
                                            <a class="btn btn-xs btn-info" href="#">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>
                                            <a class="btn btn-xs btn-danger" href="#">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation" class="center">
                            <ul class="pagination" id="pagination"></ul>
                        </nav>
                        <input id="currentPage" value="" type="hidden" name="currentPage">
                        <input id="limitItems" value="" type="hidden" name="limitItems">
                        <input id="sortBy" value="" type="hidden" name="sortBy">
                        <input id="sortType" value="" type="hidden" name="sortType">
                    </div>
                </div><!-- /.row -->
            </form>
        </div><!-- /.page-content -->
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        $(function () {
            const totalPages = ${model.totalPages};
            const limitItems = 10;
            const currentPage = ${model.currentPage};
            const sortBy = '${model.sortBy}';
            const sortType = '${model.sortType}';
            window.pagObj = $('#pagination').twbsPagination({
                totalPages: totalPages,
                visiblePages: limitItems,
                startPage: currentPage,
                first: 'Trang đầu',
                prev: '<',
                next: '>',
                last: 'Trang cuối',
                onPageClick: function (event, page) {
                    console.info(page + ' (from options)');
                    if(currentPage !== page){
                        $('#currentPage').val(page);
                        $('#limitItems').val(limitItems);
                        $('#sortBy').val(sortBy);
                        $('#sortType').val(sortType);
                        $('#pageSubmit').submit();
                    }
                }
            }).on('page', function (event, page) {
                console.info(page + ' (from event listening)');
            });
        });
    });
</script>
</body>
</html>
