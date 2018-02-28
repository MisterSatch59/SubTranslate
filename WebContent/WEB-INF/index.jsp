<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Traducteur de sous titres</title>
	</head>
	<body>
		<form method ="post" action="Index">
			<fieldset>
				<legend>Charger un nouveau fichier à traduire</legend>
				<label>Titre : </label>
				<input id="titre" type ="text"/>

				<label>Langue des sous titres : </label>
				<select name = "languageName">
					<c:forEach items="${ languagesNames }" var="languageName" varStatus="status">
						<option values = "${ languageName }"><c:out value="${ languageName }" /></option>
					</c:forEach>
				</select>

				<label>Fichier SRT : </label>
				<input type="file"/>

				<input type ="submit" value = "valider"/>
			</fieldset>
		</form>
		
		<form method ="get" action="EditSubtitle">
			<fieldset>
				<legend>Travailler sur un fichier existant : </legend>
				<label>Fichier : </label>
				<select  name = "subtitlesOriginalsNames">
					<c:forEach items="${ subtitlesOriginalsNames }" var="subtitlesOriginalsName" varStatus="status">
						<option values = "${ subtitlesOriginalsNames }"><c:out value="${ subtitlesOriginalsName }" /></option>
					</c:forEach>
				</select>
				<label>Langue de destination : </label>
				<select name = "languagesNameDest">
					<c:forEach items="${ languagesNames }" var="languageNameDest" varStatus="status">
						<option values = "${ languageNameDest }"><c:out value="${ languageNameDest }" /></option>
					</c:forEach>
				</select>
				<input type ="submit" value = "valider"/>
			</fieldset>
		</form>
		
		<form method ="post" action="DownloadSRT">
			<fieldset>
				<legend>Générer un fichier SRT : </legend>
				<label>Fichier : </label>
				<select  name = "subtitlesNames">
					<c:forEach items="${ subtitlesNames }" var="subtitlesName" varStatus="status">
						<option values = "${ subtitlesNames }"><c:out value="${ subtitlesName }" /></option>
					</c:forEach>
				</select>
				<input type ="submit" value = "valider"/>
			</fieldset>
		</form>
	</body>
</html>