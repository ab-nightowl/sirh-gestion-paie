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
	<title>PAIE - Lister employés</title>
	<link rel="icon" type="image/png" sizes="32x32" href="favicon-32x32.png">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href='<c:url value="/bootstrap-3.3.7-dist/css/bootstrap.min.css"></c:url>'>

</head>
<body>
	<div class="container">
		<h1>Liste des employés</h1>
		
		<div class="row">
			
			<div class="col-md-12 col-lg-12">
				<table class="table table-striped table-bordered" id="table">
					<thead>
						<tr>
							<th>Date/heure création</th>
							<th>Matricule</th>
							<th>Grade</th>
							<th>Entreprise</th>
							<th>Profil Remunération</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:forEach var="employe" items="${listEmployes}">
							<tr> 
								<td>${employe.dateHeureCreation.getDayOfMonth()}/${employe.dateHeureCreation.getMonthValue()}/${employe.dateHeureCreation.getYear()} ${employe.dateHeureCreation.getHour()}
								:${employe.dateHeureCreation.getMinute()}
								:${employe.dateHeureCreation.getSecond()}
								</td>
								<td>${employe.matricule}</td>
								<td>${employe.grade.code}</td>
								<td>${employe.entreprise.denomination}</td>
								<td>${employe.profilRemuneration.code}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>