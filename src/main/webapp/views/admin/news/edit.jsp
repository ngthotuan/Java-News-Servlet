<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nttuan
  Date: 29/09/2020
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="newsApi" value="/api/admin/news"/>
<c:url var="newsList" value="/admin/news/list"/>
<html>
<head>
    <c:choose>
        <c:when test="${not empty NEWS_MODEL.id}">
            <title>Cập nhật bài viết</title>
        </c:when>
        <c:otherwise>
            <title>Tạo bài viết mới</title>
        </c:otherwise>
    </c:choose>
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
                <li class="active">Tạo bài viết mới</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row" >
                <div class="col-xs-12">
                    <form id="newsForm">
                        <div class="form-group">
                            <label for="categoryId">Thể loại</label>
                            <select id="categoryId" class="form-control" name="categoryId">
                                <option>Chọn thể loại</option>
                                <c:forEach var="item" items="${CATEGORY_MODEL}">
                                    <option value="${item.id}" <c:if test="${item.id == NEWS_MODEL.categoryId}"> selected </c:if>>
                                            ${item.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="title">Tiêu đề</label>
                            <input type="text" class="form-control" id="title" name="title" value="${NEWS_MODEL.title}" >
                        </div>
                        <div class="form-group">
                            <label for="thumbnail">Ảnh minh họa</label>
                            <input type="text" class="form-control" id="thumbnail" name="thumbnail" value="${NEWS_MODEL.thumbnail}" >
                        </div>
                        <div class="form-group">
                            <label for="shortDescription">Mô tả ngắn</label>
                            <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${NEWS_MODEL.shortDescription}">
                        </div>
                        <div class="form-group">
                            <label for="content">Nội dung bài viết</label>
                            <textarea type="text" class="form-control" id="content" name="content">${NEWS_MODEL.content}</textarea>
                        </div>
                        <div class="text-center">
                            <c:choose>
                                <c:when test="${not empty NEWS_MODEL.id}">
                                    <button type="submit" class="btn btn-primary">Cập nhật viết</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">Tạo bài viết mới</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <input type="hidden" value="${NEWS_MODEL.id}" id="id" name="id"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->
<script>
    document.addEventListener('DOMContentLoaded', () => {
        function updateNews(data) {
            $.ajax({
                url: '${newsApi}',
                type: 'put',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (data) {
                    window.location.href = '${newsList}'
                },
                error: function (data) {
                    alert('An error occurred.');
                },
            });
        }

        function addNews(data) {
            $.ajax({
                url: '${newsApi}',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (data) {
                    window.location.href = '${newsList}'
                },
                error: function (data) {
                    alert('An error occurred.');
                },
            });
        }

        $('#newsForm').submit(function (e) {
            e.preventDefault();
            const data = {};
            const formData = $('#newsForm').serializeArray();
            $.map(formData, function(n, i){
                data[n['name']] = n['value'];
            });
            //get data tinymce
            data['content'] = tinymce.get('content').getContent();
            const id = $('#id').val();

            if(id === ""){
                addNews(data);
            }
            else{
                updateNews(data);
            }
        });
    });
</script>
</body>
</html>