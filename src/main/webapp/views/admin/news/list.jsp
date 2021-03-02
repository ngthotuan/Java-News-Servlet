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
    <title>Danh sách bài viết</title>
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
            <div class="text-center" style="margin: 10px 0px;">
                <a href="#" id="removeSelected"  class="btn btn-danger" data-toggle="modal">
                    <i class="fa fa-trash"></i>
                    <span>Xóa bài đã chọn</span>
                </a>
                <div class="modal fade" id="dialog-confirm" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header widget-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="smaller"><i class="ace-icon fa fa-exclamation-triangle red"></i> Xác nhận xóa các bài viết đã chọn?</h4>
                            </div>
                            <div class="modal-body">
                                <div class="ui-dialog-content ui-widget-content">
                                    <div class="alert alert-info bigger-110">
                                        Sau khi xóa dữ liệu sẽ không thể khôi phục
                                    </div>

                                    <div class="space-6"></div>

                                    <p class="bigger-110 bolder center grey">
                                        <i class="ace-icon fa fa-hand-o-right blue bigger-120"></i>
                                        Bạn có chắc chắn?
                                    </p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                <button type="button" id="confirmDelete" class="btn btn-danger">
                                    <i class="fa fa-trash-o"></i>
                                    Xác nhận
                                </button>
                            </div>
                        </div>
                    </div>
                </div><!-- #dialog-confirm -->
                <a href="/admin/news/edit"  class="btn btn-success">
                    <i class="fa fa-plus"></i>
                    <span>Tạo bài viết mới</span>
                </a>
            </div>
            <form action="<c:url value='/admin/news/list'/>" id="pageSubmit" method="get">
                <div class="row">
                    <div class="col-xs-12">
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" />
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">Bài viết</th>
                                <th class="center">Ngày tạo</th>
                                <th class="center">Người tạo</th>
                                <th class="center">Sửa đổi lần cuối</th>
                                <th class="center">Người chỉnh sửa</th>
                                <th class="center">Sửa bài viết</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${MODEL.listResult}">
                                <tr>
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" name="${item.id}" value="${item.id}"/>
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td class="center">
                                        <a href="<c:url value="/news/${item.title}-${item.id}"/>">${item.title}</a>
                                    </td>
                                    <td class="center">${item.createdDate}</td>
                                    <td class="center">${item.createdBy}</td>
                                    <td class="center">${item.modifiedDate}</td>
                                    <td class="center">${item.modifiedBy}</td>
                                    <td class="center">
                                        <div class="btn-group">
                                            <a class="btn btn-xs btn-info" href="<c:url value='/admin/news/edit?id=${item.id}' />">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
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
            const totalPages = ${MODEL.totalPages};
            const limitItems = 10;
            const currentPage = ${MODEL.currentPage};
            const sortBy = '${MODEL.sortBy}';
            const sortType = '${MODEL.sortType}';
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

        // const btnCheckAll = $('#checkbox-all')[0];
        //
        // btnCheckAll.click(() => {
        //     const checkItems = $("tbody tr td:first-child input");
        //     if($(this).checked){
        //         for(let i = 0; i < checkItems.length; i ++){
        //             checkItems[i].checked = true;
        //         }
        //     } else{
        //         for(let i = 0; i < checkItems.length; i ++){
        //             checkItems[i].checked = false;
        //         }
        //     }
        // });
        const active_class = "active";
        //And for the first simple table, which doesn't have TableTools or dataTables
        //select/deselect all rows according to table header checkbox
        $('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
            const th_checked = this.checked;//checkbox inside "TH" table header
            $('#removeSelected').show();
            $(this).closest('table').find('tbody > tr').each(function(){
                const row = this;
                if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#simple-table').on('click', 'td input[type=checkbox]' , function(){
            const $row = $(this).closest('tr');
            if(this.checked) $row.addClass(active_class);
            else $row.removeClass(active_class);
        });

        function deleteNews(data){
            $.ajax({
                url: '/api/admin/news',
                type: 'delete',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (data) {
                    window.location.href = '/admin/news/list'
                },
                error: function (data) {
                    alert('An error occurred.');
                },
            });
        }


        $('#removeSelected').click(e => {
            e.preventDefault();
            const data = {};
            const ids = $('#simple-table > tbody > tr > td input[type=checkbox]:checked').map(function(){return $(this).val();}).get();
            data['ids'] = ids ;
            if(ids.length === 0){
                alert("Chưa chọn bài viết nào");
            } else{
                $('#dialog-confirm').modal('show');
                $('#confirmDelete').click(() => {
                    deleteNews(data);
                });
            }
        });
    });
</script>
</body>
</html>
