<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid mx-auto">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Gestion Des Events</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                    <!-- <c:if test="${role == 'admin'}"> --> 
                        <li class="nav-item">
                            <a class="nav-link" href="Controller?action=listeFaculte">Facultés</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controller?action=listeClub">Clubs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Controller?action=listeUser">Utilisateurs</a>
                        </li>
                       <!-- </c:if>  -->
                            <li class="nav-item">
                            <a class="nav-link" href="Controller?action=listeEvent">Events</a>
                        </li>
                    </ul>
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="Controller?action=logout">Déconnexion</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card my-5">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Modifier un Utilisateur</h3>
                    <form action="Controller?action=postmodifierUser" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                        <div class="form-floating mb-3">
                            <input value="${user.username}" type="text" class="form-control" id="username" name="username" placeholder="Username">
                            <label for="username">Username :</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input value="${user.login}" type="text" class="form-control" id="login" name="login" placeholder="Login">
                            <label for="Login">Login :</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input value="${user.password}" type="text" class="form-control" id="password" name="password" placeholder="Password">
                            <label for="Password">Password :</label>
                        </div>
                        
                        <div class="form-floating mb-3">
                                   
                                <select class="form-control" id="role" name="role" value="${user.role}">
                                    <option value="admin">Admin</option>
                                    <option value="user">User</option>
                                </select>
                                <label for="role">Role :</label>
                        </div>
                        <button type="submit" class="btn btn-primary w-100 mt-4">Modifier</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
</body>
</html>
