<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
  <div class="container">
    <a class="navbar-brand" href="/">Trang chủ</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <c:forEach var="category" items="${categories}">
          <li class="nav-item">
            <a class="nav-link" href="/category/${category.code}">${category.name}</a>
          </li>
        </c:forEach>
        <c:choose>
          <c:when test="${empty USER_MODEL}">
            <li class="nav-item">
              <a class="nav-link" href="/login">Đăng nhập</a>
            </li>
          </c:when>
          <c:otherwise>'
            <c:if test="${USER_MODEL.role.code == 'admin'}">
              <li class="nav-item">
                <i class="ace-icon fa fa-cogs"></i>
                <a class="nav-link" href="/admin/home">Quản trị</a>
              </li>
            </c:if>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPages" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Hi ${USER_MODEL.fullName}
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPages">
                <a class="dropdown-item" href="#">Trang cá nhân</a>
                <a class="dropdown-item" href="/logout">Đăng xuất</a>
              </div>
            </li>
          </c:otherwise>
        </c:choose>

      </ul>
    </div>
  </div>
</nav>