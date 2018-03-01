<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<title>Traducteur de sous titres</title>
		<style type="text/css">
			.error {
				color : red ;
				margin : 10px 10px 10px 10px;
			}
			.margin{
				margin : 10px 10px 10px 10px;
			}
		</style>
	</head>
	<body class="margin">
		<div class="container-fluid">
			<div class="row">
				<c:if test="${ !empty error }">
					<p class = "error col-sm-offset-4 col-sm-4"><span class="glyphicon glyphicon-warning-sign"></span> ${ error }</p>
				</c:if>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-4">
					<form method ="post" action="index" enctype="multipart/form-data" class = "well">
						<fieldset >
							<legend>Charger un nouveau fichier à traduire</legend>
							<div class="form-group">
								<label for = "title">Titre : </label>
								<input id ="title" name="title" type ="text" class = "form-control"/>
							</div>
							<div class="form-group">
								<label for  = "languageName">Langue des sous titres : </label>
								<select id = "languageName" name = "languageName" class = "form-control">
									<c:forEach items="${ languagesNames }" var="languageName" varStatus="status">
										<option values = "${ languageName }"><c:out value="${ languageName }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="fichier" class = "col-xs-6">Fichier SRT : </label>
								<input type="file" name="fichier" id ="fichier"/>
							</div>
							<div class="form-group">
								<input type ="submit" value = "Valider" class="btn btn-primary"/>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="col-sm-4">
					<form method ="get" action="EditSubtitle" class = "well">
						<fieldset>
							<legend>Travailler sur un fichier existant</legend>
							<div class="form-group">
								<label for = "subtitlesOriginalsNames">Fichier : </label>
								<select  name = "subtitlesOriginalsNames" id = "subtitlesOriginalsNames" class = "form-control">
									<c:forEach items="${ subtitlesOriginalsNames }" var="subtitlesOriginalsName" varStatus="status">
										<option values = "${ subtitlesOriginalsNames }"><c:out value="${ subtitlesOriginalsName }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for = languagesNameDest>Langue de destination : </label>
								<select name = "languagesNameDest" id = languagesNameDest class = "form-control">
									<c:forEach items="${ languagesNames }" var="languageNameDest" varStatus="status">
										<option values = "${ languageNameDest }"><c:out value="${ languageNameDest }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<input type ="submit" value = "Valider" class="btn btn-primary"/>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="col-sm-4">
					<form method ="post" action="DownloadSRT" class = "well">
						<fieldset>
							<legend>Générer un fichier SRT</legend>
							<div class="form-group">
								<label for = "subtitlesNames">Fichier : </label>
								<select  name = "subtitlesNames" id = "subtitlesNames" class = "form-control">
									<c:forEach items="${ subtitlesNames }" var="subtitlesName" varStatus="status">
										<option values = "${ subtitlesNames }"><c:out value="${ subtitlesName }" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<input type ="submit" value = "Valider" class="btn btn-primary"/>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>