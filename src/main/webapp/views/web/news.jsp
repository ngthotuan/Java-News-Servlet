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
    <title>${news.title}</title>
</head>
<body>
<div class="row">

    <!-- Post Content Column -->
    <div class="col-md-8">

        <!-- Title -->
        <h1 class="mt-4">${news.title}</h1>

        <!-- Author -->
        <p class="lead">
            by
            <a href="#">${news.createdBy}</a>
        </p>

        <hr>

        <!-- Date/Time -->
        <p>Posted on ${news.createdDate}</p>

        <hr>

        <!-- Post content -->
        <div class="news-content">
            ${news.content}
        </div>

        <br>

        <!-- Comments Form -->
        <div class="card my-4">
            <h5 class="card-header">Leave a Comment:</h5>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <textarea class="form-control" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

        <!-- Single Comment -->
        <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
            <div class="media-body">
                <h5 class="mt-0">Commenter Name</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
            </div>
        </div>

        <!-- Comment with nested comments -->
        <div class="media mb-4">
            <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
            <div class="media-body">
                <h5 class="mt-0">Commenter Name</h5>
                Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.

                <div class="media mt-4">
                    <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                    <div class="media-body">
                        <h5 class="mt-0">Commenter Name</h5>
                        Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                    </div>
                </div>

                <div class="media mt-4">
                    <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                    <div class="media-body">
                        <h5 class="mt-0">Commenter Name</h5>
                        Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante sollicitudin. Cras purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
                    </div>
                </div>

            </div>
        </div>

    </div>

    <!-- Sidebar Widgets Column -->
    <div class="col-md-4">

        <!-- Search Widget -->
        <div class="card my-4">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-append">
                <button class="btn btn-secondary" type="button">Go!</button>
              </span>
                </div>
            </div>
        </div>

        <!-- Categories Widget -->
        <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
                <div class="row">
                    <c:forEach var="category" items="${categories}">
                        <div class="col-lg-6">
                            <a href="/category/${category.code}" class="ist-unstyled mb-0">${category.name}</a>
                        </div>
                    </c:forEach>
<%--                    <div class="col-lg-6">--%>
<%--                        <ul class="list-unstyled mb-0">--%>
<%--                            <li>--%>
<%--                                <a href="#">Web Design</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">HTML</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">Freebies</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                    <div class="col-lg-6">--%>
<%--                        <ul class="list-unstyled mb-0">--%>
<%--                            <li>--%>
<%--                                <a href="#">JavaScript</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">CSS</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="#">Tutorials</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>

        <!-- Side Widget -->
        <div class="card my-4">
            <h5 class="card-header">Side Widget</h5>
            <div class="card-body">
                You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!
            </div>
        </div>

    </div>

</div>
<!-- /.row -->
</body>
</html>
