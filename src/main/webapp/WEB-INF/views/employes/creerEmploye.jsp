<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="dev.paie.entite.Entreprise"%>
<%@page import="dev.paie.entite.ProfilRemuneration"%>
<%@page import="dev.paie.entite.Grade"%>
<%@page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>PAIE - Créer employé</title>
	<link rel="icon" type="image/png" sizes="32x32" href="favicon-32x32.png">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="/app.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href='<c:url value="/bootstrap-3.3.7-dist/css/bootstrap.min.css"></c:url>'>

</head>
<body>
	<div class="container">

		<form class="form-horizontal" method="POST">
			<fieldset>

				<!-- Form Name -->
				<legend>Ajouter un employé</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="matricule-input">Matricule</label>
					<div class="col-md-4">
						<input id="matricule-input" name="matricule" type="text"
							placeholder="Matricule" class="form-control input-md">
					</div>
				</div>

				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="liste-entreprises">Entreprise</label>
					<div class="col-md-4">
						<select id="liste-entreprises" name="entreprise" class="form-control">
							<c:forEach var="ent" items="${listEnt}">
								<option value="${ent.id}">${ent.denomination}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="liste-profils">Profil</label>
					<div class="col-md-4">
						<select id="liste-profils" name="profil" class="form-control">
							<c:forEach var="prof" items="${listProfils}">
								<option value="${prof.id}">${prof.code}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- Select Basic -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="liste-grades">Grade</label>
					<div class="col-md-4">
						<select id="liste-grades" name="grade" class="form-control">
							<c:forEach var="grade" items="${listGrades}">
								<option value="${grade.id}">${grade.code}</option>
							</c:forEach>
						</select>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="btn-ajouter"></label>
					<div class="col-md-4">
						<button id="btn-ajouter" class="btn btn-primary">Ajouter</button>
					</div>
				</div>


			</fieldset>
			<sec:csrfInput/>
		</form>
		
		<!-- en cas d'erreur un paramètre "error" est créé par Spring Security -->
		<c:if test="${param.error !=null}">
			Erreur d'authentification
		</c:if>
		
	</div>
</body>
</html>
