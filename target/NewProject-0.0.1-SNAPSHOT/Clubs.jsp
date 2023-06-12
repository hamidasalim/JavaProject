<%@page import="java.util.List"%>
<%@page import="dao.entities.Club"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Liste Des Clubs</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
        crossorigin="anonymous">
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
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h2 class="h2">Liste Des Clubs</h2>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="Controller?action=addClub" class="btn btn-primary">Ajouter Club</a>
                    <c:if test="${role == 'admin'}">
                    <a href="Controller?action=affecterUserClub" class="btn btn-primary">Affecter User</a>
                    </c:if>
                </div>
            </div>

            <form action="Controller?action=postrechercherClub" method="post">
                <div class="input-group mb-3">
                    <input type="text" name="rech" class="form-control" placeholder="Recherche Par Nom Club">
                    <button type="submit" class="btn btn-secondary">Rechercher</button>
                    </div>
			</form>
			        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Nom Club</th>
                    <th>Description</th>
                    <th>Fculté</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%-- Loop through clients for the current page --%>
                <c:forEach var="p" items="${listeC}">
                    <tr>
                        <td>${p.nomClub}</td>
                        <td>${p.descriptionClub}</td>
                        <td>${p.faculte.nomFac}</td>
                        <td>
                            <a href="Controller?action=modifierClub&id=${p.id}" class="btn btn-primary">Modifier</a>
                            <a onclick="return confirm('Voulez vous vraiment supprimer ce Club?')"
                                href="Controller?action=supprimerClub&id=${p.id}" class="btn btn-danger">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Pagination links -->
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}">&laquo;</a></li>
            </c:if>
            <c:forEach var="page" begin="1" end="${totalPages}">
                <li class="page-item ${page == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${page}">${page}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}">&raquo;</a></li>
            </c:if>
        </ul>
    </div>
</div>
			
