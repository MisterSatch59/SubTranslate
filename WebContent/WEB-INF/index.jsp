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
				<select>
					<option>fr</option>
					<option>en</option>
				</select>

				<label>Fichier SRT : </label>
				<input type="file"/>

				<input type ="submit" value = "valider"/>
			</fieldset>

			<fieldset>
				<legend>Travailler sur un fichier existant : </legend>
				<label>Fichier : </label>
				<select>
					<option>fichier1</option>
					<option>fichier2</option>
				</select>
				<label>Langue de destination : </label>
				<select>
					<option>fr</option>
					<option>en</option>
				</select>
				<input type ="submit" value = "valider"/>
		</form>
	</body>
</html>